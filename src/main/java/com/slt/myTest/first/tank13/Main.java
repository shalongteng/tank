package com.slt.myTest.first.tank13;


/**
 * 坦克换成图片版
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //初始化敌方tank
        for (int i =0;i< 5;i++){
            tankFrame.tankList.add(new Tank(100+100*i,200, Dir.DOWN,tankFrame));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
