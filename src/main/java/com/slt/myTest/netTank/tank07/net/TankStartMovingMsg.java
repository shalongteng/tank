package com.slt.myTest.netTank.tank07.net;

import com.slt.myTest.netTank.tank07.Dir;
import com.slt.myTest.netTank.tank07.Tank;
import com.slt.myTest.netTank.tank07.TankFrame;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TankStartMovingMsg extends Msg{
    UUID id;
    int x, y;
    Dir dir;

    public TankStartMovingMsg(UUID id, int x, int y, Dir dir) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public TankStartMovingMsg(Tank tank) {
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
    }

    @Override
    public void handle() {
        //自己不用处理
        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId())){
            return;
        }

        Tank tank = TankFrame.INSTANCE.findByUUID(id);
        if(tank != null){
            tank.setMoving(true);
            tank.setDir(dir);
            tank.setX(x);
            tank.setY(y);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] bytes = null;
        try {
            dataOutputStream.writeLong(id.getMostSignificantBits());
            dataOutputStream.writeLong(id.getLeastSignificantBits());
            dataOutputStream.writeInt(x);
            dataOutputStream.writeInt(y);
            dataOutputStream.writeInt(dir.ordinal());
            dataOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(dataOutputStream != null){
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(byteArrayOutputStream != null){
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(byteInputStream);
        try {
            this.id = new UUID(dataInputStream.readLong(),dataInputStream.readLong());
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
            this.dir = Dir.values()[dataInputStream.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(byteInputStream != null){
                    byteInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dataInputStream != null){
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("uuid=" + id + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("dir=" + dir + " | ")
                .append("]");

        return builder.toString();
    }
}
