package com.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
//表示注入spring容器加载(且只扫当前java文件下的方法)
public class IndexController {
    @RequestMapping("/indexController")
    public String IndexController(){
        System.out.println("12313111123"+Class.class.getClass().getResource("/").getPath());
        return "index";
    }
}
