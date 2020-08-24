package com.controller;


import com.pay.dao.PayService;
import com.pojo.Users;

import com.users.dao.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
@Transactional(rollbackFor=Exception.class)
public class UserController {
    @Resource
    private PayService payService;
    @Resource
    private UserService userService;

    @RequestMapping("/insertUsersUser")
    @Transactional(rollbackFor=Exception.class)
    public Users insertUsersUser(String name, Integer age) {
        Users users = new Users();
        users.setAge(age);
        users.setName("users" + name);
        System.out.println("名字是：" + name + "年龄是：" + age);
        userService.insertUser(users);//插入数据
        return users;
    }

    @RequestMapping("/insertPayUser")
    @Transactional(rollbackFor=Exception.class)
    public Users insertPayUser(String name, Integer age) {
        Users users = new Users();
        users.setAge(age);
        users.setName("pay" + name);
        System.out.println("名字是：" + name + "年龄是：" + age);
        payService.insertUser(users);//插入数据
        return users;
    }

    @RequestMapping("/insertPayUserSW")
    //@Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Transactional(rollbackFor=Exception.class)
    public Users insertPayUserSW (String name, Integer age) throws SQLException {
        Users users = new Users();
        users.setAge(age);
        users.setName("pay" + name);
        System.out.println("名字是：" + name + "年龄是：" + age);
        payService.insertUser(users);//插入数据
        int i=1/0;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return users;
    }
}
