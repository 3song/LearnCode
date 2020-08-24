package com.communication;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadSign extends Thread {
    private Semaphore semaphore;

    public ThreadSign(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int i = semaphore.availablePermits();//获取当前剩余信号值
            if (i>0){
                System.out.println(getName()+"有剩余"+i+"个资源");
            }else{
                System.out.println(getName()+"没有剩余资源，请等待！");
            }
            try {
                semaphore.acquire(1); //此方法会把信号量减1 信号量小于0时（达到线程最大限度时） 其余的线程在此等待，直到其他线程释放资源才能继续使用
                //因为每个线程处理数据的逻辑不同（时间不同）
                System.out.println(getName()+"获取到新的资源");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println(getName()+"资源使用完毕，准备释放资源");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
    }

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(2);
        //5的意思是可同时支持五个线程操作  多出来的会阻塞 等待其他线程释放，才可使用
        for (int i = 0; i < 10; i++) {
            new ThreadSign(semaphore).start();
        }
    }
}
