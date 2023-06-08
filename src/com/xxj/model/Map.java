package com.xxj.model;

import com.xxj.util.MapUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个地图 下面有多个图层 层层遮盖
 */
public class Map {



    private Integer floorHeight;//有几个图层

    private List<Layer> list = new ArrayList<>();//存放图层数据

    public Integer getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Integer floorHeight) {
        this.floorHeight = floorHeight;
    }

    public List<Layer> getList() {
        return list;
    }

    public void setList(List<Layer> list) {
        this.list = list;
    }

    /**
     * 判断map中所有牌的颜色
     * 1.游戏开始时调用一次
     * 2.点击牌之后调用一次
     */
    public void compareAll() {
        System.out.println("compareAll");
        //最顶层图层不用判断
        for (int i = 1; i < list.size(); i++) {
            Layer layer = list.get(i);
            Cell[][] cells = layer.getCells();

            for (int row = 0; row < cells.length; row++) {
                for (int col = 0; col < cells[0].length; col++) {
                    if (cells[row][col].getState() == 0) {
                        continue;
                    }
                    Brand brand = cells[row][col].getBrand();
                    boolean compare = MapUtil.compare(brand, layer.getParent());
                    brand.setGray(compare);
                }
            }
        }

    }

}
