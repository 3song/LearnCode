package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSwagger {
    public static void main(String[] args) {
        // http://localhost:8084/swagger-ui.html
        SpringApplication.run(AppSwagger.class, args);
    }
}
