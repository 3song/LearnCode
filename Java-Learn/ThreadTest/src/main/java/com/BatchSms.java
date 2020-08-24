package com;

import com.entity.Users;
import com.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

class UserThread implements Runnable{
    private List<Users> listUser;
    public UserThread(List<Users> usersList){
        this.listUser=usersList;
    }
    public void run() {
        for (Users users:listUser) {
            System.out.println("线程名称为："+Thread.currentThread().getName()+"==用户信息为："+users.toString());
        }
        System.out.println("------批次分割线-------");
    }
}

public class BatchSms {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //1.初始化数据 初始化10个数据
        initData();
        //2.定义每个线程分批发送的容量
        int count=2;
        //3.计算每个线程需要分批跑的数据
        List<List<Users>> lists = ListUtils.splitList(initData(), count);
        //4.分批发送
        for (int i = 0; i <lists.size() ; i++) {
            List<Users> usersList = lists.get(i);
            UserThread userThread = new UserThread(usersList);//分批发送
            Thread thread=new Thread(userThread,"线程"+i);
            thread.start();
        }
        //此处写要测试的代码
        long end = System.currentTimeMillis();
        System.out.println("共耗时"+(end-start)+"毫秒");
    }

    private static List<Users> initData(){
        ArrayList<Users> usersList = new ArrayList<Users>();
        for (int i = 0; i <= 10; i++) {
            usersList.add(new Users("userId:"+i,"usersName:"+i));
        }
        return usersList;
    }
}
