package com.slt.myTest.netTank.tank07.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 对自定义协议的解析
 * 消息头：
 * 前4位消息类型
 * 后四位消息体长度
 * 消息体：
 */
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return;//TCP 拆包 粘包的问题

        in.markReaderIndex();//标记读的下标

        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();

        //可读的长度 消息消息体长度 直接返回
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Msg msg = null;
        //Class.forName(msgType.toString + "Msg").newInstance();
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                //消息自己来解析
                break;
            case TankStop:
                msg = new TankStopMsg();
                //消息自己来解析
                break;
            default:
                break;
        }
        msg.parse(bytes);
        out.add(msg);
    }

}
