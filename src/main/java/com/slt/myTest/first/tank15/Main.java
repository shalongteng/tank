package com.slt.myTest.first.tank15;


/**
 * 添加爆炸功能
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();


        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //初始化敌方tank
        for (int i =0;i< 5;i++){
            tankFrame.tankList.add(new Tank(100+100*i,200, Dir.DOWN,tankFrame, Group.BAD));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
