package com.slt.myTest.designPatternTank.tank03;


/**
 * Facade 模式应用
 * 解决添加新游戏物体的问题
 * model 和 view 的分离
 *
 * model 添加一个大管家GameModel
 * Frame  所有和 游戏物体打交道 交给 gamemodel
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
