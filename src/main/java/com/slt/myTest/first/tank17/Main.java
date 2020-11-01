package com.slt.myTest.first.tank17;


/**
 * 修复碰撞时产生很多rect的小问题，在子弹和坦克中维护一个rect，随动更新
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();


        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方tank
        for (int i =0;i< initTankCount; i++){
            tankFrame.tankList.add(new Tank(50+50*i,200, Dir.DOWN,tankFrame, Group.BAD));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
