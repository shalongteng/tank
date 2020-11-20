package com.slt.myTest.netTank.tank07.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerFrame extends Frame {
	//饿汉式 单例
	public static final ServerFrame INSTANCE = new ServerFrame();

	Button btnStart = new Button("start");
	TextArea taLeft = new TextArea();
	TextArea taRight = new TextArea();
	Server server = new Server();

	public ServerFrame () {
		this.setSize(1200, 600);
		this.setLocation(300, 30);
		this.add(btnStart, BorderLayout.NORTH);
		Panel p = new Panel(new GridLayout(1, 2));
		p.add(taLeft);
		p.add(taRight);
		this.add(p);

		taLeft.setFont(new Font("verderna",Font.PLAIN, 25));

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});


	}

	public static void main(String[] args) {
		ServerFrame.INSTANCE.setVisible(true);
		ServerFrame.INSTANCE.server.serverStart();
	}

	/**
	 * 左侧显示服务端消息
	 * @param string
	 */
	public void updateServerMsg(String string) {
		this.taLeft.setText(taLeft.getText() + string + System.getProperty("line.separator"));
	}

	/**
	 * 吧消息更新到 右侧
	 * @param string
	 */
	public void updateClientMsg(String string) {
		this.taRight.setText(taRight.getText() + string + System.getProperty("line.separator"));
	}
}
