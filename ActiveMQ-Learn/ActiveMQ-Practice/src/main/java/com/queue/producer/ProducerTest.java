package com.queue.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

public class ProducerTest {
    private static Logger logger=Logger.getLogger("info");
    //mq通讯地址
    private static String url="tcp://127.0.0.1:61616";
    //队列名称
    private static String queueName="my_queue";
    public static void main(String[] args) throws JMSException {
        //1.先创建连接工厂
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.创建MQ会话 参数1.设置是否以事务方式提交 参数2.设置JMS消息方式 默认自动签收（false） Session.AUTO_ACKNOWLEDGE
        // // 设置事务方式提交，必须给消息提交事务，保证了消息的可靠性
        connection.start();// 启动消息长链接
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //4.创建目标（消息队列） 队列为点对点方式
        Queue queue = session.createQueue(queueName);
        //5.创建生产者
        MessageProducer producer = session.createProducer(queue);
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//表示不会持久化
        //producer.setDeliveryMode(DeliveryMode.PERSISTENT); //表示会持久化消息  Activemq在当前版本下默认支持持久化
        for (int i = 0; i <= 10; i++) {
            //6.创建消息
            TextMessage textMessage = session.createTextMessage("消息内容 i：" + i);
            //7.发送消息
            producer.send(textMessage);
            // 以事务方式提交 必须要提交事务 如果未提交事务  消息并不会成功发送 消息不会挂起
            session.commit();
        }
        //8.关闭连接
        connection.close();
        logger.info("发送消息成功,关闭连接");
    }
}
