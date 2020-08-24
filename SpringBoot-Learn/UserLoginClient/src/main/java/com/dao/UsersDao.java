package com.dao;

import com.entity.Users;
import com.mapper.UsersMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UsersDao {
    @Resource
    UsersMapper usersMapper;
    public int insert(Users users){
        return usersMapper.insert(users);
    }

    public List<Users> findAll(){
        return usersMapper.findAll();
    }

    public Users findByNameAndAge(Users users){
        return usersMapper.findByNameAndAge(users);
    }
}
