package cn.bluedog2333.blueorginbackinit.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "homeindexsharer.jwt")
@Data
public class JwtProperties {
    private String secret;
    private long expiratime;
    private String tokenName;
}
