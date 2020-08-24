package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSessions {
    //http://www.3song.com/setSession?sessionKey=123&sessionValue=test
    public static void main(String[] args) {
        SpringApplication.run(AppSessions.class, args);
    }
}
