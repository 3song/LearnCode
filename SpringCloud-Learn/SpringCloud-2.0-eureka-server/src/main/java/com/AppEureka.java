package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEureka {
    //@EnableEurekaServer 表示开启Eureka服务 （开启注册中心）
    public static void main(String[] args) {
        SpringApplication.run(AppEureka.class, args);
    }
}
