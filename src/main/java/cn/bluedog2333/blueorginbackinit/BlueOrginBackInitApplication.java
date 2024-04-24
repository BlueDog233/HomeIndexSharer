package cn.bluedog2333.blueorginbackinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.bluedog2333.blueorginbackinit.mapper")
@SpringBootApplication
public class BlueOrginBackInitApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlueOrginBackInitApplication.class, args);
    }
}
