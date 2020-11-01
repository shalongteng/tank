package com.slt.myTest.first.tank09;

import java.awt.*;

/**
 * 抽象出坦克类
 *  坦克位置 x y
 *  坦克方向 dir
 *  坦克速度 SPEED
 */
public class Tank extends Frame {
    public int x ;
    public int y ;
    //坦克初始方向
    public Dir dir;

    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 10;
    //盘点tank 是否在移动
    public boolean moving = false;

    Tank(int x, int y, Dir dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        //使用画笔 画一个长方形
        Color color = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x,y,50,50);

        graphics.setColor(color);
        //如果tank 没有移动，tank静止。
        move();
    }

    /**
     * tank 移动 抽出 一个方法
     */
    private void move() {
        if(!moving) return ;

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
