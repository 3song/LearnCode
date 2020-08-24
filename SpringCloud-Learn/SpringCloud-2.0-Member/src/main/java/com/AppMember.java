package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableEurekaClient
public class AppMember {
    //@EnableEurekaClient 将当前服务注册到Eureka 注册中心上
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class, args);
    }
}
