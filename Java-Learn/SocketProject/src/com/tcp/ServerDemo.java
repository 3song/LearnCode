package com.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * @Author 陈磊
 * @Description //TODO
 * 1.创建接收端ServiceSocket对象（需要明确端口号）
 * 2.监听客户端
 * 3.接收数据（使用IO输入流接收对象）
 * 4.释放资源
 * @Date
 * @Param
 * @return
 **/
public class ServerDemo {
    private static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) throws IOException {
        try {
            //1.创建接收端ServiceSocket对象（需要明确端口号）
            ServerSocket serverSocket = new ServerSocket(34567);
            //监听Socket对象并创建Socket连接
            logger.info("正在等待客户端发送数据....................");
            Socket socket=serverSocket.accept();
            logger.info("已接收到数据，客户端发送数据成功....................");
            //接收socket的输出流
            InputStream inputStream=socket.getInputStream();
            //获取数据
            InetAddress inetAddress=socket.getInetAddress();
            byte[] s=new byte[1024];
            inputStream.read(s);
            logger.info("客户端发送数据为："+new String(s).toLowerCase());
            logger.info("客户端发送数据转换小写为："+new String(s).toLowerCase());
            logger.info("客户端ip为："+inetAddress.getHostAddress());
            logger.info("客户端名称为："+inetAddress.getHostName());
            inputStream.close();
            //服务端一般不用关闭连接
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
