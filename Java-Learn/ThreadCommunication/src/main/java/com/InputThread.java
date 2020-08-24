package com;
class Users {
    public String name;
    public Integer age;
    // 为true 表示生产者线程等待  消费者可以进行消费 false 表示生产者生产（写入），消费者不能进行消费（等待）
    public boolean flag=false;

}



public class InputThread extends Thread {
    Users users;
    public InputThread(Users users){
        this.users=users;
    }

    @Override
    public void run() {
        //写 奇数写3song 偶数写陈磊
        int count = 0;
        while (true){
            //用this锁不能解决 因为 this指向的InputThread 和 OutPutThread 锁不同步
            //可以用users来做锁
            synchronized (users){
                if(users.flag){
                    //表示消费者没读完
                    try {
                        users.wait(); //让当前线程从运行状态变为休眠状态，并且释放锁的资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count==0){
                    users.name="陈磊";
                    users.age=25;
                }else{
                    users.name="3song";
                    users.age=0;
                }
                //count+1对2取模 余数判断奇偶数
                //判断奇偶数公式
                count=(count+1)%2;
                users.flag=true;
                users.notify();
            }
        }
    }
}
class OutPutThread extends Thread{
    Users users;
    public OutPutThread(Users users){
        this.users=users;
    }
    @Override
    public void run() {
        while (true){
            //用this锁不能解决 因为 this指向的InputThread 和 OutPutThread 锁不同步
            //可以用users来做锁
            synchronized (users){
                if (!users.flag){
                    //表示生产者没写完，无法读取
                    try {
                        users.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(users.name+users.age);
                users.flag=false;
                users.notify();//表示唤醒当前wait的线程
            }
        }
    }
}
class OutThread{
    public static void main(String[] args) {
        Users users=new Users();
        InputThread inputThread=new InputThread(users);
        OutPutThread outPutThread=new OutPutThread(users);

        outPutThread.start();
        inputThread.start();
    }
}




