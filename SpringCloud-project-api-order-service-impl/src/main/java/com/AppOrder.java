package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class AppOrder {
    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();
        InputStream in= AppOrder.class.getClassLoader().getResourceAsStream("application.yml");
        properties.load(in);
        SpringApplication springApplication=new SpringApplication(AppOrder.class);
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
        //SpringApplication.run(AppOrder.class, args);
    }
}
