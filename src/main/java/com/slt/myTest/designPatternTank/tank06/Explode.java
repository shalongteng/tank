package com.slt.myTest.designPatternTank.tank06;

import java.awt.*;

public class Explode extends GameObject {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;

	private boolean living = true;

	private int step = 0;

	public Explode(int x, int y) {
		this.x = x;
		this.y = y;
		//爆炸的背景音乐
		new Thread(()->new Audio("audio/explode.wav").play()).start();

		GameModel.getInstance().add(this);
	}



	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		//当step 大于 16 那么就是爆炸结束 将次爆炸从list中移除
		if(step >= ResourceMgr.explodes.length) {
			GameModel.getInstance().remove(this);
		}
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}


}
