package com;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
class ServerHandler extends ChannelHandlerAdapter{
    /*
    *
    * 当通道被调用时，执行此方法（获取到数据）
    */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String message= (String) msg;
        System.out.println("服务器端收到客户端内容："+message);
        ctx.writeAndFlush("OK");  //ctx.write()放到缓冲区中   不发送   ctx.flush()放到通道中发送
    }
}
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("服务器端启动..........");
        //1.创建两个线程池 1个负责接收客户端 1个进行数据传输
        NioEventLoopGroup loopGroup=new NioEventLoopGroup();
        NioEventLoopGroup cGroup=new NioEventLoopGroup();
        //2.创建辅助类
        ServerBootstrap serverBootstrap =new ServerBootstrap();
        serverBootstrap.group(loopGroup,cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024)
        //3.设置缓冲区与发送区大小
        .option(ChannelOption.SO_RCVBUF,32*1024).option(ChannelOption.SO_RCVBUF,32*1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * This method will be called once the {@link Channel} was registered. After the method returns this instance
                     * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
                     *
                     * @param ch the {@link Channel} which was registered.
                     * @throws Exception is thrown if an error occurs. In that case it will be handled by
                     *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
                     *                   the {@link Channel}.
                     */
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //返回结果String类型
                        ByteBuf byteBuf= Unpooled.copiedBuffer("-3songs".getBytes());
                        //ch.pipeline().addLast(new FixedLengthFrameDecoder(10));//定长
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        ChannelFuture channelFuture=serverBootstrap.bind(8080).sync();
        channelFuture.channel().closeFuture().sync();
        loopGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
//返回需要响应头

    }
}
