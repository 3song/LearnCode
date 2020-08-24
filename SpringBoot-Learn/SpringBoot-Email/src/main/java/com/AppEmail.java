package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppEmail {
    public static void main(String[] args) {
        //http://localhost:8083/sendEmail  从1048253765@qq.com 发送到songs3ongs@icloud.com 一封测试邮件
        SpringApplication.run(AppEmail.class, args);
    }
}
