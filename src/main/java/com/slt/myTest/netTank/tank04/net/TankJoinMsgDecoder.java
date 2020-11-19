package com.slt.myTest.netTank.tank04.net;

import java.util.List;
import java.util.UUID;


import com.slt.myTest.netTank.tank04.Dir;
import com.slt.myTest.netTank.tank04.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < 33) return; //TCP 拆包 粘包的问题

		//in.markReaderIndex();
		TankJoinMsg msg = new TankJoinMsg();

		msg.x = in.readInt();
		msg.y = in.readInt();
		msg.dir = Dir.values()[in.readInt()];
		msg.moving = in.readBoolean();
		msg.group = Group.values()[in.readInt()];
		msg.id = new UUID(in.readLong(), in.readLong());

		out.add(msg);
	}

}
