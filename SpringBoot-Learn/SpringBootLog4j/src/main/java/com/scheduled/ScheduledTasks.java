package com.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Scheduled(fixedRate = 1000)
    //表示一秒打印一次
    public void Test(){
        logger.info("当前正每隔一秒打印一次");
    }

}
