package com.slt.myTest.netTank.tank07.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {

	/**
	 * �����Զ�����Ϣ��������д��ȥ
	 * @param ctx
	 * @param msg
	 * @param out
	 * @throws Exception
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
		out.writeInt(msg.getMsgType().ordinal());
		byte[] bytes = msg.toBytes();
		out.writeInt(bytes.length);
		out.writeBytes(bytes);
	}
}
