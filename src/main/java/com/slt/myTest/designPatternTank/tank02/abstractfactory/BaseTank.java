package com.slt.myTest.designPatternTank.tank02.abstractfactory;


import com.slt.myTest.designPatternTank.tank02.Group;

import java.awt.*;

/**
 * 抽象的产品
 */
public abstract class BaseTank {
	public Group group = Group.BAD;
	public Rectangle rect = new Rectangle();
	
	public abstract void paint(Graphics g);

	public Group getGroup() {
		return this.group;
	}

	public abstract void die();

	public abstract int getX();

	public abstract int getY();
}
