package cn.bluedog2333.blueorginbackinit;

import cn.bluedog2333.blueorginbackinit.mapper.UserMapper;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("cn.bluedog2333.blueorginbackinit.mapper")
@SpringBootApplication
public class BlueOrginBackInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueOrginBackInitApplication.class, args);

    }


}
