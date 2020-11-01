package com.slt.myTest.first.tank06;

/**
 * 让tank 根据上下左右键 来移动
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
