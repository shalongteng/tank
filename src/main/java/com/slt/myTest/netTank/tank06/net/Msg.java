package com.slt.myTest.netTank.tank06.net;
/**
 * ���Զ���Э��Ľ���
 * ��Ϣͷ��
 *  ǰ4λ��Ϣ����
 *  ����λ��Ϣ�峤��
 * ��Ϣ�壺
 *
 */
public abstract class Msg {
	public abstract void handle();
	public abstract byte[] toBytes();
	public abstract void parse(byte[] bytes);
	public abstract MsgType getMsgType();
}
