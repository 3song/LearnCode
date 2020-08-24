package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
    Logger logger=LoggerFactory.getLogger(IndexController.class);
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/setString")
    public String setString(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        logger.info("添加String类型成功");
        return "success";
    }
    @RequestMapping("/getKey")
    public String getKey(String key){
        logger.info("查找"+key+"的数据");
        return redisTemplate.opsForValue().get(key).toString();
    }
}
