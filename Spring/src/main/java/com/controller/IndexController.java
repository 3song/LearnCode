package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@Controller
public class IndexController {
    private static Integer count=0;
    @RequestMapping("/index")
    public String index(){
        System.out.println("index页面跳转");
        return "indexJsp";
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        if (count != 0) {
            count = 0;
        }
        inCount();
    }
    public void inCount(){
        synchronized (this){
            System.out.println("Test...........count："+(count++));
        }
    }
}
