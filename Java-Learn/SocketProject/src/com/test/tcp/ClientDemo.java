package com.test.tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientDemo {
    private static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) throws IOException {
        //创建Socket对象
        Socket socket=new Socket("192.168.50.73", 34567);

        String s="3SONGS,IS GOOD";
        //创建输出流对象 创建socket的output对象 ，需要监听绑定啊
        OutputStream outputStream=socket.getOutputStream();
        outputStream.write(s.getBytes());
        //需要重新创建Sock连接防止端口占用
       // ServerSocket serverSocket=new ServerSocket(34567);
        logger.info("发送数据成功，正等待服务端返回数据..................................");
       // socket=serverSocket.accept();
        byte[] bytes=new byte[1024];

        InputStream inputStream=socket.getInputStream();
        inputStream.read(bytes);
        logger.info("接收返回数据成功，数据为："+new String(bytes));
        outputStream.close();
        //socket.close();



    }
}
