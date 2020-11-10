package com.slt.myTest.designPatternTank.tank08;

import lombok.Data;

import java.awt.*;

/**
 * 和抽象出 tank类 相似
 */
@Data
public class Bullet extends GameObject{
    //坦克初始方向
    public Dir dir;
    //子弹的大小
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 20;

    private boolean living = true;
    public Group group = Group.BAD;

    public Rectangle rectangle = new Rectangle();
    public Bullet(int x, int y, Dir dir, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics graphics) {
        if(!living) {
            GameModel.getInstance().remove(this);
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    /**
     *
     * @param tank
     */
    public void collideWith(Tank tank) {
        //区分敌我
        if(this.group == tank.getGroup()){
            return;
        }

        //TODO: 用一个rect来记录子弹的位置
        //子弹所在的长方形
        //tank 所在的长方形
        //有交集
        if(rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();

            //调整爆炸的位置
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            GameModel.getInstance().add(new Explode(eX, eY));
        }
    }

    public void die() {
        this.living = false;
    }


}
