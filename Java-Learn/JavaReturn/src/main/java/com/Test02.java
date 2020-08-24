package com;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        //通过反射机制访问类的私有对象和所有方法
        //通过反射机制获取类的所有属性
        Class<?> users = Class.forName("com.Users");
        Field[] declaredFields = users.getDeclaredFields();
        for (Field field:declaredFields){
            System.out.println(field.getName());
        }
        //通过java反射机制获取到类的所有方法
        Method[] declaredMethods = users.getDeclaredMethods();
        for (Method method:declaredMethods){
            System.out.println(method.getName());
        }
        //使用java反射机制给私有成员赋值
        //获取Users类的id属性
        Object o=users.newInstance();
        Field id = users.getDeclaredField("id");
        id.setAccessible(true);//私有成员赋值需要赋权 表示允许反射私有属性
        id.set(o,"004");
        Field name = users.getDeclaredField("name");
        name.setAccessible(true);//私有成员赋值需要赋权 表示允许反射私有属性
        name.set(o, "陈磊");
        Users user= (Users) o;
        System.out.println(user.toString());
    }
}
