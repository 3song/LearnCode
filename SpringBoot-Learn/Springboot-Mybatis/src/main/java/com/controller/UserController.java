package com.controller;

import com.pojo.Users;
import com.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    @Resource
    public UserService userService;

    @RequestMapping("/insertUser")
    public Users insertUser(String name,Integer age){
        Users users=new Users();
        users.setAge(age);
        users.setName(name);
        System.out.println("名字是："+name+"年龄是："+age);
        userService.insert(users);//插入数据
        return users;
    }
    //查询所有人员
    @RequestMapping("/findByName")
    public List<Users> findByName(String name){
        Users users=new Users();
        users.setName(name);
        return userService.findByName(users);
    }
}
