package com.item.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
//表示注入spring容器加载(且只扫当前java文件下的方法)
@RestController
//表示该接口总是返回json格式
public class HelloWorldController {

    @RequestMapping("/index")
    public String index(){
        return "success";
    }
    @RequestMapping("/getMap")
    public Map<String,Object> getMap(){
        Map<String,Object> result =  new HashMap<String, Object>();
        result.put("errorCode","200");
        result.put("errorMsg","成功");
        return result;
    }


}
