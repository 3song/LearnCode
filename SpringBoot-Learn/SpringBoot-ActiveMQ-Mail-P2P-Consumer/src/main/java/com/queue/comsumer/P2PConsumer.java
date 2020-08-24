package com.queue.comsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class P2PConsumer {
    private static Logger logger=Logger.getLogger("info");
    //获取生产者队列 对象 可能会有幂等性问题
    @JmsListener(destination = "${my_queue}")
    public void receive(String msg){
        logger.info("消费者成功获取到生产者发送的消息 msg:"+msg);
    }
}
