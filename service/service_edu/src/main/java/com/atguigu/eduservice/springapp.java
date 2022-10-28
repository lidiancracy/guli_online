package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName springapp
 * @Description TODO
 * @Date 2022/10/21 16:16
 */

@SpringBootApplication
@ComponentScan(basePackages={"com.atguigu"})
@EnableDiscoveryClient
@EnableFeignClients
public class springapp {
    public static void main(String[] args) {
        SpringApplication.run(springapp.class,args);
    }
}
