package com.xxj.util;

import com.xxj.model.Brand;

import java.util.Random;

public class BrandUtil {

    public static Random random = new Random();

    public static String[] brandNames = new String[] {
            "刷子", "剪刀", "叉子",
            "手套", "水桶", "火",
            "玉米", "粉线球", "瓶子",
            "白菜", "稻草", "木桩",
            "胡萝卜", "白棉花", "青草", "铃铛"
    };

    //随机取牌
    public static String getBrandName() {
        int randomIndex = random.nextInt(brandNames.length);
        return brandNames[randomIndex];
    }

    //创建随机牌的数组
    public static Brand[] buildBrands(Integer capacity) {
        Brand[] brands = new Brand[capacity];

        //加入brands数组中的某一类牌型必须要是3的倍数，因此需要生成一张牌的时候直接生成三个
        for (int i = 0; i < capacity; i = i + 3) {
            String randomBrandName = getBrandName();//每次循环随机获得一张牌的名字
            Brand brand1 = new Brand(randomBrandName);//实例化当前那张牌
            Brand brand2 = new Brand(randomBrandName);
            Brand brand3 = new Brand(randomBrandName);
            brands[i] = brand1;//将产生的牌放进牌的数组中
            brands[i+1] = brand2;
            brands[i+2] = brand3;
        }


        //将brands数组的位置打乱
        for (int i = 0; i < capacity; i++) {
            //当前位置的牌
            Brand brand = brands[i];
            //交换位置的索引
            int randomIndex = random.nextInt(brands.length);
            Brand brandEx = brands[randomIndex];
            brands[i] = brandEx;
            brands[randomIndex] = brand;
        }

        return brands;
    }

}
