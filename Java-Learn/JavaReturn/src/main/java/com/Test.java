package com;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //ArrayList arrayList=new ArrayList();

        Class<?> users = Class.forName("com.Users");
        //使用JAVA反射机制 创建类的对象：默认使用无参构造创建arrayList 对象必须声明public
        Users user = (Users) users.newInstance();
        user.toString();
        //使用JAVA反射机制 创建类的对象：使用有参构造
        Constructor<?> constructor = users.getConstructor(String.class,String.class);
        // constructor.newInstance(5); 给构造参数传值，可以传多个值，用逗号隔开 arrayList.getConstructor(int.class,String.class);
        Users user1 = (Users) constructor.newInstance("陈磊","001");
        System.out.println(user1.toString());



    }
}
