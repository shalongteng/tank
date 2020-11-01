package com.slt.myTest.designPatternTank.tank05;


/**
 * 将 collider 串在一起 就是责任链模式
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
