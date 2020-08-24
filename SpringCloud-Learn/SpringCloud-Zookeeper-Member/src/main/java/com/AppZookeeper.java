package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.zookeeper.ConditionalOnZookeeperEnabled;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableDiscoveryClient
//注解的作用是如果服务使用consul、zookeeper 使用@EnableDiscoveryClient注解向注册中心上注册服务
public class AppZookeeper {
    public static void main(String[] args) {
        SpringApplication.run(AppZookeeper.class, args);
    }
}
