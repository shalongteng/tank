package com.slt.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
	private static final int SPEED = 1;
	public static int WIDTH = ResourceMgr.tankD.getWidth();

	public static int HEIGHT = ResourceMgr.tankD.getHeight();
	
	private Random random = new Random();

	private int x, y;

	private Dir dir = Dir.DOWN;

	private boolean moving = true;
	private TankFrame tf = null;
	private boolean living = true;
	private Group group = Group.BAD;
	
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}
	public void fire() {
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
	}
	
	public Dir getDir() {
		return dir;
	}
	
	public int getX() {
		return x;
	}
	
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}
	/**
	 * 根据方法进行移动
	 */
	private void move() {
		//如果没有移动 退出方法
		if(!moving) {
			return ;
		}
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		//坦克移动时候，随机发射子弹
		if(random.nextInt(10) > 8) {
			this.fire();
		}
	}

	public void paint(Graphics g) {
		if(!living) tf.tanks.remove(this);
		
		switch(dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		}
	
		move();
	
	}


	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void die() {
		this.living = false;
	}
	
	

}
