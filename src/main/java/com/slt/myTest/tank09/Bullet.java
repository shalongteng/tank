package com.slt.myTest.tank09;

import java.awt.*;

/**
 * 和抽象出 tank类 相似
 */
public class Bullet extends Frame{
    public int x ;
    public int y ;
    //坦克初始方向
    public Dir dir;
    //子弹的大小
    private static int WIDTH = 30, HEIGHT = 30;
    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 20;

    Bullet(int x,int y,Dir dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, WIDTH, HEIGHT);
        graphics.setColor(c);

        //子弹 移动
        move();
    }

    /**
     * 子弹 移动
     */
    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }
}
