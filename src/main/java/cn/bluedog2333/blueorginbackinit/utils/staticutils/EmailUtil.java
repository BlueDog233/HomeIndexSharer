package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.hutool.extra.mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUtil {
    /**
     *
     * @param email 邮箱地址
     * @param title 邮箱标题
     * @param content 邮箱内容
     */
    public static void sendEmail(String email,String title,String content){
        MailUtil.send(email, title, content, false);
    }

    public static void sendVerifyCode(String email,String code){
        sendEmail(email,"蓝泉验证码",code+"   5分钟后过期");
    }
}
