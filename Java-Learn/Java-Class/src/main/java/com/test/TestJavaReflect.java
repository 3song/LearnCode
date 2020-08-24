package com.test;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestJavaReflect {
    //实现1：1.使用字节码技术创建class文件 2.对一个类新增一个方法 3.动态执行新增的方法
    //操作字节码技术 asm JavaAssist
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //使用反射技术执行某个方法
        Class<?> aClass = Class.forName("com.test.TestJavaReflect");
        //  com.test.TestJavaClassUpdate 创建一个对象
        Object o = aClass.newInstance();
        //执行sum方法
        Method sumMethod = aClass.getDeclaredMethod("sum", int.class, int.class);
        //invoke表示执行方法 动态代理同时使用了这个方法
        sumMethod.invoke(o,1,1);

    }
    static public void sum(int a,int b){
        System.out.println("sum:"+(a+b));
    }
}
