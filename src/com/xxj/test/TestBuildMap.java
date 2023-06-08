package com.xxj.test;

import com.xxj.model.Layer;
import com.xxj.model.Map;
import com.xxj.util.LayerUtil;

public class TestBuildMap {

    public static void main(String[] args) {
        Map map = new Map();
        map.setFloorHeight(3);
        Layer layer1 = LayerUtil.build(3, 3);
        Layer layer2 = LayerUtil.build(3, 3);
        Layer layer3 = LayerUtil.build(3, 3);
        map.getList().add(layer1);
        map.getList().add(layer2);
        map.getList().add(layer3);
    }

}
