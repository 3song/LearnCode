package com.pay.dao;


import com.pay.mapper.PayMapper;
import com.pojo.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor=Exception.class)
public class PayService {

    @Resource
    private PayMapper payMapper;

    @Transactional(rollbackFor=Exception.class)
    public int insertUser(Users users) {
        System.out.println("进入PayService");
        return payMapper.insert(users);
    }
}