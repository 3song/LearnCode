package com.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key,Object value){
        this.set(key, value,null);
    }
    public void setString(String key,Object value,Long time){
        this.set(key, value,time);
    }

    public void setList(String key,List<String> listValue){
        this.set(key,listValue,null);
    }
//    public void setMap(String key,List<String> listValue){
//        this.setObject(key,listValue);
//    }
    //记得设置缓存有效期
    public void set(String key,Object value,Long time){
        //判断空值
        if (StringUtils.isEmpty(key)||value==null){
            return;
        }
        //判断类型 instanceof判断value是否为String类型
        if (value instanceof String){
            String strValue=(String) value;
            if (time!=null){
                //最后一个参数，时间格式
                stringRedisTemplate.opsForValue().set(key,strValue,time, TimeUnit.SECONDS);
            }else{
                stringRedisTemplate.opsForValue().set(key, strValue);
            }
        }
        //判断存放list类型
        if (value instanceof List){
            List<String> listValue= (List<String>) value;
            for (String str:listValue){
                    //最后一个参数，时间格式
                    stringRedisTemplate.opsForList().leftPush(key,str);
            }
        }
        //判断存放Set类型
        if (value instanceof Set){
            Set<String> listValue= (Set<String>) value;
            for (String str:listValue){
                //最后一个参数，时间格式
                stringRedisTemplate.opsForSet().add(key,str);
            }
        }
    }
    public String getStringKey(String key){
        //可以写成上方判断类型的方法
        return stringRedisTemplate.opsForValue().get(key);
    }
}
