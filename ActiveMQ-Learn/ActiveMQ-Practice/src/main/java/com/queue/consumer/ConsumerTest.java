package com.queue.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

public class ConsumerTest {
    //如果生产者以事务形式提交消息，消费也是以事务方式接收消息。
    //1.第一次运行消费者，可以接收消息成功，但不会标记为已消费
    //2.第一次运行消费者，如果有新的消息继续发送，消费者如果以事务形式进行接收（消费者以commit（）方式接收），消息会被标记为已消费

    private static Logger logger=Logger.getLogger("info");
    //mq通讯地址
    private static String url="tcp://127.0.0.1:61616";
    //队列名称
    private static String queueName="my_queue";
    public static void main(String[] args) throws JMSException {
        logger.info("我是002");
        //1.先创建连接工厂
        ActiveMQConnectionFactory mqConnectionFactory=new ActiveMQConnectionFactory(url);
        //2.创建连接
        Connection connection = mqConnectionFactory.createConnection();
        //3.创建MQ会话 参数1.设置是否以事务方式提交 参数2.设置JMS消息方式 默认自动签收 Session.AUTO_ACKNOWLEDGE（false） Session.CLIENT_ACKNOWLEDGE 手动签收
        // 设置事务方式提交，必须给消息提交事务，保证了消息的可靠性
        connection.start();// 启动消息长链接
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //4.创建目标（消息队列） 队列为点对点方式
        Queue queue = session.createQueue(queueName);
        //5.创建消费者 Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        //6.开启监听 监听消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage= (TextMessage) message;
                    //手动签收需要通知消息中间件已经消费成功 注意，消息可以接收到但并不等于消费成功
                    textMessage.acknowledge();
                    logger.info("消费者消费生产者内容："+textMessage.getText());
                    session.commit();//  提交之后可签收消息 无需textMessage.acknowledge() 而且会拿到之前消息容器的所有消息
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 不需要关闭连接 ，会监听不到消息
        //connection.close();
        logger.info("接收消息成功");
    }
}
