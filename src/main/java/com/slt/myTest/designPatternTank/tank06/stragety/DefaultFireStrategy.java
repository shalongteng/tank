package com.slt.myTest.designPatternTank.tank06.stragety;

import com.slt.myTest.designPatternTank.tank06.*;
import com.slt.myTest.designPatternTank.tank06.decorator.RectDecorator;

/**
 * @ProjectName: tank
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        GameModel.getInstance().add(new RectDecorator(new Bullet(bX, bY, tank.dir,tank.getGroup())));

//        if(tank.getGroup())
        if(tank.getGroup() == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
