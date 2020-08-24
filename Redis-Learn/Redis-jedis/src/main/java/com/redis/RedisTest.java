package com.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;


public class RedisTest {
    private static Logger logger= LoggerFactory.getLogger(RedisTest.class);
    private static Jedis jedis;
    public static void main(String[] args) {
            jedis=new Jedis();
            //需要给redis设置密码
            jedis.auth("211716");
            setSet();
            //setList();
            //setMap();
            //setString();
    }
    public static void setString(){
        //redis加入String
        jedis.set("name","123456");
        logger.info("添加String数据成功");
    }
    public static void setMap(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("3SONG","25岁");
        map.put("age","19951221");
        //redis加入map集合
        jedis.hmset("info",map);
        logger.info("添加map成功.......");
    }
    public static void setList(){
        //redis加入List集合
        jedis.lpush("sex","男");
        jedis.lpush("sex","男1");
        logger.info("添加List成功.......");
    }
    public static void setSet(){
        //redis加入Set集合
        jedis.sadd("info1","女");
        jedis.sadd("mobile","13264011111");
        logger.info("添加Set成功.......");
    }
    //可指定排序的集合
    public static void zSet(){
        jedis.rpush("");
    }
}
