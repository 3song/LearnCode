package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //不加这个 找不到报错 A component required a bean of type 'com.feign.MemberApiFeign' that could not be found.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
