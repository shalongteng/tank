package com.slt.myTest.netTank.net;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 服务端
 */
public class Server {
    public void serverStart(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    }
}
