package com.controller;

import com.entity.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private UsersService usersService;
    @Value("${application-properties}")
    private String stringPro;
    @Resource
    private CacheManager cacheManager;
    @RequestMapping("/insertUser")
    public Users insertUser(String name,Integer age){
        Users users=new Users(name, age);
        usersService.insertUser(users);
        logger.info("添加对象成功");
        return users;
    }
    @RequestMapping("/findAll")
    public List<Users> findAll(){
        logger.info("返回对象成功");
        return usersService.findAll();
    }

    @RequestMapping("/removeKey")
    public String removeKey(){
        //清理jvm内置缓存方法
        cacheManager.getCache("baseCache").clear();
        return "清理缓存success";
    }

    @RequestMapping("/getValue")
    public String getValue(){
        logger.info("返回对象成功");
        return stringPro;
    }

}
