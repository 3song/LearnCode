package com.test.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerDemo {
    private static Logger logger=Logger.getLogger("info");
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(34567);
        logger.info("正在等待客户端发送数据............................");
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        byte[] bytes=new byte[1024];
        inputStream.read(bytes);
        String s = new String(bytes);
        logger.info("获取到的对象为："+s);
        byte[] bytesLower = s.toLowerCase().getBytes();
        //转换对象 并返回客户端 需要重新创建连接
        //不需要重新开启链接
        //Socket socket1=new Socket("192.168.50.73",34568);
        OutputStream outputStream=socket.getOutputStream();
        //把转换的值   重新写回去
        outputStream.write(bytesLower);
        logger.info("转换成功，发送给客户端数据："+new String(bytesLower));
        outputStream.close();

    }
}
