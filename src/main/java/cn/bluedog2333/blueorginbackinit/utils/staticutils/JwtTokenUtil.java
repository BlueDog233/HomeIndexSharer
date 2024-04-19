package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.hutool.core.convert.Convert;
import cn.hutool.jwt.JWT;
import org.springframework.stereotype.Component;




public class JwtTokenUtil {
    /**
     * 通过 id name password 获得token
     * @param id
     * @param name
     * @param password
     * @return
     */
    public static String getToken(int id,String name,String password) {
        String token = JWT.create()
                .setPayload("id", id)
                .setPayload("name", name)
                .setPayload("password", password)
                .setKey("indesharer".getBytes())
                .sign();
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        return JWT.of(token).setKey("bluedog".getBytes()).verify();
    }

    /**
     * 通过token获取id
     * @param token
     * @return
     */
    public static int getId(String token) {
        Object id = JWT.of(token).setKey("indexsharer".getBytes()).getPayload().getClaim("id");
        int re= Convert.toInt(id);
        return re;
    }
}