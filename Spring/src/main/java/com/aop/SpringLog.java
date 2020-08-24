package com.aop;

import com.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Aspect
@Component
public class SpringLog {
    @Resource
    private TransactionUtils transactionUtils;
    //Spring 通知
    //前置通知---执行方法前通知 后置通知----执行方法之后通知 异常通知-----程序异常时通知  环绕通知----执行方法之前之后一起通知
    private TransactionStatus status;

    //表示在执行add方法之前打印
    private static Logger logger=Logger.getLogger("info");
    @Before("execution(* com.service.UserService.add(..))")
    public void beanBeforeLog(){
        logger.info("add方法之前的打印日志");
    }

    //表示在执行add方法之后打印
    @After("execution(* com.service.UserService.add(..))")
    public void beanAfterLog(){
        logger.info("add方法之后的打印日志");
    }

    //
    @AfterReturning("execution(* com.service.UserService.add(..))")
    public void beanRuntimeLog(){
        logger.info("add方法正在运行日志");
    }

    @AfterThrowing("execution(* com.service.UserService.add(..))")
    public void beanExceptionAfterLog(){
        //transactionUtils.rollback(status);
        logger.info("add方法出现异常日志");
    }
    //环绕通知
    @Around("execution(* com.service.UserService.add(..))")
    public void beanAroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //status = transactionUtils.begin();
        logger.info("add方法Before 通知");
        //放行方法  不加这个就不会执行对应方法
            proceedingJoinPoint.proceed();//区别方法前后 可以用来判断权限 if没权限 不使用proceedingJoinPoint.proceed()
        //transactionUtils.commit(status);
        logger.info("add方法After 通知");
    }
}
