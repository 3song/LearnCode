package com.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceUtils {
    @Resource
    private StringRedisTemplate redisTemplates;

    public void setString(String key,Object data,Long time){
        if (data instanceof String){
            String value= (String) data;
            //在此直接保存键值  时间在后面判断
            redisTemplates.opsForValue().set(key,value);
        }
        if (time != null) {
            //expire  表示设置超时时间
            redisTemplates.expire(key, time, TimeUnit.SECONDS);
        }
    }
    public String getString(String key){
        return redisTemplates.opsForValue().get(key);
    }
    public void delete(String key){
        redisTemplates.delete(key);
    }
}
