package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppES {
    // http://192.168.50.73:8080/addUser?id=2 添加user
    // http://192.168.50.73:8080/findUser?id=2 查找user
    public static void main(String[] args) {
        SpringApplication.run(AppES.class, args);
    }
}
