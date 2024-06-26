package cn.bluedog2333.blueorginbackinit.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "homeindexsharer.base")
@Data
public class BaseProperties {
    private String url;
}
