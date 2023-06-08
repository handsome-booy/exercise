package com.xxj.test;

import com.xxj.model.*;
import com.xxj.util.LayerUtil;
import com.xxj.util.MapUtil;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 测试渲染一个地图的工具
 */
public class TestRenderMap extends JFrame {
    public static Map map = MapUtil.build(3);

    public TestRenderMap() throws FileNotFoundException, JavaLayerException {
        //初始化窗口基本信息
        init();

        //渲染图层
        renderMap();
        //游戏开始时判断一次图片的颜色
        map.compareAll();

        //自动刷新
        autoRefresh();

        new Music().music();
    }

    public void renderMap() {
        List<Layer> list = map.getList();
        for (int i = 0; i < list.size(); i++) {
            renderLayer(list.get(i));
        }
    }

    public void renderLayer(Layer layer) {

        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                Brand brand = cells[row][col].getBrand();
                int x = col * 50 + layer.getOffsetX();
                int y = row * 50 + layer.getOffsetY();
                brand.setBounds(x, y, 50, 50);//改变图片位置
                this.getContentPane().add(brand);//把数组中的牌加到渲染图中
            }
        }
    }

    public void init() {
        this.setTitle("养了个羊游戏");
        this.setSize(450, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的同时关闭程序
        //设置 绝对布局
        this.setLayout(null);
        this.setBounds(0, 0, 450, 800);
        this.setLocationRelativeTo(null);//窗口居中
        this.setVisible(true);
    }

    private void autoRefresh() {
        JFrame start = this;

        //创建一个线程去刷新界面
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    start.repaint();

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        new TestRenderMap();
    }
}
