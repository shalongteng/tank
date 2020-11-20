package com.slt.myTest.netTank.tank05;


import com.slt.myTest.netTank.tank05.net.Client;

/**
 * 学习使用SimpleChannelInboundHandler
 * 系统初始化后连接服务器，主战坦克随机位置
 * 处理坦克加入的消息
 * 新加入的人，能够看到老人
 * bug：client端 有一定几率第一个启动的人，不去画自己。自己丢了
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame =TankFrame.INSTANCE;
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
        Client client = new Client();
        client.connect();
    }
}
