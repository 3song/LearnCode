package com.thread;


import java.util.logging.Logger;

class CreateThreadDemo01 extends Thread{
    public static Logger logger=Logger.getLogger("info");
    
    /*
     * @Author 陈磊
     * @Description //TODO Run方法就是线程需要执行的任务或者执行的代码
     * @Date
     * @Param
     * @return
     **/
    @Override
    public void run() {
        for (int i = 0; i < 30 ; i++) {
            logger.info("执行run方法 i的值为："+i);
        }
        super.run();
    }
}

public class ThreadDemo extends Thread {
    /**
     * @Author 陈磊
     * @Description //TODO 什么是进程，进程就是正在运行的应用程序，进程是线程的集合
     * 什么是线程，线程是程序的一条执行路径，一个独立的执行单元。
     * 什么是多线程，在程序中创建多个线程，目的提高程序的执行效率。
     * 创建线程有哪些方式
     * 1.继承Thread类
     * 2.实现Runnable接口方式
     * 3.使用匿名内部类
     * 4.实现Callable接口,重写call()方法
     * 5.在企业开发中，使用线程池创建线程
     *      怎么调用线程?
     *      
     * @Date
     * @Param
     * @return
     **/
    public static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) {
        //1.怎么调用线程
        CreateThreadDemo01 createThreadDemo01=new CreateThreadDemo01();
        //2.启动线程 ，并不是要直接调用run()，调用run()是直接在单线程执行并不是多线程，而是调用start()
        //createThreadDemo01.run();//错误方式
        createThreadDemo01.start();
        for (int i = 0; i < 30; i++) {
            logger.info("执行 main 方法 获取i的值为 :"+i);
        }
        //获得的结果就是多线程的结果
        //开启多线程后，代码并不会从上往下执行，结果是交替执行的
    }
}
