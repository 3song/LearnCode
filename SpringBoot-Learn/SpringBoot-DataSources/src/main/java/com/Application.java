package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan(basePackages = {"com.users.*","com.pay.*","com.controller","com.pojo"})
@SpringBootApplication
@MapperScan(basePackages = {"com.users.mapper","com.pay.mapper"})
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
