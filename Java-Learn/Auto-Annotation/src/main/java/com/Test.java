package com;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;


//表示注解的应用场景 表示应用在方法上、Type 表示应用在类上或者接口上使用、Field 表示在属性上使用 访问权限
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
//什么时候访问注解信息，用于描述注解的生命周期（在什么范围生效）
@Retention(RetentionPolicy.RUNTIME)
//定义一个注解类
@interface TestAnnotation{
    //表示注解的参数 表示可以不传参数 默认为0
    int beanId() default 0;
    String className() default "";
    String[] array(); //必须传值;
}
public class Test {
    //表示忽略代码过时警告
    @SuppressWarnings("deprecation")
    public void Date(){
        new Date().parse("");
    }
    //表示方法以过时
    @Deprecated
    @TestAnnotation(beanId = 1,className = "com",array = {"111","222"})
    public void delete(){

    }
}
