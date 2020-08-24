package com.communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadCyclicBarrier extends Thread{
    private  CyclicBarrier cyclicBarrier;

    public ThreadCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(getName()+"线程进入");
        try {
            Thread.sleep(1000);
            if(cyclicBarrier.getNumberWaiting()==4){
                System.out.println("人员到齐，开始执行所有线程");
            }
            cyclicBarrier.await();

            //cyclicBarrier.await();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new ThreadCyclicBarrier(cyclicBarrier).start();
        }
    }
}
