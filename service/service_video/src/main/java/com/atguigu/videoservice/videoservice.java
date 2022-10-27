package com.atguigu.videoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName videoservice
 * @Description TODO
 * @Date 2022/10/27 18:24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.atguigu"})
public class videoservice {
    public static void main(String[] args) {
        SpringApplication.run(videoservice.class,args);
    }
}
