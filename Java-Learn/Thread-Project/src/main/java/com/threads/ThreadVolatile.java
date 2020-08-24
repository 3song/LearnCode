package com.threads;

 class ThreadVolatileDemo extends Thread {
    public volatile boolean flag=true;//加了volatile 关键字，主内存中修改了flag的值，才能刷新到run方法中去
    @Override
    public void run() {
        super.run();//开启线程
        System.out.println("子线程开始执行.....");
        while (flag){

        }
        System.out.println("子线程执行结束.....");
    }
    public void setFlag(boolean flag){
        this.flag=flag;
    }
}
public class ThreadVolatile  {
    public static void main(String[] args) {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        try {
            //处于sleep状态时，主内存修改值，由于主线程在等待，并不会及时通知到本地内存刷新flag值。
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 在这里修改false ，只是主线程在当前的主内存中修改了值，在run方法内的本地内存副本没有刷新修改后的值，所以子线程一直无法结束，需要把flag用volatile修饰
        threadVolatileDemo.setFlag(false);
        System.out.println("flag已经修改为false！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("flag:"+threadVolatileDemo.flag);
    }
}
