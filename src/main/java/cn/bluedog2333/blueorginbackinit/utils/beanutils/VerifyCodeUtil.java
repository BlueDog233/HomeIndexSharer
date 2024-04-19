package cn.bluedog2333.blueorginbackinit.utils.beanutils;

import cn.bluedog2333.blueorginbackinit.utils.staticutils.EmailUtil;
import cn.bluedog2333.blueorginbackinit.utils.staticutils.OSSUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class VerifyCodeUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    public String getVerifyCode(String key){
        String code = (String) redisTemplate.opsForValue().get(key);
        return code;
    }
    //两种方式,1.邮箱验证(email做key),2.验证码验证(nickname做key)
    public void setVerifyCode(String key,String code){
        redisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);
    }

    /**
     * 验证验证码是否正确
     * @param key
     * @param code 等待验证的code
     * @return
     */
    public boolean verify(String key,String code){
        String value = (String) redisTemplate.opsForValue().get(key);
        if(value == null){
            return false;
        }
        return value.equalsIgnoreCase(code);
    }

    /**
     *
     * @param nickname 图片验证码(在redis中 以 nickname作为key)
     * @return ,返回图片链接
     * @throws IOException
     */
    public String sendVerifyImg(String nickname) throws IOException {
        String code= RandomUtil.randomString(4);
        setVerifyCode(nickname,code);
        Image image = CaptchaUtil.createShearCaptcha(100,40).createImage(code);
        return OSSUtil.uploadImg((RenderedImage) image,"yzm/"+String.valueOf(System.currentTimeMillis()),true);
    }

    /**
     *
     * @param email 以Email作为key,并发送到email
     */

    public void sendVerifyCode(String email){
        String code= RandomUtil.randomString(4);
        EmailUtil.sendVerifyCode(email,code);
        setVerifyCode(email,code);
    }
}
