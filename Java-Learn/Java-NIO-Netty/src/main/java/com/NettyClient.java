package com;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler extends SimpleChannelHandler {
    //通道关闭时触发
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }

    //必须建立连接，关闭通道的时候触发
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
        System.out.println("channelConnected");
    }

    //接受数据出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    //接收客户端数据
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("messageReceived");
        System.out.println("客户端获取服务器端返回的参数"+e.getMessage());

    }
}

public class NettyClient {
    public static void main(String[] args) {
        //1.创建服务对象
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        //2.创建2个线程池
        //作用 第一个线程池 用来监听端口号 第二个用来监听NIO
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();
        //3.将线程池放入到工程中
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos, work));
        //4.设置管道工程
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                //设置管道
                ChannelPipeline pipeline= Channels.pipeline();
                //数据类型
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                //设置事件监听类
                pipeline.addLast("clientHandler",new ClientHandler());
                return pipeline;
            }
        });
        //绑定端口号
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1",8080));
        System.out.println("Netty客户端已经被启动");
        Channel channel = connect.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println("请输入内容");
            channel.write(scanner.next());
        }


    }
}
