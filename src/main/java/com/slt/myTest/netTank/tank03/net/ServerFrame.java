package com.slt.myTest.netTank.tank03.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 用来调试 客户端发送的消息
 */
public class ServerFrame extends Frame{
    public static final ServerFrame INSTANCE = new ServerFrame();

    //TextArea对象是显示文本的多行区域。 它可以设置为允许编辑或只读。
    Button btnStart = new Button("start");
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Server server = new Server();

    public ServerFrame () {
        this.setSize(1600, 600);
        this.setLocation(300, 30);
        this.add(btnStart, BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(taLeft);
        p.add(taRight);
        //把面板加到 框架里
        this.add(p);

        taLeft.setFont(new Font("verderna",Font.PLAIN, 25));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
    }

    public void updateServerMsg(String string) {
        // 获取操作系统对应的换行符
        this.taLeft.setText(taLeft.getText() + string + System.getProperty("line.separator"));
    }
}
