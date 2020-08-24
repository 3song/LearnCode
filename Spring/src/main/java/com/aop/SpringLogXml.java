package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

public class SpringLogXml {
    //Spring 通知
    //前置通知---执行方法前通知 后置通知----执行方法之后通知 异常通知-----程序异常时通知  环绕通知----执行方法之前之后一起通知

    //表示在执行add方法之前打印
    private static Logger logger=Logger.getLogger("info");
    public void beanBeforeLog(){
        logger.info("add方法之前的打印日志");
    }

    //表示在执行add方法之后打印
    public void beanAfterLog(){
        logger.info("add方法之后的打印日志");
    }

    public void beanRuntimeLog(){
        logger.info("add方法正在运行日志");
    }

    public void beanExceptionAfterLog(){
        logger.info("add方法出现异常日志");
    }
    //环绕通知
    public void beanAroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("add方法Before 通知");
        proceedingJoinPoint.proceed();//区别方法前后 可以用来判断权限 if没权限 不使用proceedingJoinPoint.proceed() 
        logger.info("add方法After 通知");
    }
}
