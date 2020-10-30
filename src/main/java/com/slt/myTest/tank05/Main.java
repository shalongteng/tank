package com.slt.myTest.tank05;

/**
 * 让tank 自动移动
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
