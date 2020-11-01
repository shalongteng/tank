package com.slt.myTest.designPatternTank.tank02.abstractfactory;


import com.slt.myTest.designPatternTank.tank02.Dir;
import com.slt.myTest.designPatternTank.tank02.Group;
import com.slt.myTest.designPatternTank.tank02.TankFrame;

/**
 * 抽象工厂
 */
public abstract class GameFactory {
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
	public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
