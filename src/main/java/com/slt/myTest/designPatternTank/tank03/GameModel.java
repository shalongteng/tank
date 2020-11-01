package com.slt.myTest.designPatternTank.tank03;

import com.slt.myTest.designPatternTank.tank02.FireStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameModel 大管家
 */
public class GameModel {
    public Tank myTank = new Tank(200,500, Dir.DOWN,this, Group.GOOD);
    //子弹集合
    public List<Bullet> bulletList = new ArrayList<>();

    public List<Tank> tankList = new ArrayList<>();
    public List<Explode> explodeList = new ArrayList<>();



    public GameModel(){
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方tank
        for (int i =0;i< initTankCount; i++){
            tankList.add(new Tank(50+50*i,200, Dir.DOWN,this, Group.BAD));
        }
    }

    public void paint(Graphics graphics){
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        graphics.drawString("敌人的数量:" + tankList.size(), 10, 80);

        graphics.setColor(c);

        //调用tank paint方法
        myTank.paint(graphics);
        /**
         * 不能使用 增强for循环  ConcurrentModificationException
         * 因为循环过程中 bullet类 remove了子弹
         */
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(graphics);
        }

        //每一个子弹 都要和 任何一个tank 做碰撞检测
        for(int i=0; i<bulletList.size(); i++) {
            for(int j = 0; j<tankList.size(); j++){
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }

        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(graphics);
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
