package com.slt.myTest.netTank.tank07.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
	public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public void serverStart() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);

		try {
			ServerBootstrap b = new ServerBootstrap();
			ChannelFuture f = b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pl = ch.pipeline();
						pl.addLast(new MsgEncoder());
						pl.addLast(new MsgDecoder());
						pl.addLast(new ServerChildHandler());
					}
				})
				.bind(8888)
				.sync();

			ServerFrame.INSTANCE.updateServerMsg("server started!");

			f.channel().closeFuture().sync(); //close()->ChannelFuture
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}


}

class ServerChildHandler extends ChannelInboundHandlerAdapter { //SimpleChannleInboundHandler Codec

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Server.clients.add(ctx.channel());
	}

	/**
	 * 服务器端挂上 编解码器，才能看到客户端消息的具体内容
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//读到客户端消息以后，更新到服务端fram的右侧
		ServerFrame.INSTANCE.updateClientMsg(msg.toString());
		Server.clients.writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		//抛异常以后，从group中移出该channel
		Server.clients.remove(ctx.channel());
		ctx.close();
	}


}





