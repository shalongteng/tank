package com.slt.myTest.netTank.tank03.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 服务端
 */
public class Server {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) {
        new Server().serverStart();
    }
    public void serverStart(){
        //这两个group相当于线程池，bossGroup相当于selector负责 连接。
        // workerGroup 负责处理连接以后的逻辑处理
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);

        //服务端启动配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //快捷键 Ctrl + alt + T
        try {
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //当每一个客户端连上来以后给它一个监听器 都看作是自己的一个孩子
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //每连接一个客户端，初始化一个channel，调用一次
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("ch: " + ch);
                            //监听器处理过程就是 加一个对通道的处理器
                            //pipeline管道上加自己的处理器，类似责任链
                            ch.pipeline().addLast(new TankMsgDecoder())
                                    .addLast(new ServerChildHandler());

                        }
                    })
                    .bind(8666)
                    .sync();

            ServerFrame.INSTANCE.updateServerMsg("server started!");

            //如果没有人调用close方法closeFuture会一直等待。如果调用了close才会继续往下执行。
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel is atcived");
        //初始化channel的时候 吧channel 放到channelGroup中
        Server.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        try {
            TankMsg tm = (TankMsg)msg;

            System.out.println(tm);
            ctx.writeAndFlush(msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
        /*

        ByteBuf byteBuf = (ByteBuf) msg;
        //这种情况输出的PooledUnsafeDirectByteBuf(ridx: 0, widx: 10, cap: 1024)
//        System.out.println(byteBuf.toString());
        System.out.println("server: channel read：" + byteBuf.toString(CharsetUtil.UTF_8));

        //读到数据以后，写会给客户端。模拟通信
        ctx.writeAndFlush(byteBuf);
//        ctx.close();

         */


//        Server.channelGroup.writeAndFlush(msg);

    }

    @Override
    @SuppressWarnings("deprecation")
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        Server.channelGroup.remove(ctx.channel());
        ctx.close();
    }
}
