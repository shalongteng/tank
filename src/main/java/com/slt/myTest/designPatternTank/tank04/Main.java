package com.slt.myTest.designPatternTank.tank04;


/**
 * mediator 模式应用
 * 解决添加新游戏物体的问题
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
