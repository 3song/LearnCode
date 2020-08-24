package com.loader;

import com.entity.Users;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HotSwap {
    @Test
    public void main() throws InterruptedException {
        Users users=new Users();
        users.add();
        //将2.0Class 覆盖1.0Class
        Thread.sleep(10*1000);
        users.add();
    }

    @Test
    public void main1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        System.out.println("开始加载1.0版本");
        loadUser();
        //1.0class
        File file1=new File("E:\\IdeaProjects\\Java-Learn\\Java-Class\\target\\classes\\com\\entity\\Users.class");
        //2.0class
        File file2=new File("C:\\Users\\10482\\Desktop\\Users.class");
        boolean delete = file1.delete();//删除1.0版本文件
        if (!delete){
            System.out.println("热部署失败无法删除文件");
            return;
        }
        file2.renameTo(file1);// 移动Users2.0.class 到1.0 目录下
        Thread.sleep(10*1000);
        System.out.println("开始加载2.0版本");
        loadUser();
    }

    public static void loadUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader=new MyClassLoader();
        //使用类加载器读取Users信息
        Class<?> aClass = myClassLoader.findClass("com.entity.Users");
        //使用反射机制创建Users对象
        Object o = aClass.newInstance();
        Method add = aClass.getMethod("add");
        //使用反射机制调用方法
        add.invoke(o);
        System.out.println(o.getClass());
        System.out.println(o.getClass().getClassLoader());
    }
}
