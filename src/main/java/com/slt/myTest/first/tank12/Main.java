package com.slt.myTest.first.tank12;

/**
 * 坦克换成图片版
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
