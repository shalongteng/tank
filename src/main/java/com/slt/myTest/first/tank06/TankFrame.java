package com.slt.myTest.first.tank06;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class TankFrame extends Frame {
    public int x = 200;
    public int y = 200;
    //坦克初始方向
    public Dir dir = Dir.DOWN;
    //此变量属于类即可 ，final不可修改, 常量最好用大写
    public static final int SPEED = 10;

    TankFrame(){
        this.setTitle("tank war");
        this.setVisible(true);

        this.setResizable(true);
        this.setSize(600,600);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        /**
         * 添加键盘监听事件
         */
        addKeyListener(new MyKeyListener());
    }
    /**
     * Java中每次重绘都会调用paint方法
     */
    @Override
    public void paint(Graphics graphics){
        System.out.println("paint");
        //使用画笔 画一个长方形
        graphics.fillRect(x,y,50,50);
        switch(dir) {
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }



    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            //vk virtual key
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            //键盘按下，改变tank方向
            setMainTankDir();
        }
        /**
         * 键盘抬起 给四个bool值 恢复false
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            //vk virtual key
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
        }

        /**
         * 修改主站tank 方向
         */
        private void setMainTankDir() {
            if(bL) dir = Dir.LEFT;
            if(bU) dir = Dir.UP;
            if(bR) dir = Dir.RIGHT;
            if(bD) dir = Dir.DOWN;
        }

    }
}
