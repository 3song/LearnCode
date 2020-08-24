package com.utils;

import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class TokenUtils {
    //1.什么是token令牌 表示一个临时并且不允许重复的值
    //2.使用Token令牌防止重复提交：只能防止网络延迟导致的重复提交（并不能防止伪造token）
    // 使用场景：在调用API接口时，需要传递令牌，该API接口获取到令牌后，执行当前业务逻辑，执行完成后，把当前令牌删除掉
    // 令牌失效时间 建议15分钟到2小时
    //代码步骤
    // 1.获取令牌并存放在请求头中
    // 2.判断令牌是否在缓存中有对应数据
    // 3.如果缓存中没有该令牌，直接报错（请勿重复提交）
    // 4.如果缓存中有该令牌，直接执行当前业务逻辑
    // 5.执行完成业务逻辑之后，直接删除该令牌
    @Resource
    private static RedisServiceUtils redisServiceUtils;
    //获取令牌 生成令牌并存入缓存（redis）
    public static synchronized String getToken() {
        // 如何在分布式场景下使用分布式全局ID实现
        String token="token"+System.currentTimeMillis();
        redisServiceUtils.setString(token,token,1500l);
        return token;
    }

    public static synchronized boolean findToken(String tokenKey) {
        //判断该令牌是否在 redis中是否存在
        String token = redisServiceUtils.getString(tokenKey);
        if (StringUtils.isEmpty(token)){
            return false;
        }
        //token获取成功后 在redis中删除对应的token
        redisServiceUtils.delete(tokenKey);
        return true;
    }
}