package com;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTask {
    public static void main(String[] args) {
        // Thread 方式
//        while (true){
//            try {
//                Thread.sleep(1000);
//                System.out.println("每一秒执行一次");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        // TimerTask 方式实现定时任务
//        TimerTask timerTask = new TimerTask() {
//            /**
//             * The action to be performed by this timer task.
//             */
//            @Override
//            public void run() {
//                System.out.println("TimerTask 方式每一秒执行一次");
//            }
//        };
//        //定义定时规则
//        Timer timer=new Timer();
//        //定义天数
//        long delay=0;
//        //定义秒数
//        long period=1000;
//        timer.scheduleAtFixedRate(timerTask, delay, period);
        //使用线程池方式 单线程
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = new Runnable() {

            /**
             * When an object implementing interface <code>Runnable</code> is used
             * to create a thread, starting the thread causes the object's
             * <code>run</code> method to be called in that separately executing
             * thread.
             * <p>
             * The general contract of the method <code>run</code> is that it may
             * take any action whatsoever.
             *
             * @see Thread#run()
             */
            @Override
            public void run() {
                System.out.println(" newSingleThreadScheduledExecutor 方式：每隔一秒执行一次");
            }
        };
        //第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        scheduledExecutorService.scheduleAtFixedRate(runnable,1,1,TimeUnit.SECONDS);

    }
}
