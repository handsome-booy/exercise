package com.xxj.test;

import com.xxj.model.Brand;
import com.xxj.model.Cell;
import com.xxj.model.Layer;
import com.xxj.util.LayerUtil;

import javax.swing.*;

/**
 * 测试渲染一个图层的工具
 */
public class TestRenderLayer extends JFrame {
    private Layer layer = LayerUtil.build(6, 6);

    public TestRenderLayer() {
        //初始化窗口基本信息
        init();

        //渲染图层
        renderLayer();

        //自动刷新
        autoRefresh();
    }

    public void renderLayer() {
        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                Brand brand = cells[row][col].getBrand();
                int x = col * 50;
                int y = row * 50;
                brand.setBounds(x, y, 50, 50);
                this.getContentPane().add(brand);//把数组中的牌加到渲染图中
            }
        }
    }

    public void init() {
        this.setTitle("养了个羊游戏");
        this.setSize(450, 800);
        this.setLocationRelativeTo(null);//窗口居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的同时关闭程序
        //设置 绝对布局
        this.setLayout(null);
        this.setBounds(0, 0, 450, 800);
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

    public static void main(String[] args) {
        new TestRenderLayer();
    }
}
