package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppPass {
    public static void main(String[] args) {
        //http://localhost:8080/getPayToken?id=12&money=500 使用传参数
        //http://localhost:8080/pay?payToken=f3eb9374-659a-4e0c-aa19-4e0573acc3ec 解参数
        SpringApplication.run(AppPass.class, args);
    }
}
