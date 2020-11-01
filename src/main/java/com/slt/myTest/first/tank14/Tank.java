package com.slt.myTest.first.tank14;

import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * 抽象出坦克类
 *  坦克位置 x y
 *  坦克方向 dir
 *  坦克速度 SPEED
 */
@Data
public class Tank extends Frame {
    public int x ;
    public int y ;
    //坦克初始方向
    public Dir dir;

    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 3;
    //盘点tank 是否在移动
    public boolean moving = true;
    public boolean living = true;

    private TankFrame tankFrame = null;
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private Group group = Group.BAD;
    private Random random = new Random();

    Tank(int x, int y, Dir dir, TankFrame tankFrame,Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        //如果tank 死亡就 移除
        if(!living){
            tankFrame.tankList.remove(this);
        }

        //使用画笔 画一个长方形
        switch(dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }

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
        if(random.nextInt(10) > 8){
            this.fire();
        }

    }

    /**
     * 开火
     */
    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tankFrame.bulletList.add(new Bullet(bX, bY, dir,tankFrame,group));
    }

    public void die() {
        this.living = false;
    }
}
