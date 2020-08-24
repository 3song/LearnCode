package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDao")
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;


    public void add(String name,Integer age){
        System.out.println("执行UserDao的add方法......");
        String sql="INSERT INTO users(name,age) VALUES(?,?)";
        int update=jdbcTemplate.update(sql,name,age);
        System.out.println("添加数据成功，添加了"+update+"条数据");
    }
}
