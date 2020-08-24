package com.service;

import com.dao.UserDao;
import com.entity.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public Users getUserByPassword(Users users){
        return userDao.getUserByPassword(users);
    }

    public int insertUser(Users users){
        return userDao.insertUser(users);
    }
}
