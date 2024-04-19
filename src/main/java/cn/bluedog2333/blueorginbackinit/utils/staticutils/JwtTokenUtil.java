package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.hutool.core.convert.Convert;
import cn.hutool.jwt.JWT;
import org.springframework.stereotype.Component;




public class JwtTokenUtil {
    public static String getToken(int id,String name,String password) {
        String token = JWT.create()
                .setPayload("id", id)
                .setPayload("name", name)
                .setPayload("password", password)
                .setKey("bluedog".getBytes())
                .sign();
        return token;
    }

    public static boolean verify(String token) {
        return JWT.of(token).setKey("bluedog".getBytes()).verify();
    }
    public static int getId(String token) {
        Object id = JWT.of(token).setKey("bluedog".getBytes()).getPayload().getClaim("id");
        int re= Convert.toInt(id);
        return re;
    }
}