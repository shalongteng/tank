package com.slt.myTest.netTank.tank07.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * ���Զ���Э��Ľ���
 * ��Ϣͷ��
 * ǰ4λ��Ϣ����
 * ����λ��Ϣ�峤��
 * ��Ϣ�壺
 */
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return;//TCP ��� ճ��������

        in.markReaderIndex();//��Ƕ����±�

        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();

        //�ɶ��ĳ��� ��Ϣ��Ϣ�峤�� ֱ�ӷ���
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
                //��Ϣ�Լ�������
                break;
            case TankStop:
                msg = new TankStopMsg();
                //��Ϣ�Լ�������
                break;
            default:
                break;
        }
        msg.parse(bytes);
        out.add(msg);
    }

}
