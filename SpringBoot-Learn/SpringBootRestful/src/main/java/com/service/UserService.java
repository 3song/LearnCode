package com.service;

import com.dao.UsersDao;
import com.entity.Users;
import com.mapper.UsersMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UsersDao usersDao;

    public int userInsert(Users users){
        return usersDao.userInsert(users);
    }


    public List<Users> findUsersAll(){
        return usersDao.findUsersAll();
    }


    public Users findByNameAndAgeUsers(Users users){
        return findByNameAndAgeUsers(users);
    }

}
