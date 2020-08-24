package com.dao;

import com.users.mapper.UsersMapper;
import com.pojo.Users;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao  {
    @Resource
    public UsersMapper usersMapper;



    public int insert(Users record){
        System.out.println("record name:"+record.getName());
        return usersMapper.insert(record);
    };

    public List<Users> findByName(Users record){
        System.out.println("record name:"+record.getName());
        return usersMapper.findByName(record);
    };




}
