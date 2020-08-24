package com.dao;

import com.entity.Users;
import com.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    @Resource
    private UserMapper userMapper;

    public Users getUserByPassword(Users users){
        return userMapper.getUserByPassword(users);
    }

    public int insertUser(Users users){
        return userMapper.insertUser(users);
    }
}
