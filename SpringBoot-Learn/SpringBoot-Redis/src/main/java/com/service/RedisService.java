package com.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key,Object value){
        this.setObject(key, value,null);
    }
    public void setString(String key,Object value,Long time){
        this.setObject(key, value,time);
    }

    public void setList(String key,List<String> listValue){
        this.setObject(key,listValue,null);
    }
//    public void setMap(String key,List<String> listValue){
//        this.setObject(key,listValue);
//    }
    //记得设置缓存有效期
    public void setObject(String key,Object value,Long time){
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
        //set map
//        if (value instanceof Map){
//            Map<String,Object> map=(HashMap<String, Object>)value;
//            stringRedisTemplate.opsForHash().putAll("mapTest",map);
//        }
    }
    public String getStringKey(String key){
        //可以写成上方判断类型的方法
        return stringRedisTemplate.opsForValue().get(key);
    }
}
