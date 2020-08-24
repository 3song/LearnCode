package com.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
//定义一个切面
@Component
//表示把这个类注册到SpringBoot容器中去
public class WebLobAspect {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.controller.*.*(..))")
    //定义一个切入点   从 controller开始拦截
    public void WebLog(){

    }
    @Before("WebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //接收到WebLog()请求，纪录请求内容
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        logger.info("#################################拦截到请求   请求开始############################################");
        //在此纪录请求内容
        logger.info("URL:"+request.getRequestURI().toString());
        logger.info("HTTP_METHOD :"+request.getMethod());
        logger.info("IP:"+request.getRemoteAddr());
        //这是在干吗？？？
        Enumeration<String> emu = request.getParameterNames();
        while (emu.hasMoreElements()){
            //为什么要强转
            String name=emu.nextElement();
            logger.info("name:{},value:{}",name,request.getParameter(name));
        }
    }
    @AfterReturning(returning = "ret",pointcut = "WebLog()")
    public void doAfterReturn(Object ret) throws Throwable{
        //处理完请求，返回内容
        logger.info("RESPONSE:"+ret);
        logger.info("#################################拦截到请求   请求结束############################################");
    }

}
