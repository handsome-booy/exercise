package com.xxj.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberInputProgram extends JFrame {

    private JTextField textField;
    private JButton sendButton;
    private int[] numArray = new int[10]; // 后台数组

    public NumberInputProgram() {
        // 设置窗口标题
        setTitle("数字输入程序");
        // 定义文本框和按钮
        textField = new JTextField();
        sendButton = new JButton("发送");
        // 将按钮添加点击事件监听器
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当点击按钮时，将文本框内的数字添加到数组中
                try {
                    int num = Integer.parseInt(textField.getText());
                    numArray[num % 10]++;
                    System.out.println(num);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumberInputProgram.this, "输入的不是数字！", "错误", JOptionPane.ERROR_MESSAGE);
                }
                textField.setText(""); // 清空文本框
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NumberInputProgram();
    }
}

