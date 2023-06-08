package com.xxj.view;

import com.xxj.model.*;
import com.xxj.util.MapUtil;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 程序的启动类
 */
public class Start extends JFrame {

    public static Map map;


    public Start() throws HeadlessException {
        init();
        //渲染图层
        renderMap();
        //游戏开始时判断一次图片的颜色
        map.compareAll();

        //启动自动刷新界面
        autoRefresh();

        //加入背景音乐
        try {
            new Music().music();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public static Integer input = 3;
    public static Boolean isIn = false;
    public void init() {
        JTextField textField = new JTextField();
        JButton sendButton = new JButton("输入难度级别");
        // 将按钮添加点击事件监听器
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当点击按钮时，将文本框内的数字添加到map中去
                try {
                    int num = Integer.parseInt(textField.getText());
                    input = num;
                    isIn = true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Start.this, "输入的不是数字！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // 将文本框和按钮添加到窗口
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        add(panel);
        // 设置窗口大小和显示位置
        setSize(300, 100);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        while (isIn != true) {
            System.out.println("continue");
        }
        panel.setVisible(false);


        System.out.println(input);
        this.setTitle("养了个羊游戏");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口的同时关闭程序
        //设置 绝对布局
        this.setLayout(null);
        this.setBounds(0, 0, 800, 800);
        this.setLocationRelativeTo(null);//窗口居中
        this.setVisible(true);
    }


    public void renderMap() {
        map = MapUtil.build(input);
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

    private void autoRefresh() {
        Start start = this;

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
        new Start();
    }
}
