package com.controller;

import com.dao.UserDao;
import com.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class IndexController {
    @Resource
    private UserDao userDao;

    @RequestMapping("/addUser")
    public User addUser(User user){
        user.setName("3song");
        user.setSex(0);
        user.setAge(22);
        return userDao.save(user);//自动转化json存储
    }
    //查询文档
    @RequestMapping("/findUser")
    public Optional<User> findUser(String id){
        return userDao.findById(id);//自动转化json存储
    }


}
