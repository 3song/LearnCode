package com.service;

import com.dao.UserDao;
import com.pojo.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserDao userDao;

    public int insert(Users users) {
        System.out.println("进入Service层"+users.getName()+users.getAge()+users.getId());
        return userDao.insert(users);
    }

    public List<Users> findByName(Users users) {
        return userDao.findByName(users);
    }
}
