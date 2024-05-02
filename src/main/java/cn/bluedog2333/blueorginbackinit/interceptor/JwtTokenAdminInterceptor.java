package cn.bluedog2333.blueorginbackinit.interceptor;
import cn.bluedog2333.blueorginbackinit.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * jwt令牌校验的拦截器
 */
/*
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    */
/**
     * 校验jwt
     *//*

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(jwtProperties.getTokenName());
        try {
            Claims claims= (Claims) Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
*/
