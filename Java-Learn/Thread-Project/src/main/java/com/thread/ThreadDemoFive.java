package com.thread;

public class ThreadDemoFive {
    public static void main(String[] args) {
        final Thread thread=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程开始执行，i 为："+i);
                }
            }
        });
        thread.setDaemon(true);//把该线程设置为守护线程，主线程停止，该线程一起销毁
        thread.start();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.currentThread().sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程开始执行，i 为："+i);
        }
        System.out.println("主线程执行完毕");
    }
}
