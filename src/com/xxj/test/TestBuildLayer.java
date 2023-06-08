package com.xxj.test;

import com.xxj.model.Brand;
import com.xxj.model.Cell;
import com.xxj.model.Layer;

import java.util.Random;


/**
 * 测试图层数据的构建
 */
public class TestBuildLayer {

    public static Random random = new Random();

    public static String[] brandNames = new String[] {
            "刷子", "剪刀", "叉子",
            "手套", "水桶", "火",
            "玉米", "球", "瓶子",
            "白菜", "稻草", "肉腿",
            "胡萝卜", "苹果", "苹果",
            "青草", "铃铛"
    };

    //随机取牌
    public static String getBrandName() {
        int randomIndex = random.nextInt(brandNames.length);
        return brandNames[randomIndex];
    }

    public static void main(String[] args) throws Exception {
        Layer layer = new Layer(6, 6);//容量必须是3的倍数

        Brand[] brands = new Brand[layer.getCapacity()];

        //加入brands数组中的某一类牌型必须要是3的倍数，因此需要生成一张牌的时候直接生成三个
        for (int i = 0; i < brands.length; i = i + 3) {
            String randomBrandName = getBrandName();//每次循环随机获得一张牌的名字
            Brand brand1 = new Brand(randomBrandName);//实例化当前那张牌
            Brand brand2 = new Brand(randomBrandName);
            Brand brand3 = new Brand(randomBrandName);
            brands[i] = brand1;//将产生的牌放进牌的数组中
            brands[i+1] = brand2;
            brands[i+2] = brand3;
        }


        //将brands数组的位置打乱
        for (int i = 0; i < brands.length; i++) {
            //当前位置的牌
            Brand brand = brands[i];
            //交换位置的索引
            int randomIndex = random.nextInt(brands.length);
            Brand brandEx = brands[randomIndex];
            brands[i] = brandEx;
            brands[randomIndex] = brand;
        }

        int flag = 0;
        //把数组填充到cells中
        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                Cell cell = new Cell();
                cell.setState(1);
                cell.setBrand(brands[flag++]);

                cells[row][col] = cell;
            }
        }
    }
}
