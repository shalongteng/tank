package com.slt.myTest.designPatternTank.tank06;

import com.slt.myTest.designPatternTank.tank06.stragety.DefaultFireStrategy;
import com.slt.myTest.designPatternTank.tank06.stragety.FireStrategy;
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
public class Tank extends GameObject {
     //坦克初始方向
    public Dir dir;

    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 3;
    //盘点tank 是否在移动
    public boolean moving = true;
    public boolean living = true;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Group group = Group.BAD;
    private Random random = new Random();

    public Rectangle rectangle = new Rectangle();


    public int oldX, oldY;
    Tank(int x, int y, Dir dir, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        //new tank的时候 给tank的 长方形初始化
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;


        GameModel.getInstance().add(this);

    }

    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        //如果tank 死亡就 移除
        if(!living){
            GameModel.getInstance().remove(this);
        }

        //根据tank阵营，画好tank 还是画 敌人tank
        switch(dir) {
            case LEFT:
                graphics.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                graphics.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        //如果tank 没有移动，tank静止。
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * tank 移动 抽出 一个方法
     */
    private void move() {
        //记录移动之前的位置
        oldX = x;
        oldY = y;
        if(!moving) {
            return ;
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
        //敌人随机发射子弹
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire(null);
        }
        //敌人tank随机改变方向
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }
        //边界检测
        boundsCheck();
        //update rect
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    private void boundsCheck() {
        //tank是一个正方形 左边界
        //TODO 左边界为啥是2
        if(this.x < 2){
            x = 2;
        }
        //上边界
        if (this.y < 28){
            y = 28;
        }
        //右边
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        //下边
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ){
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT -2;
        }
    }
    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 开火
     */
    public void fire(FireStrategy fireStrategy) {
        if(fireStrategy == null){
            new DefaultFireStrategy().fire(this);
        }else{
            fireStrategy.fire(this);
        }
    }

    public void die() {
        this.living = false;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void back() {
        x = oldX;
        y = oldY;
    }
}
