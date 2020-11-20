package com.slt.myTest.netTank.tank06.net;
/**
 * 对自定义协议的解析
 * 消息头：
 *  前4位消息类型
 *  后四位消息体长度
 * 消息体：
 *
 */
public abstract class Msg {
	public abstract void handle();
	public abstract byte[] toBytes();
	public abstract void parse(byte[] bytes);
	public abstract MsgType getMsgType();
}
