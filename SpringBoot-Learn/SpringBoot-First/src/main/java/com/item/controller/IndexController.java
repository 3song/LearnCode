package com.item.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Map;

@Controller
//表示注入spring容器加载(且只扫当前java文件下的方法)
public class IndexController {
    @RequestMapping("/indexController")
    public String indexController(Map<String,Object> result) {
        System.out.println("welcome to 3SONG World");
        result.put("name","3SONG");
        return "welcome";
    }
}
