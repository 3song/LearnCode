package com.test;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestJavaClassUpdate {
    //实现1：1.使用字节码技术创建class文件 2.对一个类新增一个方法 3.动态执行新增的方法
    //操作字节码技术 asm JavaAssist
    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            //读取Users类
            CtClass usersClass = pool.get("com.entity.Users");
            // CtMethod.make() 也能做到
            CtMethod sumMethod = new CtMethod(CtClass.voidType, "sum",new CtClass[]{CtClass.intType,CtClass.intType},usersClass);
            //CtMethod toString = new CtMethod(CtClass.voidType, "toString",new CtClass[]{}, usersClass);
            //添加sumMethod 方法 $0 表示this
            sumMethod.setBody("{System.out.println(\"sum:\"+($1+$2));}");
            //添加方法
            usersClass.addMethod(sumMethod);
            //生成Class文件
            usersClass.writeFile("E:/IdeaProjects/Java-Learn/Java-Class/src/main/resources");
            //动态执行生成的字节码的方法
            Class aClass=usersClass.toClass();
            Object o= aClass.newInstance();
            Method method=aClass.getDeclaredMethod("sum", int.class,int.class);
            method.invoke(o, 9,9);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static public void sum(int a,int b){
        System.out.println("sum:"+(a+b));
    }
}
