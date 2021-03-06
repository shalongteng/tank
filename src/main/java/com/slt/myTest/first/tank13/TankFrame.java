package com.slt.myTest.first.tank13;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TankFrame extends Frame {
    public Tank myTank = new Tank(200,500, Dir.DOWN,this);
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    //子弹集合
    public List<Bullet> bulletList = new ArrayList<>();

    public List<Tank> tankList = new ArrayList<>();
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
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        graphics.drawString("敌人的数量:" + tankList.size(), 10, 80);

        graphics.setColor(c);

        //调用tank paint方法
        myTank.paint(graphics);
        /**
         * 不能使用 增强for循环  ConcurrentModificationException
         * 因为循环过程中 bullet类 remove了子弹
         */
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(graphics);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(graphics);
        }
        //每一个子弹 都要和 任何一个tank 做碰撞检测
        for(int i=0; i<bulletList.size(); i++) {
            for(int j = 0; j<tankList.size(); j++){
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }
    }

    /**
     * 利用双缓冲，消除闪烁
     * 在内存中加载完成，在拷贝到显存中。
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
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
            //键盘抬起，tank静止
            setMainTankDir();
        }

        /**
         * 修改主站tank 方向
         */
        private void setMainTankDir() {
            //如果键盘没有 按下，tank静止
            if(!bL && !bU && !bR && !bD){
                myTank.moving =false;
            }else {
                //键盘按下，tank 移动
                myTank.moving = true;
                if(bL) myTank.dir = Dir.LEFT;
                if(bU) myTank.dir = Dir.UP;
                if(bR) myTank.dir = Dir.RIGHT;
                if(bD) myTank.dir = Dir.DOWN;
            }
        }

    }
}
