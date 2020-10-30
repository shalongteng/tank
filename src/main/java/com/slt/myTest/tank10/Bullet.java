package com.slt.myTest.tank10;

import lombok.Data;

import java.awt.*;

/**
 * 和抽象出 tank类 相似
 */
@Data
public class Bullet extends Frame{
    public int x ;
    public int y ;
    //坦克初始方向
    public Dir dir;
    //子弹的大小
    private static int WIDTH = 30, HEIGHT = 30;
    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 20;

    private boolean live = true;
    private TankFrame tankFrame = null;
    Bullet(int x, int y, Dir dir,TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
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
        if(!live) {
            tankFrame.bulletList.remove(this);
        }
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
        //子弹飞出边界 子弹死亡
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
