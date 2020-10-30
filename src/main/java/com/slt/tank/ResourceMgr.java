package com.slt.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Java将一幅图片加载到内存的方法是：
 *	 BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
 */
public class ResourceMgr {
	//四个方向坦克图片 资源
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
	public static BufferedImage badTankL, badTankU, badTankR, badTankD;
	//四个方向子弹图片 资源
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	//爆炸图片 资源
	public static BufferedImage[] explodes = new BufferedImage[16];

	static {
		try {
			/**
			 * ClassLoader
			 *  InputStream getResourceAsStream(String name)
			 *  从classpath下 加载文件
			 *  需要在pom.xml中 配置resource 标签
			 */
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

			//加载爆炸的图片 共有16张
			for(int i=0; i<16; i++){
				explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
