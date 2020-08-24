package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.mapper"})
@EnableCaching
public class AppCache {

    public static void main(String[] args) {
        SpringApplication.run(AppCache.class, args);
    }
}
