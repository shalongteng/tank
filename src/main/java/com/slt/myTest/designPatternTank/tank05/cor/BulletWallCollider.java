package com.slt.myTest.designPatternTank.tank05.cor;


import com.slt.myTest.designPatternTank.tank05.Bullet;
import com.slt.myTest.designPatternTank.tank05.GameObject;
import com.slt.myTest.designPatternTank.tank05.Wall;

public class BulletWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Wall) {
			Bullet b = (Bullet)o1;
			Wall w = (Wall)o2;


			if(b.getRectangle().intersects(w.rect)) {
				b.die();
			}

		} else if (o1 instanceof Wall && o2 instanceof Bullet) {
			return collide(o2, o1);
		}

		return true;

	}

}
