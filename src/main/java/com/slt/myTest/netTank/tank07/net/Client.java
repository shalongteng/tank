package com.slt.myTest.netTank.tank07.net;

import com.slt.myTest.netTank.tank07.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 在Netty中所有的I/O操作都是异步的。这意味着任何的I/O调用都将立即返回，
 * 而不保证这些被请求的I/O操作在调用结束的时候已经完成。你会得到一个返回的ChannelFuture实例，
 * 这个实例将给你一些关于I/O操作结果或者状态的信息。
 */
public class Client {
	private Channel channel = null;
	public static final Client INSTANCE = new Client();

	public void connect() {
		// 工作线程
		EventLoopGroup group = new NioEventLoopGroup(1);
		//客户端配置
		Bootstrap b = new Bootstrap();
		try {
			ChannelFuture f = b.group(group)
					.channel(NioSocketChannel.class)
					//禁用nagle算法
					.option(ChannelOption.TCP_NODELAY, false)
					.handler(new ClientChannelInitializer())
					.connect("localhost", 8888);

			f.addListener(new ChannelFutureListener() {
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

			f.sync();
			// wait until close
			f.channel().closeFuture().sync();
			System.out.println("connection closed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public void send(Msg msg) {
//		ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
		channel.writeAndFlush(msg);
	}

}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
			.addLast(new MsgEncoder())
			.addLast(new MsgDecoder())
			.addLast(new ClientHandler());
	}

}

/**
 * ChannelInboundHandlerAdapter 替换成 SimpleChannelInboundHandler
 * 使用了泛型
 */
class ClientHandler extends SimpleChannelInboundHandler<Msg> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//通道初始化时候，发送tank加入消息
		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
		msg.handle();
	}
}
