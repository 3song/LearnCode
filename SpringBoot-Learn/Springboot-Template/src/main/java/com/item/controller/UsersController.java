package com.item.controller;

import com.item.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    //必须加入自动装配
    @Autowired(required=true)
    public UserService userService;

    //name,age 自动传入
    @RequestMapping("/insertUser")
    public String insertUser(String name,Integer age){
        userService.insertUser(name,age);
        return "success";
    }
}
