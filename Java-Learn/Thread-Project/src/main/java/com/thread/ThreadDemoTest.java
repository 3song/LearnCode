package com.thread;

public class ThreadDemoTest {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("T1线程 i:"+i);
                }
            }
        });
        thread1.start();
        final Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("T2线程 i:"+i);
                }
            }
        });
        thread2.start();
        final Thread thread3 = new Thread(new Runnable() {
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("T3线程 i:"+i);
                }
            }
        });
        thread3.start();
        //3个线程 ，保证t2 在t1执行完执行，t3在t2 执行完执行




    }
}
