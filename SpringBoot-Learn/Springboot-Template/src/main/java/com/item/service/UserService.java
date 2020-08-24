package com.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public void insertUser(String name,Integer age){
        System.out.println("创建用户请求");
        //jdbc 的对象操作 name age 为前台传入对象
        jdbcTemplate.update("insert into users values (null,?,?)",name,age);
        System.out.println("创建用户成功");
    }

}
