package com.service;

import com.entity.Users;
import com.mapper.UsersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersService {
    @Resource
    private UsersMapper usersMapper;
    public int insertUser(Users users){
        return usersMapper.insertUser(users);
    }

    public List<Users> findAll(){
        return usersMapper.findAll();
    }
}
