package com.slt.tank;

import com.slt.myTest.netTank.tank04.net.TankJoinMsg;
import com.slt.myTest.netTank.tank04.net.TankJoinMsgDecoder;
import com.slt.myTest.netTank.tank04.net.TankJoinMsgEncoder;
import com.slt.myTest.netTank.tank04.Dir;
import com.slt.myTest.netTank.tank04.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 单元测试
 * 测试编解码 类，不需要启动服务器来测试了。
 * 启动服务器太麻烦
 *
 * ChannelHandlers是Netty应用程序的重要因素，所以彻底测试它们应该是开发过程的标准部分。
 * 最佳实践要求你进行测试不仅要证明你的实现是正确的，而且容易隔离因代码修改而突然出现的问题。
 * 这类测试叫做单元测试。
 *
 */
public class TankJoinMsgCodecTest {
    @Test
    void testEncoder() {
        //嵌入的channel
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD, id);
        //给通道添加编码器
        channel.pipeline().addLast(new TankJoinMsgEncoder());
        //写出数据到EmbeddedChannel。如果可以通过readOutbound()从EmbeddedChannel读取数据，返回true。
        channel.writeOutbound(msg);

        //从EmbeddedChannel读数据。返回的任何内容都遍历ChannelPipeline。如果没有准备好的数据来读取，返回null。
        ByteBuf buf = (ByteBuf)channel.readOutbound();//encoder

        /**
         * 按照编码的规则
         * 写解码规则
         */
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];
        boolean moving = buf.readBoolean();
        int groupOrdinal = buf.readInt();
        Group g = Group.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(true, moving);
        assertEquals(Group.BAD, g);
        assertEquals(id, uuid);
    }

    @Test
    void testDecoder() {
        EmbeddedChannel channel = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD, id);
        channel.pipeline().addLast(new TankJoinMsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        //msg.toBytes() 编码规则
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());
        //使用解码器，获取消息
        TankJoinMsg msgR = (TankJoinMsg)channel.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Dir.DOWN, msgR.dir);
        assertEquals(true, msgR.moving);
        assertEquals(Group.BAD, msgR.group);
        assertEquals(id, msgR.id);
    }
}
