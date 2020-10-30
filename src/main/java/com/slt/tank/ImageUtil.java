package com.slt.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * BufferedImage:  将一幅图片加载到内存中
 * BufferedImage生成的图片在内存里有一个图像缓冲区，利用这个缓冲区我们可以很方便地操作这个图片
 * 图像缩放、选择图像平滑度等，通常用来做图片大小变换、图片变灰、设置透明不透明等
 */
public class ImageUtil {
    /**
     * 旋转图片
     *
     * @param bufferedimage 图片对象
     * @param degree        需要旋转的度数
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
        int width = bufferedimage.getWidth();
        int height = bufferedimage.getHeight();
        BufferedImage img;
        Graphics2D graphics2d;

        //返回透明度。
        int type = bufferedimage.getColorModel().getTransparency();
        /**
        * setRenderingHint
        * 设置渲染算法的单个首选项的值。 提示类别包括渲染过程中渲染质量和总体时间/质量权衡的控制。
        * 有关一些常用键和值的定义，请参阅RenderingHints类。
        */
        (graphics2d = (img = new BufferedImage(width, height, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        //toRadians 将以度为单位测量的角度转换为以弧度为单位测量的近似等效角度。 以图片中心旋转
        graphics2d.rotate(Math.toRadians(degree), width / 2, height / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        //处置此图形上下文并释放它正在使用的任何系统资源。
        graphics2d.dispose();

        return img;
    }
}
