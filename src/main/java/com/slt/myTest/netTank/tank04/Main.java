package com.slt.myTest.netTank.tank04;


/**
 * 完善坦克消息
 * 重命名消息类，编解码类
 * EmbeddedChannel 单元测试
 * 客户端发送TankJoinMsg并使用自己的Codec接收到
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();


//        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
//        int initTankCount = PropertyMgr.getInt("initTankCount");
//        //初始化敌方tank
//        for (int i =0;i< initTankCount; i++){
//            tankFrame.tankList.add(new Tank(50+50*i,200, Dir.DOWN,tankFrame, Group.BAD));
//        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
