package com;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerHandler extends SimpleChannelHandler{
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
        System.out.println("服务器端获取客户端发来的参数"+e.getMessage());
        ctx.getChannel().write("你好！");
    }
}
//NettyServer 服务器端
public class NettyServer {
    public static void main(String[] args) {
        //1.创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2.创建2个线程池
        //作用 第一个线程池 用来监听端口号 第二个用来监听NIO
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();
        //3.将线程池放入到工程中
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, work));
        //4.设置管道工程
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                //设置管道
                ChannelPipeline pipeline= Channels.pipeline();
                //数据类型
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                //设置事件监听类
                pipeline.addLast("serverHandler",new ServerHandler());
                return pipeline;
            }
        });
        //绑定端口号
        serverBootstrap.bind(new InetSocketAddress("127.0.0.1",8080));
        System.out.println("Netty服务器端已经被启动");
    }
}
