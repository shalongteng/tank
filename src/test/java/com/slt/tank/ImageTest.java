package com.slt.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageTest {

	@Test
	void test() {
		try {
			/**
			 * 这种方法读图片 有局限性
			 * 绝对路径写死了
			 */
			BufferedImage image = ImageIO.read(new File("/Users/shalongteng/IdeaProjects1/tank/src/main/resources/images/bulletD.gif"));
			//如果image 不是空 就会测试通过
			assertNotNull(image);


			/**
			 * 从classpath下读取
			 * resources若不配置，可能会发送打包不全  需要配置pom.xml
			 * target打包时候只有application.properties
			 */
			//放在内存里的一个 image
			InputStream inputStream = ImageTest.class.getClassLoader().getResourceAsStream("bulletD.gif");
			InputStream inputStream2 = ImageTest.class.getClassLoader().getResourceAsStream("application.properties");
			BufferedImage image2 = ImageIO.read(inputStream2);
			//this.getClass()
			assertNotNull(image2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
