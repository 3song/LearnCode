package com.thread;

class ThreadTrain implements Runnable{
    //当一个方法被static修饰时会存放在永久区里面，当class被加载时就会初始化
    private static int trainCount=100;
    private Object object=new Object();//定义一个对象锁
    public boolean flag=true;
    public void run() {

            if (flag){
                while (trainCount>0) {
                    synchronized (ThreadTrain.class){
                        if(trainCount>0){
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // synchronized 中的内容只能同时允许一个线程操作，必须拿到锁的时候才能访问。
                            System.out.println(Thread.currentThread().getName()+"正在出售第"+(100-trainCount+1)+"张票");
                            trainCount--;
                        }
                    }
                }
            }else{
                while (trainCount>0) {
                    //sale02();//非静态同步锁函数
                    sale03();//静态同步锁函数

                }
            }
        }
//        while (trainCount>0){
//            /*try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }*/
//            sale01();
//        }



    private void sale01() {
        // synchronized 同步代码块 包裹可能出现线程安全的问题的代码块；\
        // 任何一个对象 都能作为锁，相当于密码
        synchronized (object){
            if(trainCount>0){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // synchronized 中的内容只能同时允许一个线程操作，必须拿到锁的时候才能访问。
                System.out.println(Thread.currentThread().getName()+"正在出售第"+(100-trainCount+1)+"张票");
                trainCount--;
            }
        }
    }
    private synchronized void sale02() {
        if(trainCount>0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // synchronized 中的内容只能同时允许一个线程操作，必须拿到锁的时候才能访问。
            System.out.println(Thread.currentThread().getName()+"正在出售第"+(100-trainCount+1)+"张票");
            trainCount--;
        }

    }

    private static synchronized void sale03() {
        if(trainCount>0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // synchronized 中的内容只能同时允许一个线程操作，必须拿到锁的时候才能访问。
            System.out.println(Thread.currentThread().getName()+"正在出售第"+(100-trainCount+1)+"张票");
            trainCount--;
        }

    }
}
public class ThreadDemoConcurrent {
    public static void main(String[] args) throws InterruptedException {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread thread1 = new Thread(threadTrain,"窗口①");
        Thread thread2 = new Thread(threadTrain,"窗口②");
        thread1.start();
        Thread.sleep(400);
        threadTrain.flag=false;
        thread2.start();
    }
}
