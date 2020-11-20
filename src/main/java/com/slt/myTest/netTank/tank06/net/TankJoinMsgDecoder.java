package com.slt.myTest.netTank.tank06.net;

import com.slt.myTest.netTank.tank06.Dir;
import com.slt.myTest.netTank.tank06.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * 对自定义协议的解析
 * 消息头：
 *  前4位消息类型
 *  后四位消息体长度
 * 消息体：
 *
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < 8) return;//TCP 拆包 粘包的问题

		in.markReaderIndex();//标记读的下标

		MsgType msgType = MsgType.values()[in.readInt()];
		int length = in.readInt();

		//可读的长度 消息消息体长度 直接返回
		if(in.readableBytes()< length) {
			in.resetReaderIndex();
			return;
		}

		byte[] bytes = new byte[length];
		in.readBytes(bytes);

		switch(msgType) {
			case TankJoin:
				TankJoinMsg msg = new TankJoinMsg();
				msg.parse(bytes);
				out.add(msg);
				break;
			default:
				break;
		}
	}

}
