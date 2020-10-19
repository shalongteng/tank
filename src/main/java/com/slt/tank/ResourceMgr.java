package com.slt.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
	//四个方向坦克图片 资源
	public static BufferedImage tankL, tankU, tankR, tankD;
	//四个方向子弹图片 资源
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	//爆炸图片 资源
	public static BufferedImage[] explodes = new BufferedImage[16];

	static {
		try {
			tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			tankL = ImageUtil.rotateImage(tankU, -90);
			tankR = ImageUtil.rotateImage(tankU, 90);
			tankD = ImageUtil.rotateImage(tankU, 180);

//			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
//			badTankL = ImageUtil.rotateImage(badTankU, -90);
//			badTankR = ImageUtil.rotateImage(badTankU, 90);
//			badTankD = ImageUtil.rotateImage(badTankU, 180);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);

			for(int i=0; i<16; i++){
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
