package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.item.controller")
public class Application {
    public static void main(String[] args) {
        //遇到的问题
        //1.文件只有在webapp下 才能识别，不需要加/web-inf/
        //2必须使用mvn spring boot：run 或 mvn clean spring boot：run (添加新的文件时需要clean重新打包)
        //3.一个项目只有一个main方法   @SpringBootApplication 不限制
        SpringApplication.run(Application.class,args);

    }
}
