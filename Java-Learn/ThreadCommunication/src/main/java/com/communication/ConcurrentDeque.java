package com.communication;

import java.util.concurrent.*;

public class ConcurrentDeque {
    public static void main(String[] args) throws InterruptedException {
        //无界限队列 不允许null值
        ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer("张三");
        //concurrentLinkedQueue.offer(null); 报错
        concurrentLinkedQueue.offer("李四");
        concurrentLinkedQueue.offer("王五");
        System.out.println("concurrentLinkedQueue一共有"+concurrentLinkedQueue.size()+"个值");
        System.out.println("第一个值为"+concurrentLinkedQueue.peek());
        System.out.println("第二个值为"+concurrentLinkedQueue.peek());
        System.out.println("第三个值为"+concurrentLinkedQueue.peek());
        System.out.println("执行peek方法后，剩余"+concurrentLinkedQueue.size()+"个值");
        System.out.println("第一个值为"+concurrentLinkedQueue.poll());
        System.out.println("第二个值为"+concurrentLinkedQueue.poll());
        System.out.println("第三个值为"+concurrentLinkedQueue.poll());
        System.out.println("执行poll方法后，剩余"+concurrentLinkedQueue.size()+"个值");
        //有界限队列
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(5);
        arrayBlockingQueue.add("张三");
        //concurrentLinkedQueue.offer(null); 报错
        arrayBlockingQueue.add("李四");
        arrayBlockingQueue.add("王五");
        //offer是添加阻塞队列的方法   可等待   2秒之后添加  result表示添加成功或失败
        boolean result = arrayBlockingQueue.offer("3song", 2, TimeUnit.SECONDS);
        System.out.println("第一个值1为"+arrayBlockingQueue.poll());
        System.out.println("第二个值1为"+arrayBlockingQueue.poll());
        System.out.println("第三个值1为"+arrayBlockingQueue.poll());
        System.out.println("第四个值1为"+arrayBlockingQueue.poll());
    }
}
