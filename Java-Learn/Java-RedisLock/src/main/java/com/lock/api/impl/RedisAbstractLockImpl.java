package com.lock.api.impl;

import com.lock.api.RedisAbstractLock;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

public class RedisAbstractLockImpl extends RedisAbstractLock {
    //redis线程池
    protected JedisPool jedisPool;
    //相同的key值
    private String lockKey="redis_lock";
    /**
     * Redis 实现分布式锁有两个超时问题
     * 1.在获取锁之前的超时问题：当尝试获取锁时，如果在规定的时间还没有获取锁，就直接放弃。
     * 2.在获取锁之后的超时问题：当获取锁成功时，对应的key 有对应有效期，对应的key 在规定时间后进行失效
     **/
    /*
     * @Author 陈磊
     * @Description //TODO
     * @Date 19:11 2020/5/30
     * @param acquireTimeOut 在获取锁之前的超时时间
	 * @param timeOut 在获取锁之后的超时时间
     * @return void
     **/
    @Override
    public String getLock(Long acquireTimeOut,Long timeOut) {
        //1.建立redis连接
        Jedis jedisConnect=null;
        try {
            jedisConnect = jedisPool.getResource();
            //2.定义Redis对应key的value值（锁的id）UUID 释放锁时需要释放对应锁
            String value= UUID.randomUUID().toString();
            //3.定义获取锁之前的超时时间
            Long expireLock=timeOut / 1000;
            //4.定义获取锁之后的超时时间
            //5.使用循环机制，保证重复进行尝试获取锁（乐观锁）
            Long endTime=System.currentTimeMillis()+acquireTimeOut;//表示预期结束时间
            while (endTime>System.currentTimeMillis()){//未超时
                //获取锁
                //6.使用setNx命令插入对应的RedisKey，如果返回值为1 代表成功获取锁
                if (jedisConnect.setnx(lockKey, value)==1){
                    //设置对应key的有效期
                    jedisConnect.expire(lockKey, Math.toIntExact(expireLock));
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisConnect!=null)
                jedisConnect.close();
        }
        //高并发运行到这里表示获取不到锁
        return null;
    }

    @Override
    public void unLock() {

    }


    @Override
    public void waitLock() {

    }
}
