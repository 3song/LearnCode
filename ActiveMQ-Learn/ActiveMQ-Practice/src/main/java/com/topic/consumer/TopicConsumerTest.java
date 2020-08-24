package com.topic.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

public class TopicConsumerTest {
    private static Logger logger=Logger.getLogger("info");
    //mq通讯地址
    private static String url="tcp://127.0.0.1:61616";
    //队列名称
    private static String queueName="my_topic";
    public static void main(String[] args) throws JMSException {
        //1.先创建连接工厂
        ActiveMQConnectionFactory mqConnectionFactory=new ActiveMQConnectionFactory(url);
        //2.创建连接
        Connection connection = mqConnectionFactory.createConnection();
        //3.创建MQ会话 参数1.设置是否以事务方式提交 参数2.设置JMS消息方式 默认自动签收（false）
        connection.start();// 启动消息长链接
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目标（消息队列） 队列为点对点方式
        Topic topic = session.createTopic(queueName);
        //5.创建消费者 Consumer
        MessageConsumer consumer = session.createConsumer(topic);
        //6.开启监听 监听消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage= (TextMessage) message;
                    logger.info("消费者消费生产者内容："+textMessage.getText());
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
