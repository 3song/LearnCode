package com.thread;

public class ThreadDemoDead implements Runnable{
    private volatile static int count = 100 ;
    private static Object object = new Object();
    public boolean flag=true;
    public static byte[] bytes = new byte[100*1024*1024];
    @Override
    public void run() {
        if (flag) {
            while (count > 0){
                synchronized (object){ //拿到oj锁
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sale(); //this锁
                }
            }
        } else {
            while (count > 0){
                sale();//先拿this锁
            }
        }

    }

    private synchronized void sale() {
        synchronized (object){ //在拿oj锁
            if (count > 0){
                System.out.println(Thread.currentThread().getName()+",出售了"+(100-count+1)+"张票");
                count--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperties());
        ThreadDemoDead threadDemoDead=new ThreadDemoDead();
        Thread thread1=new Thread(threadDemoDead,"窗口1");
        Thread thread2=new Thread(threadDemoDead,"窗口2");
        thread1.start();
        Thread.sleep(40);
        threadDemoDead.flag=false;
        thread2.start();
    }


}
