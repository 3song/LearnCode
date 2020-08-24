package com.utils;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

//事务工具类
@Component
public class TransactionUtils {
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin(){
        System.out.println("开启事务");
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());//DefaultTransactionDefinition() 参数是 事务隔离级别
        return transaction;
    }
    //提交事务
    public void commit(TransactionStatus transaction){
        System.out.println("提交事务");
        dataSourceTransactionManager.commit(transaction);
    }
    //出错回滚事务
    public void rollback(TransactionStatus transaction){
        System.out.println("回滚事务");
        dataSourceTransactionManager.rollback(transaction);
    }
}
