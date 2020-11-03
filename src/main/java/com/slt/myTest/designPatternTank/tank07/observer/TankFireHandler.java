package com.slt.myTest.designPatternTank.tank07.observer;


import com.slt.myTest.designPatternTank.tank07.Tank;

public class TankFireHandler implements TankFireObserver {

	@Override
	public void actionOnFire(TankFireEvent e) {
		Tank t = e.getSource();
		t.fire(null);
	}

}
