package com.slt.myTest.first.tank13;

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
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 20;

    private boolean living = true;
    private TankFrame tankFrame = null;
    Bullet(int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics graphics) {
        if(!living) {
            tankFrame.bulletList.remove(this);
        }
        switch(dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

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
        //子弹飞出边界 子弹死亡
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    public void collideWith(Tank tank) {
        //子弹所在的长方形
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        //tank 所在的长方形
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        //有交集
        if(rect1.intersects(rect2)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
