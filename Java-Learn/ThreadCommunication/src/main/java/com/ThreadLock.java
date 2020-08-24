package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UsersLock {
    public String name;
    public Integer age;
    // 为true 表示生产者线程等待  消费者可以进行消费 false 表示生产者生产（写入），消费者不能进行消费（等待）
    public boolean flag = false;

    //定义Lock锁
    Lock lock=new ReentrantLock(); //重入锁、自旋锁、读写锁

}


class InputThreadLock extends Thread {
    UsersLock users;
    Condition condition;
    public InputThreadLock(UsersLock users,Condition condition) {
        this.users = users;
        this.condition=condition;
    }

    @Override
    public void run() {
        //写 奇数写3song 偶数写陈磊
        int count = 0;
        while (true) {
            //开始上锁
            users.lock.lock();
            if (users.flag){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (count == 0) {
                    users.name = "陈磊";
                    users.age = 25;
                } else {
                    users.name = "3song";
                    users.age = 0;
                }
                //count+1对2取模 余数判断奇偶数
                //判断奇偶数公式
                count = (count + 1) % 2;
                users.flag = true;
                condition.signal();//唤醒方法
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                users.lock.unlock();
            }
        }
    }
}

class OutPutThreadLock extends Thread {
    UsersLock users;
    Condition condition;
    public OutPutThreadLock(UsersLock users,Condition condition) {
        this.users = users;
        this.condition=condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                users.lock.lock();
                if (!users.flag){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(users.name + users.age);
                users.flag = false;
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                users.lock.unlock();
            }
        }
    }
}

public class ThreadLock extends Thread {
    public static void main(String[] args) {
        UsersLock users = new UsersLock();
        Condition condition=users.lock.newCondition();
        InputThreadLock inputThread = new InputThreadLock(users,condition);
        OutPutThreadLock outPutThread = new OutPutThreadLock(users,condition);

        outPutThread.start();
        inputThread.start();
    }
}