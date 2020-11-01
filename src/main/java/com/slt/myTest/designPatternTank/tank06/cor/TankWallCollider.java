package com.slt.myTest.designPatternTank.tank06.cor;


import com.slt.myTest.designPatternTank.tank06.GameObject;
import com.slt.myTest.designPatternTank.tank06.Tank;
import com.slt.myTest.designPatternTank.tank06.Wall;

public class TankWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Wall) {
			Tank t = (Tank)o1;
			Wall w = (Wall)o2;


			if(t.getRectangle().intersects(w.rect)) {
				t.back();
			}

		} else if (o1 instanceof Wall && o2 instanceof Tank) {
			return collide(o2, o1);
		}

		return true;

	}

}
