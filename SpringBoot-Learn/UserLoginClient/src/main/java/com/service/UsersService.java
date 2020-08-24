package com.service;

import com.dao.UsersDao;
import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersService {
    @Resource
    UsersDao usersDao=new UsersDao();
    public int insert(Users users){
        return usersDao.insert(users);
    }

    public List<Users> findAll(){
        return usersDao.findAll();
    }

    public Users findByNameAndAge(Users users){
        return usersDao.findByNameAndAge(users);
    }
}
