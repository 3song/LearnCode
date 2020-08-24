package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author 陈磊
 * @Description //TODO
 * @Date 18:38 2020/6/2
 * @param FanOut
 * @return
 **/
@Component
public class FanOutConfig {
    //1.建立连接
    //2.创建管道
    //3.设立队列类型
    //邮件队列
    private static final String FANOUT_EMAIL_QUEUE="FANOUT_EMAIL_QUEUE";
    //短信队列
    private static final String FANOUT_SMS_QUEUE="FANOUT_SMS_QUEUE";
    //交换机名称
    private static final String FANOUT_EXCHANGE_QUEUE="FANOUT_EXCHANGE_QUEUE";
    //创建死信队列和交换机信息
    private static final String DEAD_QUEUE_NAME="DEAD_QUEUE";
    private static final String DEAD_ROUTING_KEY="DEAD_ROUTING_KEY";
    private static final String DEAD_EXCHANGE_NAME="DEAD_EXCHANGE_NAME";
    //创建交换机标识符
    private static final String DEAD_LETTER_QUEUE_KEY="x-dead-letter-exchange";
    //死信队列交换机绑定键标识符
    private static final String DEAD_LETTER_ROUTING_KEY="x-dead-letter-routing-key";


    //这段代码相当于<bean id="fanoutQueue" class="org.springframework.amqp.core.Queue" />
    //定义邮件队列
    @Bean
    public Queue fanoutEmailQueue(){

        // 正常代码return new Queue(FANOUT_EMAIL_QUEUE);
        // 定义邮件队列并绑定死信队列
        Map<String,Object> args=new HashMap<>(2);
        args.put(DEAD_LETTER_QUEUE_KEY,DEAD_EXCHANGE_NAME);
        args.put(DEAD_LETTER_ROUTING_KEY, DEAD_ROUTING_KEY);
        //死信队列绑定Email队列
        Queue queue = new Queue(FANOUT_EMAIL_QUEUE, true, false, false, args);
        return queue;
    }
    //定义短信队列
    @Bean
    public Queue fanoutSMSQueue(){
        return new Queue(FANOUT_SMS_QUEUE);
    }
    //4.定义扇形交换机 主题交换机 TopicExchange 直连交换机 DirectExchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE_QUEUE);
    }
    //5.交换机绑定队列
    //队列与交换机进行绑定，参数名称 fanoutEmailQueue fanoutExchange 一定要和交换机的方法名称一致  fanoutEmailQueue() fanoutSMSQueue()
    @Bean
    public Binding bindingExchangeEmail(Queue fanoutEmailQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }
    //邮件队列绑定死信交换机（备用交换机）
    //创建死信交换机
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(DEAD_EXCHANGE_NAME);
    }
    //创建死信队列
    @Bean
    public Queue deadQueue(){
        Queue queue=new Queue(DEAD_QUEUE_NAME,true);
        return queue;
    }
    // 死信交换机与死信队列绑定
    @Bean
    public Binding bindingDeadExchange(Queue deadQueue,DirectExchange deadExchange){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_ROUTING_KEY);
    }
}
