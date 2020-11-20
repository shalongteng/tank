package com.slt.myTest.netTank.tank07.net;

import com.slt.myTest.netTank.tank07.Tank;
import com.slt.myTest.netTank.tank07.TankFrame;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TankDieMsg extends Msg{
    //who killed me
    UUID bulletId;
    UUID id;

    public TankDieMsg(UUID bulletId, UUID id) {
        this.bulletId = bulletId;
        this.id = id;
    }

    @Override
    public void handle() {
        System.out.println("we got a tank die:" + id);
        System.out.println("and my tank is:" + TankFrame.INSTANCE.getMainTank().getId());
        //如果是自己，自己就死掉
        if(id.equals(TankFrame.INSTANCE.getMainTank().getId())){
            TankFrame.INSTANCE.getMainTank().die();
        }else{
            Tank tank = TankFrame.INSTANCE.findByUUID(id);
            if(tank != null){
                tank.die();
            }
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(bulletId.getMostSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
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
            this.bulletId = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(), dis.readLong());
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
        return MsgType.TankDie;
    }
}
