package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class AppConsulMember {
    //http://localhost:8500/ui/dc1/services
    public static void main(String[] args) {
        SpringApplication.run(AppConsulMember.class, args);
    }
    @Bean
    @LoadBalanced
    //不需要 但是是什么意思？开启负载均衡器
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //如果使用Rest方式以别名调用需要依赖Ribbon负载均衡器 必须开启负载均衡器 @LoadBalanced
}
