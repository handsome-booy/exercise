package com.xxj.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * 图像灰度算法
 */
public class ImageColor {
    public static String[] brandNames = new String[] {
            "刷子", "剪刀", "叉子",
            "手套", "水桶", "火",
            "玉米", "粉线球", "瓶子",
            "白菜", "稻草", "木桩",
            "胡萝卜", "白棉花", "青草", "铃铛"
    };

    /**
     * 让图片变灰色
     * @param imgPath 图片的路径
     * @param fileUrl 输出图片的路径
     */
    public static void inverse(String imgPath, String fileUrl) {
        try {
            FileInputStream fileInputStream = new FileInputStream(imgPath);
            BufferedImage image = ImageIO.read(fileInputStream);
            //生成字符图片
            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage imageBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            //绘制字符
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int rgb = image.getRGB(x, y);
                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0x00ff00) >> 8;
                    int B = rgb & 0x0000ff;

                    int gray = (int)(R * 0.299 + G * 0.587 + B * 0.114);

                    int newPixel = colorToRGB(gray, gray, gray);

                    imageBuffer.setRGB(x, y, newPixel);
                }
            }
            ImageIO.write(imageBuffer, "png", new File(fileUrl));//输出图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int colorToRGB(int red, int green, int blue) {
        int newPixel = 0;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        return  newPixel;
    }

    public static void main(String[] args) {
        for (int i = 0; i < brandNames.length; i++) {
            String name = brandNames[i];
            //inverse("D:\\user01\\白菜.png", "D:\\user01\\白菜_gray.png");
            inverse("C:\\Users\\Administrator\\Desktop\\exercise\\images\\" + name + ".png",
                    "C:\\Users\\Administrator\\Desktop\\exercise\\images\\" + name + "_gray.png");
        }
        System.out.println("finish");
    }
}
