package com.controller;

import com.ehcache.MyCache;
import com.entity.Users;
import com.service.UsersService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    private MyCache<String,Object> mapMyCache;
    @Resource
    private UsersService usersService;
    @Resource
    private CacheManager cacheManager1;
    @RequestMapping("/get")
    public String get(String key){
        return (String) mapMyCache.get(key);
    }
    @RequestMapping("/insertUser")
    public String insertUser(String name,Integer age){
        Users users=new Users(name,age);
        int i = usersService.insertUser(users);
        if (i>0){
            return "success";
        }
        return "false";
    }


    @RequestMapping("/getUserById")
    public List<Users> getUserById(Integer id){
        Cache userCache = cacheManager1.getCache("userCache");
        System.out.println("大小"+userCache.getKeys());
        return usersService.getUserById(id);
    }
    @RequestMapping("/put")
    public String put(String key,String value){
        mapMyCache.put(key, value);
        return "success";
    }
    @RequestMapping("/remove")
    public String remove(String key){
        mapMyCache.remove(key);
        return "success";
    }

}
