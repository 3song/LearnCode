package com.service;

import com.alibaba.fastjson.JSONObject;
import com.dao.UsersDao;
import com.entity.Users;
import com.utils.EhCacheUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UsersService implements Serializable {
    @Resource
    private UsersDao usersDao;
    @Resource
    private EhCacheUtils ehCacheUtils;
    @Resource
    private RedisService redisService;
    private Lock lock=new ReentrantLock();
    private String cacheName="userCache";

    public int insertUser(Users users){
        return usersDao.insertUser(users);
    }


    public Users getUserById(Integer id) {
        //1先查询一级缓存 key 以当前类名+方法名+id+参数值FD
        String key=this.getClass().getName()+"-"+Thread.currentThread().getStackTrace()[1].getMethodName()+"-id:"+id;
        System.out.println(key);
        //1.1 如果存在 直接返回
        Users users = (Users) ehCacheUtils.get(cacheName, key);
        if (users!=null){
            System.out.println("key:"+key+"直接从一级缓存获取数据"+users.toString());
            return users;
        }
        Users userById = null;
        try {
            //使用本地锁防止redis雪崩效应
            lock.lock();
            //如果一级缓存没有对应值则查询2级缓存redis json 如果有则更新ehcache缓存
            String userJson=redisService.getStringKey(key);
            if (!StringUtils.isEmpty(userJson)){
                JSONObject jsonObject=new JSONObject();
                Users users1 = jsonObject.parseObject(userJson, Users.class);
                //再存放到ehcache中
                ehCacheUtils.put(cacheName, key, users1);
                return users1;
            }

            //如果二级缓存没有   则查询数据库
            userById = usersDao.getUserById(id);
            if (userById==null){
                return null;
            }
            //存放到redis中
            redisService.setString(key, new JSONObject().toJSONString(userById));
            //再存放到ehcache
            ehCacheUtils.put(cacheName, key, userById);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return userById;
    }

}
