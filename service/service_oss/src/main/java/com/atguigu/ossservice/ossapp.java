package com.atguigu.ossservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName com.atguigu.ossservice.ossapp
 * @Description TODO
 * @Date 2022/10/24 17:50
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.atguigu"})
@EnableDiscoveryClient
public class ossapp {
    public static void main(String[] args) {
        SpringApplication.run(ossapp.class,args);
    }
}
