package com.lock.api;

public abstract class RedisAbstractLock implements RedisLock {
    @Override
    public String getLock(Long acquireTimeOut,Long timeOut) {
        return getLock(acquireTimeOut,timeOut);
    }

    @Override
    public void unLock() {

    }

    // 等待锁
    public abstract void waitLock();
}
