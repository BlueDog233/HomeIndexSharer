package cn.bluedog2333.blueorginbackinit.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "homeindexsharer.file")
@Data
public class FileStoreProperties implements InitializingBean {
    public static String storePath;
    private String htmlStorePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        storePath=htmlStorePath;
    }
}
