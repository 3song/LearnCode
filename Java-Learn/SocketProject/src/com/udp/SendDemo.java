package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author 陈磊
 * @Description //TODO DatagramSocket : 用来发送和接收数据 是基于UDP协议的
 * UDP协议   不需要创建连接，不管是否接收都会发送，不安全
 * 端口号错误，数据可以正常发出，但是收不到
 * 接收端重复启动会包端口占用异常
 * @Date
 * @Param
 * @return
 **/
public class SendDemo {
    public static void main(String[] args) throws IOException {
        //创建发送端Socket对象
        DatagramSocket datagramSocket=new DatagramSocket();
        //创建数据并打包
        //DatagramPacket datagramPacket=new DatagramPacket(, );
        String data="hello,3SONG";
        byte[] bytes = data.getBytes();
        int length=bytes.length;
        InetAddress inetAddress=InetAddress.getByName("192.168.50.73");
        //DatagramPacket 打包
        DatagramPacket datagramPacket = new DatagramPacket(bytes, length,inetAddress,23456);
        //发送数据
//        datagramPacket.setData(bytes);
//        datagramPacket.setPort(23456);
//        datagramPacket.setAddress(InetAddress.getByName("192.168.50.73"));
        datagramSocket.send(datagramPacket);
        //释放资源
        datagramSocket.close();
    }
}
