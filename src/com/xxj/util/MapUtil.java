package com.xxj.util;

import com.xxj.model.Brand;
import com.xxj.model.Cell;
import com.xxj.model.Layer;
import com.xxj.model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapUtil {
    public static Map build(Integer floorHeight) {
        Map map = new Map();
        map.setFloorHeight(floorHeight);
        Layer pre = null;
        List<Layer> list = new ArrayList<>();
        for (int i = 0; i < floorHeight; i++) {
            Layer layer = LayerUtil.build(9, 9);//创建layer
            layer.setMap(map);
            layer.setOffsetX(new Random().nextInt(60));//设置layer偏移量
            list.add(layer);
            layer.setParent(pre);//把layer链式拉起来
            pre = layer;
        }


        for (int i = 0; i < list.size(); i++) {
            map.getList().add(list.get(i));
        }

        return map;
    }

    /**
     * 判定当前牌和某一图层内所有牌是否有矩阵交集
     * 如果为true，说明有交集，牌是灰色
     * @param brand
     * @param layer
     * @return
     */
    public static boolean compare(Brand brand, Layer layer) {
        //之前已经是最顶层了，就不用再比较了
        if (layer == null) {
            return false;
        }

        Cell[][] cells = layer.getCells();

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                //如果当前单元格为空，就不用比较
                if (cells[row][col].getState() == 0) {
                    continue;
                }
                Brand brand1 = cells[row][col].getBrand();
                Rectangle rectangle1 = brand1.getBounds();
                Rectangle rectangle = brand.getBounds();

                boolean result = rectangle.intersects(rectangle1);

                if (result) {
                    //有交集
                    return true;
                }
            }
        }
        //当前牌与当前层没有交集，然后再与更上层进行比较
        return compare(brand, layer.getParent());
    }

}
