package com.slt.myTest.designPatternTank.tank03;

import com.slt.myTest.designPatternTank.tank03.stragety.DefaultFireStrategy;
import com.slt.myTest.designPatternTank.tank03.stragety.FireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class TankFrame extends Frame {
    public GameModel gameModel = new GameModel();

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;



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
        gameModel.paint(graphics);
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
                    gameModel.getMainTank().fire(new DefaultFireStrategy());
                    break;
                case KeyEvent.VK_Z:
                    //加了不同策略   策略模式
                    //策略从配置文件读取，灵活方便
                    try {
                        FireStrategy fireStrategy = (FireStrategy) Class.forName(PropertyMgr.getString("goodFS")).newInstance();
                        gameModel.getMainTank().fire(fireStrategy);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
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
                gameModel.getMainTank().moving =false;
            }else {
                //键盘按下，tank 移动
                gameModel.getMainTank().moving = true;
                if(bL){
                    gameModel.getMainTank().dir = Dir.LEFT;
                }
                if(bU){
                    gameModel.getMainTank().dir = Dir.UP;
                }
                if(bR){
                    gameModel.getMainTank().dir = Dir.RIGHT;
                }
                if(bD){
                    gameModel.getMainTank().dir = Dir.DOWN;
                }
            }
        }

    }
}
