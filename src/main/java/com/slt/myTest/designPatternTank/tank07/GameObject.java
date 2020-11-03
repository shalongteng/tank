package com.slt.myTest.designPatternTank.tank07;

import java.awt.*;

/**
 * 游戏物体的抽象类
 * tank 继承它
 */
public abstract class GameObject {
    public int x;
    public int y;


    public abstract void paint(Graphics g);

}
