package com.slt.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        //背景音乐
//		new Thread(()->new Audio("audio/war1.wav").loop()).start();


        String s = (String) PropertyMgr.get("initTankCount");
        int initTankCount = Integer.parseInt(s);
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, tf));
        }
        //每隔50ms 调用一次repaint repaint方法会调用paint方法
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }

}
