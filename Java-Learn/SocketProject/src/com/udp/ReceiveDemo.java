package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

/**
 * @Author 陈磊
 * @Description //TODO使用UDP接收资源
 * 1.创建接收端Socket对象
 * 2.接收资源
 * 3.解析数据
 * 4.输出数据
 * 5.释放资源
 * @Date
 * @Param
 * @return
 **/
public class ReceiveDemo {
    private static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) throws IOException {
        //需要在此指定ip地址，指定接收端从哪里接收
        DatagramSocket datagramSocket=new DatagramSocket(23456);
        //bytes 是一个容器 初始化DatagramPacket 时会存储到bytes 数组中
        byte[] bytes=new byte[1024];
        DatagramPacket datagramPacket=new DatagramPacket(bytes,bytes.length);
        logger.info("正在等待数据传输......................");
        datagramSocket.receive(datagramPacket);//在此会堵塞 等待数据发送
        logger.info("数据接收成功......................");

        InetAddress address = datagramPacket.getAddress();
        logger.info("获取发送端address："+address.getHostAddress());
        //byte数组 需要String接收
        //logger.info("获取发送端data："+new String(datagramPacket.getData()));
        logger.info("获取发送端data："+new String(bytes));
        datagramSocket.close();

    }
}
