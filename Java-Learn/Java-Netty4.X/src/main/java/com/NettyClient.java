package com;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

class ClientHandler extends ChannelHandlerAdapter {
    /*
     *
     * 当通道被调用时，执行此方法（获取到数据）
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        String message= (String) msg;
        System.out.println("客户端收到内容"+message);
    }
}
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("客户端启动..........");
        //1.创建两个线程池 1个负责接收客户端 1个进行数据传输
        NioEventLoopGroup lGroup=new NioEventLoopGroup();

        //2.创建辅助类
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(lGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            /**
             * This method will be called once the {@link Channel} was registered. After the method returns this instance
             * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
             *
             * @param ch the {@link Channel} which was registered.
             * @throws Exception is thrown if an error occurs. In that case the {@link Channel} will be closed.
             */
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ByteBuf byteBuf=Unpooled.copiedBuffer("-3songs".getBytes());
                //ch.pipeline().addLast(new FixedLengthFrameDecoder(10));//定长
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new ServerHandler());
            }
        });
        ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",8080).sync();
        //channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("3song".getBytes()));
        for (int i = 0; i < 5; i++) {
            channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("3song-3songs".getBytes()));
        }
        //channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("3song".getBytes()));
        //等待客户端端口号关闭
        channelFuture.channel().closeFuture().sync();
        lGroup.shutdownGracefully();

    }
}
