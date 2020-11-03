package com.slt.myTest.designPatternTank.tank07;


/**
 * observer 模式 应用
 * todo 由于太懒没写哈哈
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
