package com.slt.myTest.netTank.tank06.net;

import com.slt.myTest.netTank.tank06.Tank;
import com.slt.myTest.netTank.tank06.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
			System.out.println("�Ѿ��˳�");
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
			.addLast(new TankJoinMsgEncoder())
			.addLast(new TankJoinMsgDecoder())
			.addLast(new ClientHandler());
	}

}

/**
 * ChannelInboundHandlerAdapter 替换成 SimpleChannelInboundHandler
 * 使用了泛型
 */
class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg> {

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println(msg);
//	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
//		//自己或者 已经存在在自己窗口的 tank 就不处理了
//		if(msg.id.equals(TankFrame.INSTANCE.getMainTank().getId()) ||
//				TankFrame.INSTANCE.findByUUID(msg.id) != null) return;
//
//		Tank t = new Tank(msg);
//		TankFrame.INSTANCE.addTank(t);
//		//当新的tank加入了，老tank发送一下自己的位置
//		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
//		System.out.println(msg);
		msg.handle();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		ctx.writeAndFlush(new TankJoinMsg(5, 8, Dir.DOWN, false, Group.GOOD, UUID.randomUUID()));
		ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
	}

}
