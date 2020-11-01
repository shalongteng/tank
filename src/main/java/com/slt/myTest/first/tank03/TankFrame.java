package com.slt.myTest.first.tank03;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 坦克移动（手动）
 * 将坦克坐标定为变量，在paint中改编值，paint被调用后，就可以动起来
 */
public class TankFrame extends Frame {
    public int x = 200;
    public int y = 200;
    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        System.out.println("paint");
        //使用画笔 画一个长方形
        graphics.fillRect(x,y,50,50);
        x += 20;
        y += 20;
    }
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        tankFrame.setTitle("tank war");
        tankFrame.setVisible(true);

        tankFrame.setResizable(true);
        tankFrame.setSize(600,600);
        tankFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }
}
