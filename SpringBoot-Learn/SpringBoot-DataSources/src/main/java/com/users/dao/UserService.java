package com.users.dao;

import com.users.mapper.UsersMapper;
import com.pojo.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserService {
    @Resource
    private UsersMapper usersMapper;
    @Transactional(rollbackFor=Exception.class)
    public int insertUser(Users users){
        return usersMapper.insert(users);
    }
}
