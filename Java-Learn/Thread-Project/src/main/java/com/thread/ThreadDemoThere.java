package com.thread;

public class ThreadDemoThere {
    abstract static class Parent{
        public abstract void add();
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    System.out.println("使用匿名内部类方式实现多线程 i的值为：" + i);
                }
            }
        });
        thread.start();
        //使用匿名内部类实现多线程
        for (int i = 0; i < 30; i++) {
            System.out.println("主线程线程 i的值为："+i);
        }
        //1.什么是匿名内部类 class {

        // }
        /*Parent parent = new Parent() {
            @Override
            public void add() {
                System.out.println("使用自定义内部类");
            }
        };*/



        //parent.add();
    }


}
