package com.lock.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//https 协议特殊字符处理
@RestController
public class IndexController {
    //1.什么是特殊字符处理 （RPC远程通讯 实现加密+？）正好和Http协议特殊字符相同，导致转换成空格
    //
    @RequestMapping("/index")
    public String index(String username){
        System.out.println("username:"+username);
        return username;
    }
}
