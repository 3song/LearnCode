package com.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author 陈磊
 * @Description //TODO TCP 必须要创建连接，效率低，但没有数据大小的限制
 * 1.创建发送端Socket对象（创建连接：设备ip，设备端口号）
 * 2.发送数据（通过IO输出流发送数据）
 * 3.关闭连接
 * @Date
 * @Param
 * @return
 **/
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建发送端Socket对象（创建连接：设备ip，设备端口号）
        InetAddress inetAddress=InetAddress.getByName("192.168.50.73");
        Socket socket=new Socket(inetAddress,34567);
        //创建输出流对象  需要获取Socket的OutputStream并写入
        OutputStream outputStream=socket.getOutputStream();
        String s="3SONG";
        //output写字节流byte
        //2.发送数据（通过IO输出流发送数据）
        outputStream.write(s.getBytes());
        // outputStream.close();
        //3.关闭连接
        socket.close();
    }

}
