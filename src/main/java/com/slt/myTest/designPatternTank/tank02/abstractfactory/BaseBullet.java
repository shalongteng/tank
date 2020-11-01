package com.slt.myTest.designPatternTank.tank02.abstractfactory;

import java.awt.*;

public abstract class BaseBullet {
	public abstract void paint(Graphics g);

	public abstract void collideWith(BaseTank tank);
}
