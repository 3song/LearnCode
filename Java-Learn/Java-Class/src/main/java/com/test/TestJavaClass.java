package com.test;

import javassist.*;

import java.io.IOException;

public class TestJavaClass {
    //实现1：1.使用字节码技术创建class文件 2.对一个类新增一个方法 3.动态执行新增的方法
    //操作字节码技术 asm JavaAssist
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //1.创建一个Users类
        CtClass usersClass = pool.makeClass("com.entity.Users");
        //2.创建一个name属性
        CtField name = CtField.make("private String name;", usersClass);
        CtField age = CtField.make("private Integer age;", usersClass);
        //3.给创建的类添加一个属性
        usersClass.addField(name);
        usersClass.addField(age);
        //4.创建方法
        CtMethod nameGet = CtMethod.make("public String getName(){return name;}", usersClass);
        CtMethod nameSet = CtMethod.make("public void setName(String name) {this.name = name;}", usersClass);
        CtMethod ageGet = CtMethod.make("public Integer getAge(){return age;}", usersClass);
        CtMethod ageSet = CtMethod.make("public void setAge(Integer age){this.age = age;}", usersClass);
        //5.添加方法
        usersClass.addMethod(nameGet);
        usersClass.addMethod(nameSet);
        usersClass.addMethod(ageGet);
        usersClass.addMethod(ageSet);
        //6.添加构造方法
        //创建两个参数的构造函数
        CtConstructor usersConstructors = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), pool.get("java.lang.Integer")}, usersClass);
        CtConstructor usersConstructor = new CtConstructor(new CtClass[]{}, usersClass);
        usersConstructors.setBody("{this.name = name;this.age = age;}");
        usersConstructor.setBody("{}");
        //添加构造方法
        usersClass.addConstructor(usersConstructors);
        usersClass.addConstructor(usersConstructor);
        //生成Class文件
        usersClass.writeFile("E:/IdeaProjects/Java-Learn/Java-Class/src/main/resources");

    }
}
