package com.dao;

import com.entity.Users;
import com.mapper.UsersMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UsersDao {
    @Resource
    private UsersMapper usersMapper;

    public int insertUser(Users users){
        return usersMapper.insertUser(users);
    }

    public Users getUserById(Integer id) {
        return usersMapper.getUserById(id);
    }
}
