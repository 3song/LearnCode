package com.service;

import com.dao.UserDao;
import com.utils.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    //@Resource
    //private TransactionUtils transactionUtils;
    @Transactional //开启事务自动回滚
    public void add(){
        //int i=1/0;
        System.out.println("进入UserService add....................");
        String name="陈磊";
        Integer age=25;
        // TransactionStatus status = null;
            //status = transactionUtils.begin();
            userDao.add(name,age);
            int i=1/0;
            //transactionUtils.commit(status);//执行到这  才添加成功
        // transactionUtils.commit(status);// 方法在这事务是无效的
    }

    public void add02(){
        //int i=1/0;
        System.out.println("进入UserService add....................");
        String name="陈磊";
        Integer age=25;
        // TransactionStatus status = null;
        try {
            //status = transactionUtils.begin();
            userDao.add(name,age);
            int i=1/0;
            //transactionUtils.commit(status);//执行到这  才添加成功
        } catch (Exception e) {
            // transactionUtils.rollback(status); 使用声明式配置
            e.printStackTrace();
        }
        // transactionUtils.commit(status);// 方法在这事务是无效的
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("setUserDao...............");
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
