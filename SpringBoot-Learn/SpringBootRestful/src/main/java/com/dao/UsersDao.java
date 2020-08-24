package com.dao;

import com.entity.Users;
import com.mapper.UsersMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UsersDao {
    @Resource
    private UsersMapper usersMapper;

    public int userInsert(Users users){
        return usersMapper.userInsert(users);
    }

    public List<Users> findUsersAll(){
       return usersMapper.findUsersAll();
    }

    public Users findByNameAndAgeUsers(Users users){
        return findByNameAndAgeUsers(users);
    }
}
