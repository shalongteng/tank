package com.slt.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 860;

	List<Explode> explodeList = new ArrayList<>();
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);

		this.addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) { // bjmashibing/tank
				System.exit(0);
			}

		});
	}

	/**
	 * 利用双缓冲，消除闪烁
	 * 在内存中加载完成，在拷贝到显存中。
	 */
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌人的数量:" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量:" + tanks.size(), 10, 100);
		g.setColor(c);

		myTank.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}

		for (int i = 0; i < explodeList.size(); i++) {
			explodeList.get(i).paint(g);
		}

		//碰撞检测
		for(int i=0; i<bullets.size(); i++) {
			for(int j = 0; j<tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}

//		explode.paint(g);
		// for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
		// Bullet b = it.next();
		// if(!b.live) it.remove();
		// }

		// for(Bullet b : bullets) {
		// b.paint(g);
		// }

	}

	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}

			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;

			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;

			default:
				break;
			}

			setMainTankDir();
		}

		/**
		 * 设置主站坦克方向
		 */
		private void setMainTankDir() {

			if (!bL && !bU && !bR && !bD){
				myTank.setMoving(false);
			}
			else {
				myTank.setMoving(true);

				if (bL){
					myTank.setDir(Dir.LEFT);
				}
				if (bU){
					myTank.setDir(Dir.UP);
				}
				if (bR){
					myTank.setDir(Dir.RIGHT);
				}
				if (bD){
					myTank.setDir(Dir.DOWN);
				}
			}
		}
	}
}
