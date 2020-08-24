package com.thread;
class ThreadDemo04 extends Thread{
    @Override
    public void run() {
        //getId()、getName() 是继承Thread类 获得的父类方法
        //进程id 是不会重复的，具有唯一性
        for (int i = 0; i < 10; i++) {
            try {
                //每1000毫秒 运行一次
                Thread.sleep(1000);

                //System.out.println("线程id为："+getId()+"线程名称为："+getName());
                System.out.println("线程id为："+Thread.currentThread().getId()+"线程名称为："+Thread.currentThread().getName()+"i 为:"+i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i==5){
                Thread.currentThread().stop();
            }
        }

    }
}

public class ThreadDemoFour {
    public static void main(String[] args) {
        //获取主线程id
        //任何一个程序都会有一个主线程
        System.out.println("主线程id为："+Thread.currentThread().getId()+"主线程名称为："+Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            //调用start开启多线程 ，需要每次都实例化
            ThreadDemo04 threadDemo04 = new ThreadDemo04();
            threadDemo04.start();
        }
        // Thread.currentThread() 获取当前线程的对象
        Thread.currentThread().stop(); //不安全，以弃用 代码未执行完，直接中断，不管代码运行结果
    }
}
