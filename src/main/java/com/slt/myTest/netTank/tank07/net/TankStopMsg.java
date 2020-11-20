package com.slt.myTest.netTank.tank07.net;

import com.slt.myTest.netTank.tank07.Tank;
import com.slt.myTest.netTank.tank07.TankFrame;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.UUID;
@Data
@NoArgsConstructor
public class TankStopMsg extends Msg{
    UUID id;
    int x, y;

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg(Tank t) {
        this.id = t.getId();
        this.x = t.getX();
        this.y = t.getY();
    }

    @Override
    public void handle() {
        //if this msg send by myself do nothing
        if(id.equals(TankFrame.INSTANCE.getMainTank().getId())){
           return;
        }
        Tank tank = TankFrame.INSTANCE.findByUUID(id);
        if(tank != null){
            tank.setMoving(false);
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
            dataOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.id = new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("uuid=" + id + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("]");
        return builder.toString();
    }
}
