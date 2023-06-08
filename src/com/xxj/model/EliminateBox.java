package com.xxj.model;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消除区域
 */
public class EliminateBox {
    //消除框
    private static List<Brand> slot = new ArrayList<>();

    public void addSlot(Brand brand) {
        slot.add(brand);
        //根据牌名称进行的排序
        slot.sort(Comparator.comparing(Brand::getName));
        //获取牌的名称
        Map<String, List<Brand>> collect = slot.stream().collect(Collectors.groupingBy(Brand::getName));
        Set<String> strings = collect.keySet();
        for (String s: strings) {
            List<Brand> brands = collect.get(s);
            if (brands.size() == 3) {
                //用迭代器清空
                deleteByBrandName(s);
                break;
            }
        }
        paint();//把点击的牌加入到消除框
        over(brand);
    }

    private void deleteByBrandName(String name) {
        Iterator<Brand> iterator = slot.iterator();
        while (iterator.hasNext()) {
            Brand next = iterator.next();
            if (next.getName().equals(name)) {
                next.getParent().remove(next);//从ui界面删除
                iterator.remove();//从数据结构上删除
            }
        }
    }

    //把牌绘制到消除框
    public void paint() {
        for (int i = 0; i < slot.size(); i++) {
            Brand brand = slot.get(i);
            int x = i * brand.getWidth() + 10;
            brand.setBounds(x, 600, 50, 50);//改变图片位置
        }
    }

    public void over(Brand brand) {
         if (slot.size() >= 7) {
             JOptionPane.showMessageDialog(brand, "游戏结束！");
             System.exit(9);
         }
    }

    public static boolean winGame(com.xxj.model.Map map) {
        System.out.println("判断有没有过关");

        if (slot.size() <= 6) {
            //判断图层上是否还有brand
            List<Layer> list = map.getList();
            for (int i = 0; i < list.size(); i++) {
                Layer layer1 = list.get(i);
                Cell[][] cells = layer1.getCells();
                for (int row = 0; row < cells.length; row++) {
                    for (int col = 0; col < cells[0].length; col++) {
                        Integer state = cells[row][col].getState();
                        if (state == 1) {
                            System.out.println("图上还有brand");
                            return false;
                        }
                    }
                }
            }
            System.out.println("游戏过关！！！");
        } else {
            return false;
        }
        return true;
    }
}
