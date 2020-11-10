package com.slt.myTest.designPatternTank.tank08.cor;

import com.slt.myTest.designPatternTank.tank08.GameObject;
import com.slt.myTest.designPatternTank.tank08.Tank;

/**
 * 坦克相撞
 * todo 坦克不走了。。。自闭了
 */
public class TankTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Tank) {
			Tank t1 = (Tank)o1;
			Tank t2 = (Tank)o2;
			if(t1.getRectangle().intersects(t2.getRectangle())) {
				t1.back();
//				t2.back();
			}

		}

		return true;

	}

}
