package com.thread;

import java.util.logging.Logger;

public class ThreadDemoTwo implements Runnable {
    public static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) {
        ThreadDemoTwo threadDemoTwo=new ThreadDemoTwo();
        //创建一个Thread对象  并把实现了Runnable接口的对象传入
        Thread thread=new Thread(threadDemoTwo,"子线程名称");
        //调用start() 开启线程
        thread.start();
        for (int i = 0; i < 30; i++) {
            logger.info("执行 main 方法 获取i的值为 :"+i);
        }
    }

    public void run() {
        for (int i = 0; i <30 ; i++) {
            logger.info("执行 run 方法 获取i的值为 :"+i);
        }
    }
}
