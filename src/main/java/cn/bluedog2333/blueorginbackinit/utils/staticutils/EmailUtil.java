package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.hutool.extra.mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUtil {
    public static void sendEmail(String email,String title,String content){
        MailUtil.send(email, title, content, false);
    }

    public static void sendVerifyCode(String email,String code){
        sendEmail(email,"蓝泉验证码",code+"   5分钟后过期");
    }
}
