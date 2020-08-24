package com.thread;

public class ThreadDemoSix {
    //join方法作用
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程开始执行 i 为："+i);
                }
            }
        });
        thread.start();
        //实现 主线程先允许子线程执行，子线程执行完毕后，主线程再开始执行 怎么做？
        thread.join();
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程开始执行 i 为："+i);
        }

    }
}
