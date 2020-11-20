package com.slt.myTest.netTank.tank06.net;

import com.slt.myTest.netTank.tank06.Dir;
import com.slt.myTest.netTank.tank06.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * ���Զ���Э��Ľ���
 * ��Ϣͷ��
 *  ǰ4λ��Ϣ����
 *  ����λ��Ϣ�峤��
 * ��Ϣ�壺
 *
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < 8) return;//TCP ��� ճ��������

		in.markReaderIndex();//��Ƕ����±�

		MsgType msgType = MsgType.values()[in.readInt()];
		int length = in.readInt();

		//�ɶ��ĳ��� ��Ϣ��Ϣ�峤�� ֱ�ӷ���
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
