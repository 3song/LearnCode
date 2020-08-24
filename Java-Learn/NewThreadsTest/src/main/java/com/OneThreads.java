package com;

import javax.security.auth.callback.Callback;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OneThreads{
    public static void main(String[] args) {
        //1.可缓存的线程池 优点可重复利用 不会创建100个线程
        /*ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <100; i++) {
            //匿名内部类想要使用外部变量  需要加final关键字修饰
            final int temp=i;
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("threadName:"+Thread.currentThread().getName()+"i:"+temp);
                }
            });
        }*/
        //2.可定义长度的线程池
        /*ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int temp=i;
            newFixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("threadName:"+Thread.currentThread().getName()+"i:"+temp);
                }
            });
        }*/
        //3.可定义长度并可定时执行的线程池
        /*ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        *//*scheduledExecutorService.execute(new Runnable() {
            public void run() {
                System.out.println("此方法无法定时");
            }
        });*//*
        //表示定时3秒后在执行线程
        for (int i = 0; i < 10; i++) {
            final int temp=i;
            scheduledExecutorService.schedule(new Runnable() {
                public void run() {
                    System.out.println("threadName:"+Thread.currentThread().getName()+"i:"+temp);
                }
            }, 3, TimeUnit.SECONDS);
        }*/
        //只使用一个线程执行 单线程线程池无法自动停止 会等待线程执行完毕在执行
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int temp=i;
            newSingleThreadExecutor.execute(new Runnable() {
                public void run() {
                    System.out.println("threadName:"+Thread.currentThread().getName()+"i:"+temp);
                }
            });
        }
        newSingleThreadExecutor.shutdown();



    }

}
