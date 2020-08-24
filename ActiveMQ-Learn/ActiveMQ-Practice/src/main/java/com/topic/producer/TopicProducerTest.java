package com.topic.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

public class TopicProducerTest {
    private static Logger logger=Logger.getLogger("info");
    //mq通讯地址
    private static String url="tcp://127.0.0.1:61616";
    //队列名称
    private static String queueName="my_topic";
    public static void main(String[] args) throws JMSException {
        //1.先创建连接工厂
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2.创建连接
        Connection connection = factory.createConnection();
        //3.创建MQ会话 参数1.设置是否以事务方式提交 参数2.设置JMS消息方式 默认自动签收（false）
        connection.start();// 启动消息长链接
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目标（消息队列） 队列为点对点方式
        Topic topic = session.createTopic(queueName);
        //5.创建生产者
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i <= 10; i++) {
            //6.创建消息
            TextMessage textMessage = session.createTextMessage("消息内容 i：" + i);
            //7.发送消息
            producer.send(textMessage);
        }
        //8.关闭连接
        connection.close();
        logger.info("发送消息成功,关闭连接");
    }
}
