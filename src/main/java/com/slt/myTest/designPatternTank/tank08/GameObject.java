package com.slt.myTest.designPatternTank.tank08;

import java.awt.*;
import java.io.Serializable;

/**
 * 游戏物体的抽象类
 * tank 继承它
 */
public abstract class GameObject implements Serializable {
    public int x;
    public int y;


    public abstract void paint(Graphics g);

}
