package com.slt.myTest.tank02;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 画出tank
 */
public class TankFrame extends Frame {
    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        //使用画笔 画一个长方形
        graphics.fillRect(200,200,80,50);
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
