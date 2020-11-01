package com.slt.myTest.designPatternTank.tank03;

import java.awt.*;

public class Explode {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;

	private boolean living = true;
	GameModel gameModel = null;

	private int step = 0;

	public Explode(int x, int y, GameModel gameModel) {
		this.x = x;
		this.y = y;
		this.gameModel = gameModel;
		//爆炸的背景音乐
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}



	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		//当step 大于 16 那么就是爆炸结束 将次爆炸从list中移除
		if(step >= ResourceMgr.explodes.length) {
			gameModel.explodeList.remove(this);
		}
	}
	
	

}
