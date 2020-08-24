package com.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    //使用属性读取配置文件信息
    @Value("${songmember}")
    private String applicationValue;

    @RequestMapping("/getApplicationValue")
    public String getApplicationValue(){
        System.out.println("123213212222222222222222"+applicationValue);
        return applicationValue;
    }
}
