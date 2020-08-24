package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class AppZooKeeperOrder {
    public static void main(String[] args) {
        SpringApplication.run(AppZooKeeperOrder.class, args);
    }
    //A component required a bean of type 'org.springframework.web.client.RestTemplate' that could not be found.
    //RestTemplate 模板找不到的问题  应该把RestTemplate模板注册到容器中 (因为默认没有注册)
    @Bean
    @LoadBalanced //不需要 但是是什么意思？开启负载均衡器
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //如果使用Rest方式以别名调用需要依赖Ribbon负载均衡器 必须开启负载均衡器 @LoadBalanced

}
