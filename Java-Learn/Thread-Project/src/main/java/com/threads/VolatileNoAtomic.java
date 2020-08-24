package com.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {
    //现在没有共享count，只是声明了10次变量，10个线程分别修改这10个变量 加了静态static才会共享  static修饰关键字，在所有的线程都会共享。
    //被static修饰的变量，都会存在静态区里面，而且只会存放一次。因为只存放一次，所以实现了变量的线程间共享
    //private volatile static int count=0;
    // 此类是JDK1.5并发包 ****内的atomic类，表示原子类，实现了同步保证了线程安全
    private static AtomicInteger count=new AtomicInteger(0);
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            //count++;
            count.incrementAndGet();//表示count++
        }
        //System.out.println(getName()+","+count);
        System.out.println(getName()+","+count.get());
    }

    public static void main(String[] args) {
        //创建10个继承了Thread的VolatileNoAtomic对象，相当于创建10个线程,10个线程共享count值
        VolatileNoAtomic[] volatileNoAtomics=new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomics.length; i++) {
            //创建10个线程
            volatileNoAtomics[i]=new VolatileNoAtomic();
        }
//        for (VolatileNoAtomic volatileNoAtomic :volatileNoAtomics){ 按顺序遍历
//            volatileNoAtomic.start();
//        }
        for (int i = 0; i < volatileNoAtomics.length; i++) {
            //在此有可能出现线程安全问题   volatile 关键字也不能保证线程安全问题
            volatileNoAtomics[i].start();
        }

    }
}
