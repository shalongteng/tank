package com.slt.myTest.designPatternTank.tank02.abstractfactory;


import com.slt.myTest.designPatternTank.tank02.Dir;
import com.slt.myTest.designPatternTank.tank02.Group;
import com.slt.myTest.designPatternTank.tank02.TankFrame;

/**
 * 具体的工厂
 * 产品族
 */
public class RectFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectTank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new RectExplode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectBullet(x, y, dir, group, tf);
	}

}
