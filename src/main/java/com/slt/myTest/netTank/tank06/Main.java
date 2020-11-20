package com.slt.myTest.netTank.tank06;


import com.slt.myTest.netTank.tank06.net.Client;

/**
 * 重构消息，让消息自己进行handle
 * 加入Msg体系继承体系后的TankJoinMsg修正
 * 自定义协议
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);
        new Thread(()->new Audio("audio/war1.wav").loop()).start();

//        int initTankCount = PropertyMgr.getInt("initTankCount");
//        //初始化敌方tank
//        for (int i =0;i< initTankCount; i++){
//            tankFrame.tankList.add(new Tank(50+50*i,200, Dir.DOWN,tankFrame, Group.BAD));
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(25);
                        tankFrame.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //or you can new a thread to run this
        //一个tank就是一个客户端
        Client.INSTANCE.connect();
    }
}
