package com.controller;

import com.dao.UserDao;
import com.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableAutoConfiguration
@RestController
public class UserController {

    @Autowired
    public UserDao userDao;
    @RequestMapping("/getUser")
    public UsersEntity getUser(Integer id){
        UsersEntity user = userDao.getOne(id);
        System.out.println("获得用户对象");
        return user;
    }
}
