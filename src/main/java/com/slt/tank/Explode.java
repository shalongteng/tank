package com.slt.tank;

import java.awt.*;

public class Explode {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x, y;
	
	//private boolean living = true;

	//每paint一次 step加一
	private int step = 0;
	private TankFrame tf =null;

	public Explode(int x, int y,TankFrame tankFrame) {
		this.x = x;
		this.y = y;
		this.tf = tankFrame;
		//创建一个线程 让他播放声音
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	
	

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length){
			step = 0;
			tf.explodeList.remove(this);
//			TankFrame.INSTANCE.explodes.remove(this);
		}

		
	}
	
	

}
