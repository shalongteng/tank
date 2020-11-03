package com.slt.myTest.designPatternTank.tank07.observer;

import java.io.Serializable;

public interface TankFireObserver extends Serializable {
	void actionOnFire(TankFireEvent e);
}
