package com.slt.myTest.designPatternTank.tank07.observer;


import com.slt.myTest.designPatternTank.tank07.Tank;

public class TankFireEvent {
	Tank tank;

	public Tank getSource() {
		return tank;
	}

	public TankFireEvent(Tank tank) {
		this.tank = tank;
	}

}
