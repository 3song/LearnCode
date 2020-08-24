package com.ehcache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//默认单例
@Component
public class MyCache<K,V> {
    //手写Cache
    //Cache容器 ConcurrentHashMap使用分段锁 实现有效期
    public Map<K,V> concurrentHashMap=new ConcurrentHashMap<K, V>();

    public void put(K k,V v){
        concurrentHashMap.put(k, v);
    }

    public V get(K k){
        return concurrentHashMap.get(k);
    }

    public void remove(K k){
        concurrentHashMap.remove(k);
    }
}
