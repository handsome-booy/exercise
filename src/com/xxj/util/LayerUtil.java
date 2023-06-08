package com.xxj.util;

import com.xxj.model.Brand;
import com.xxj.model.Cell;
import com.xxj.model.Layer;

public class LayerUtil {

    public static Layer build(Integer rowNum, Integer colNum) {
        Layer layer = null;//容量必须是3的倍数
        try {
            layer = new Layer(rowNum, colNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Brand[] brands = BrandUtil.buildBrands(layer.getCapacity());

        int flag = 0;

        //把数组填充到cells中
        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                Brand brand = brands[flag++];
                Cell cell = new Cell();
                cell.setState(1);
                cell.setBrand(brand);
                cell.setLayer(layer);
                brand.setCell(cell);
                cells[row][col] = cell;
            }
        }

        return layer;
    }
}
