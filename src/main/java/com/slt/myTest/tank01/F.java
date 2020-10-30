package com.slt.myTest.tank01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 初步认识Frame类
 *  带有标题和边框的顶层窗口
 */
public class F {
    public static void main(String[] args) {
        Frame frame = new Frame();
        //不设置可见性,窗口看不到,程序直接退出
        frame.setVisible(true);

        //即使不设置大小 也会有一个窗口
        frame.setSize(800,600);

        //窗口 可以改变大小
        frame.setResizable(true);
        frame.setTitle("坦克大战");

        frame.setBackground(Color.BLACK);
        //设置 窗口的位置
        frame.setLocation(600,200);

        /**
         * 添加 窗口监听器
         * 不是函数式接口，不可用lambda 表达式
         * 事件监听机制 -- 观察者模式
         *  F类 就是事件源
         */
        frame.addWindowListener(new WindowAdapter() {
            //对关闭窗口事件 加监听器
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });


    }
}
