package com.atguigu.serv_stat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName springapp
 * @Description TODO
 * @Date 2022/10/21 16:16
 */

@SpringBootApplication
@ComponentScan(basePackages={"com.atguigu"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class springstat {
    public static void main(String[] args) {
        SpringApplication.run(springstat.class,args);
    }
}
