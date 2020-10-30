package com.slt.myTest.tank10;

/**
 * 按下ctrl键，tank会fire出一颗子弹
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
