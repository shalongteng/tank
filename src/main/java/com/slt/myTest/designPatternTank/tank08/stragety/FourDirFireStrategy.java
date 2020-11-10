package com.slt.myTest.designPatternTank.tank08.stragety;

import com.slt.myTest.designPatternTank.tank08.Bullet;
import com.slt.myTest.designPatternTank.tank08.Dir;
import com.slt.myTest.designPatternTank.tank08.Tank;

/**
 * @ProjectName: tank
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        for (Dir dir : Dir.values()) {
            new Bullet(bX, bY, dir,tank.getGroup());
        }

//        if(tank.getGroup())
    }
}
