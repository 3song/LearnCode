package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = {"com.dao"})
public class AppCloudDisk {
    //http://192.168.50.73:8080/findById/tyoIqHIBNwZH0tZldXgm
    // http://192.168.50.73:8080/search?keyWord="纯手写" 带条件 而且分词的词语也会并列查出
    // http://192.168.50.73:8080/search?keyWord="纯手写"&page=2&size=2 带条件并且带分页
    public static void main(String[] args) {
        SpringApplication.run(AppCloudDisk.class, args);
    }
}
