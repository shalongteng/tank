package com.slt.myTest.designPatternTank.tank04.stragety;

import com.slt.myTest.designPatternTank.tank04.Bullet;
import com.slt.myTest.designPatternTank.tank04.Tank;

/**
 * @ProjectName: tank
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, tank.dir,tank.gameModel ,tank.getGroup());

//        if(tank.getGroup())
    }
}
