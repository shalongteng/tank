package com.slt.tank;

import java.awt.*;
import java.util.Random;

/**
 * tank 类
 */
public class Tank {
	private static final int SPEED = 2;
	public static int WIDTH = ResourceMgr.goodTankD.getWidth();
	public static int HEIGHT = ResourceMgr.goodTankD.getHeight();
	
	private Random random = new Random();
	private int x, y;
	/**
	 * tank 初始方向
	 * 因为方向只有四个,有限个数 所以使用枚举类
	 */
	private Dir dir = Dir.DOWN;

	private boolean moving = true;
	private TankFrame tf = null;
	private boolean living = true;
	private Group group = Group.BAD;

	//将rect作为属性，碰撞检测时候就不用new了，减少GC
	public Rectangle rect = new Rectangle();

	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
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
		//坦克移动时候，随机发射子弹,随机移动
		if(this.group == Group.BAD && random.nextInt(100) > 95){
			this.fire();
		}

		if(this.group == Group.BAD && random.nextInt(100) > 95){
			randomDir();
		}
		//边界检测 不让他开出去
		boundsCheck();

		//update rect
		rect.x = this.x;
		rect.y = this.y;
	}

	private void boundsCheck() {
		if (this.x < 2) {
			x = 2;
		}
		if (this.y < 28) {
			y = 28;
		}
		if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) {
			x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
		}
		if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ){
			y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
		}
	}

	//给他定义随机方向
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	public void paint(Graphics g) {
		if(!living){
			tf.tanks.remove(this);
		}

		switch(dir) {
			case LEFT:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case UP:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
