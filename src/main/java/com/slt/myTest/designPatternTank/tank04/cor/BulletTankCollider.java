package com.slt.myTest.designPatternTank.tank04.cor;


import com.slt.myTest.designPatternTank.tank04.Bullet;
import com.slt.myTest.designPatternTank.tank04.Explode;
import com.slt.myTest.designPatternTank.tank04.GameObject;
import com.slt.myTest.designPatternTank.tank04.Tank;

/**
 * 负责 子弹和tank、的碰撞
 */
public class BulletTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet bullet = (Bullet)o1;
			Tank tank = (Tank)o2;


			bullet.collideWith(tank);
//			if(bullet.group == t.getGroup()) {
//				return true;
//			}
//
//			if(bullet.rectangle.intersects(t.rectangle)) {
//				t.die();
//				bullet.die();
//				int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
//				int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//				new Explode(eX, eY);
//				return false;
//			}
			
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2, o1);
		} 
		
		return true;
		
	}

}
