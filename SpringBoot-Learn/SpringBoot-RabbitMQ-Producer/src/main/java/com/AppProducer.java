package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppProducer {
    // http://192.168.50.73:8080/sendMsg?queueName=FANOUT_EMAIL_QUEUE 发邮件  必须发消息创建交换机
    // http://192.168.50.73:8080/sendMsg?queueName=FANOUT_SMS_QUEUE
    public static void main(String[] args) {
        SpringApplication.run(AppProducer.class, args);
    }
}
