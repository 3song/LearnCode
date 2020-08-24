package com.lock.api;

public interface RedisLock {
    String getLock(Long acquireTimeOut,Long timeOut);
    void unLock();

}
