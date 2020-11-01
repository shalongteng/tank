package com.slt.myTest.designPatternTank.tank05;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 将ResourceMgr 修改为单例
 * 懒汉式
 */
public class ResourceMgr {
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD; 
	public static BufferedImage badTankL, badTankU, badTankR, badTankD; 
	public static BufferedImage bulletL, bulletU, bulletR, bulletD; 
	public static BufferedImage[] explodes = new BufferedImage[16];
	//构造器私有化
 	private ResourceMgr(){

	}
	private static volatile ResourceMgr instance;
 	//双重检查
 	public ResourceMgr getInstance(){
 		if(instance != null){
 			synchronized (this){
 				if(instance != null){
 					return new ResourceMgr();
				}
			}
		}
 		return instance;
	}


	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);

			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			
			for(int i=0; i<16; i++) 
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
