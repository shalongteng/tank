package com.slt.myTest.netTank.tank07;

import com.slt.myTest.netTank.tank07.net.Client;
import com.slt.myTest.netTank.tank07.net.TankDieMsg;
import lombok.Data;

import java.awt.*;
import java.util.UUID;

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
    private Group group = Group.BAD;

    public Rectangle rectangle = new Rectangle();

    //一个子弹 一个id
    private UUID id = UUID.randomUUID();
    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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

        //tank 所在的长方形，子弹所在的长方形，有交集
        if(rectangle.intersects(tank.rectangle)) {
            tank.die();
            this.die();

            //调整爆炸的位置
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodeList.add(new Explode(eX, eY, tankFrame));
            //爆炸以后发送tank死亡消息
            Client.INSTANCE.send(new TankDieMsg(id,tankFrame.myTank.id));
        }
    }

    private void die() {
        this.living = false;
    }
}
