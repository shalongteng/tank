package com.slt.myTest.designPatternTank.tank07;

import com.slt.myTest.designPatternTank.tank07.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameModel 大管家
 */
public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    public Tank myTank;

    static {
        INSTANCE.init();
    }
    //子弹集合
//    public List<Bullet> bulletList = new ArrayList<>();

//    public List<Tank> tankList = new ArrayList<>();
//    public List<Explode> explodeList = new ArrayList<>();

    private List<GameObject> gameObjectList = new ArrayList<>();


    public ColliderChain colliderChain = new ColliderChain();
    public GameModel(){

    }

    public static GameModel getInstance() {
        return INSTANCE;
    }
    private void init() {
        // 初始化主战坦克
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 200, 200, Dir.DOWN, Group.BAD);
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }
    public void add(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.gameObjectList.remove(gameObject);
    }
    public void paint(Graphics graphics){
//        Color c = graphics.getColor();
//        graphics.setColor(Color.WHITE);
//        graphics.drawString("子弹的数量:" + bulletList.size(), 10, 60);
//        graphics.drawString("敌人的数量:" + tankList.size(), 10, 80);
//
//        graphics.setColor(c);

        //调用tank paint方法
        myTank.paint(graphics);
        /**
         * 不能使用 增强for循环  ConcurrentModificationException
         * 因为循环过程中 bullet类 remove了子弹
         */
        for (int i = 0; i < gameObjectList.size(); i++) {
            gameObjectList.get(i).paint(graphics);
        }

        //每一个子弹 都要和 任何一个tank 做碰撞检测
        for(int i=0; i<gameObjectList.size(); i++) {
            for(int j = i+1; j<gameObjectList.size(); j++){
                GameObject gameObject = gameObjectList.get(i);
                GameObject gameObject2 = gameObjectList.get(j);

                colliderChain.collide(gameObject,gameObject2);
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}
