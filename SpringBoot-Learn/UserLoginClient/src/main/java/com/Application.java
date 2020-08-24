package com;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.*")
@MapperScan("com.mapper")
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
    }
}
