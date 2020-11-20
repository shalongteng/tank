package com.slt.myTest.netTank.tank07.net;

import com.slt.myTest.netTank.tank07.Bullet;
import com.slt.myTest.netTank.tank07.Dir;
import com.slt.myTest.netTank.tank07.Group;
import com.slt.myTest.netTank.tank07.TankFrame;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BulletNewMsg extends Msg{
    //自己坦克id
    UUID playerID;
    UUID id;
    int x, y;
    Dir dir;
    Group group;

    public BulletNewMsg(Bullet bullet) {
        this.playerID = TankFrame.INSTANCE.getMainTank().getId();
        this.id = bullet.getId();
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
        this.group = bullet.getGroup();
    }

    @Override
    public void handle() {
        //自己不打自己
        if (this.playerID.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Bullet bullet = new Bullet(x, y, dir, TankFrame.INSTANCE, group);
        bullet.setId(this.id);
        TankFrame.INSTANCE.bulletList.add(bullet);
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            //先写主战坦克id
            dos.writeLong(this.playerID.getMostSignificantBits());
            dos.writeLong(this.playerID.getLeastSignificantBits());
            //写子弹id
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.playerID = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("playerid=" + playerID + " | ")
                .append("uuid=" + id + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("dir=" + dir + " | ")
                .append("group=" + group + " | ")
                .append("]");
        return builder.toString();
    }
}
