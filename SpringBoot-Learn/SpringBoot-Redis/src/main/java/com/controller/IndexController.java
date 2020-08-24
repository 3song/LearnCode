package com.controller;

import com.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class IndexController {
    Logger logger= LoggerFactory.getLogger(IndexController.class);
    @Resource
    private RedisService redisService;
    @Resource
    private RedisTemplate redisTemplate;
    @RequestMapping("/setString")
    public String setString(String key,String value){
        redisService.setString(key,value);
        logger.info("添加String类型成功");
        return "success";
    }
    @RequestMapping("/getKey")
    public String getKey(String key){
        logger.info("查找"+key+"的数据");
        return redisService.getStringKey(key);
    }

    @RequestMapping("/setList")
    public String setList(String key){
        List<String> list = new ArrayList<String>();
        list.add("test01");
        list.add("test02");
        redisService.setList(key,list);
        logger.info("添加List类型成功");
        return "success";

    }

    /*
     * @Author 陈磊
     * @Description //TODO
     * Redis生成全局id方法
     * 前缀=当前日期=2020060754658254-5位自增id（例如：00001 Redis是单线程，天生保证线程安全）
     * 相同毫秒情况下，最多只能生成 10万-1=99999个订单号
     * 提前生成号码存放在redis中
     * @Date 18:04 2020/6/7
     * @param key
     * @return java.lang.Long
     **/
    @RequestMapping("/generatedId")
    public String order(String key){
        RedisAtomicLong redisAtomicLong=new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long l = redisAtomicLong.incrementAndGet();//生成自增id 从1开始 每次加1
        //%105d 中 5 表示5位数字
        String orderId =prefix()+String.format("%1$05d", l);
        return orderId;
    }
    public static String prefix(){
        String temp_str="";
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMDDHHmmss");
        temp_str = sdf.format(date);
        return temp_str;
    }

}
