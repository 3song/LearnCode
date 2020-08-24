package com.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.logging.Logger;

@Component
public class ConfigQueue {
    //获取自定义yml属性值
    @Value("${my_queue}")
    private String my_queue;
    //1 . 首先将队列注入到springboot 容器中
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(my_queue);
    }

}
