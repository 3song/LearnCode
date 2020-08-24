package com.service;

import com.dao.UsersDao;
import com.entity.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class UsersService implements Serializable {
    @Resource
    private UsersDao usersDao;

    public int insertUser(Users users){
        return usersDao.insertUser(users);
    }


    public List<Users> getUserById(Integer id) {
        return usersDao.getUserById(id);
    }
}
