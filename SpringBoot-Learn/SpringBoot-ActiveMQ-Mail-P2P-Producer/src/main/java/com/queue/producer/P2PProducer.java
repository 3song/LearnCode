package com.queue.producer;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.logging.Logger;

@Component
public class P2PProducer {
    private static Logger logger=Logger.getLogger("info");
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource
    private Queue queue;
    //每隔5s时间向队列中发送消息
    @Scheduled(fixedDelay = 5000)
    public void send(){
        String msg=System.currentTimeMillis()+"";//获取当前时间戳
        jmsMessagingTemplate.convertAndSend(queue,msg);
        logger.info("采用点对点通讯模式发送消息, msg:"+msg);
    }
}
