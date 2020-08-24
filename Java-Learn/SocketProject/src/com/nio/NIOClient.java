package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

public class NIOClient {
    //非阻塞IO
    public static void main(String[] args) throws IOException {
        System.out.println("客户端正在启动");
        //1.创建Socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        //2.切换为异步非阻塞
        socketChannel.configureBlocking(false);
        //3.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new Date().toString().getBytes());
        //4.切换到读取模式
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        //5.关闭通道
        socketChannel.close();



    }
}
//NIO 服务器端
class NIOServer{
    public static void main(String[] args) throws IOException {
        System.out.println("服务器端被启动");
        //1.创建服务器端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.设置为异步
        serverSocketChannel.configureBlocking(false);
        //3.绑定连接
            serverSocketChannel.bind(new InetSocketAddress(8080));
        //4.获取选择器
        Selector selector = Selector.open();
        //5.将通道注册到选择器中，并监听已经接受到的事件 发送请求
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.轮询获取已经准备就绪的事件
        while (selector.select()>0){//表示有结果
            //7.获取当前选择器 有注册并监听到的时间
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()){
                //8.获取准备就绪的事件（数据）
                SelectionKey selectionKey = selectionKeyIterator.next();
                //9.判断事件准备就绪
                if (selectionKey.isAcceptable()){
                    //10.获取准备就绪的事件，获取客户端连接
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    //11.设置为异步非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12.将获取的选择器注册到服务器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    //13.获取数据准备完成的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14.读取数据
                    int len=0;
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    while ((len=socketChannel.read(byteBuffer))>0){//len 赋值并判断
                        //数据读取到
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }
                selectionKeyIterator.remove();
            }
        }

    }
}