package com.slt.myTest.netTank.tank03.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class Client {
    private Channel channel = null;
    public static void main(String[] args) {
        new Client().connect();
    }

    public void connect() {
        //事件处理的线程池  connect read write
        NioEventLoopGroup workers = new NioEventLoopGroup();
        //客户端配置
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.group(workers)
                    //绑定的通道是NioSocketChannel不是SocketChannel
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("ch: " + ch);
                            ch.pipeline().addLast(new TankMsgEncoder())
                                    .addLast(new ClientHandler());
                        }
                    })
                    .connect("localhost", 8666)
                    .sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        // initialize the channel
                        channel = future.channel();
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workers.shutdownGracefully();
        }

    }
    public void send(String msg) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(byteBuf);
    }
    public void close(){
        this.send("_bye_");
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {
    //通道建立了，第一次初始化时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channel is activated.");
//
//        //byteBuf 指向直接内存 ，跳过gc
//        ByteBuf byteBuf = Unpooled.copiedBuffer("HelloNetty".getBytes());
//        //写到服务器 一个字符串，writeAndFlush会自动释放内存
//        ChannelFuture future = ctx.writeAndFlush(byteBuf);
//

        ctx.writeAndFlush(new TankMsg(6,6));

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
        try {
//            ByteBuf byteBuf = (ByteBuf) msg;
//            System.out.println("client: channel read：" + byteBuf.toString(CharsetUtil.UTF_8));
            TankMsg tm = (TankMsg)msg;

            System.out.println("client: channel read：" + tm);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
