package cn.bluedog2333.blueorginbackinit.utils.staticutils;

import cn.bluedog2333.blueorginbackinit.properties.JwtProperties;
import cn.hutool.core.convert.Convert;
import cn.hutool.jwt.JWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;


public class JwtTokenUtil {
    /**
     * 通过 id name password 获得token
     * @param id
     * @param name
     * @param password
     * @return
     */
    public static String getToken(int id,String name,String password,JwtProperties jwtProperties) {
        String token = Jwts.builder()
                .claim("id",id)
                .claim("name",name)
                .claim("password",password)
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8))
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpiratime()))
                .compact();
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