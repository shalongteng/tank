package com.slt.myTest.designPatternTank.tank01;


/**
 * ResourceMgr 修改成单例模式
 * 策略模式的使用
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
