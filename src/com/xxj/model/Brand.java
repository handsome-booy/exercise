package com.xxj.model;

import com.xxj.test.TestRenderMap;
import com.xxj.view.Start;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Brand代表游戏当中的一个牌
 */
public class Brand extends Component {

    EliminateBox eliminateBox = new EliminateBox();


    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    private Cell cell;

    private String name;//牌的名字，这个名字有两个作用：1.用于三张同样名字的牌来消除；2.用名字来找到对应的图片样式

    private Boolean isGray;//代表当前牌是否是灰色的

    private Image image;//彩色的图片对象

    private Image grayImage;//灰色的图片对象

    private Integer x;//x和y代表当前牌在渲染的时候左上角的坐标
    private Integer y;

    private Integer width;//图片的宽度
    private Integer height;

    @Override
    public String getName() {
        return name;
    }

    public Boolean getGray() {
        return isGray;
    }

    public Image getImage() {
        return image;
    }

    public Image getGrayImage() {
        return grayImage;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setGray(Boolean gray) {
        isGray = gray;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    //name的值对应了images包下面图片的前缀
    public Brand(String name) {
        this.name = name;

        this.image = Toolkit.getDefaultToolkit().getImage("images\\" + name + ".png");
        this.grayImage = Toolkit.getDefaultToolkit().getImage("images\\" + name + "_gray.png");

        this.isGray = false;

        this.width = 50;
        this.height = 50;

        this.x = 0;
        this.y = 0;

        //当发生鼠标点击的时候产生的动作（监听器）
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("brand.mouseClicked");

                Brand brand = (Brand) e.getSource();//获取当前组件
                if (brand.getGray() == true) {
                    return;
                } else {
                    //brand.getParent().remove(brand);//通过父容器删掉自己，只是在ui界面上删掉，所以要删掉这个数据结构
                    eliminateBox.addSlot(brand);//把点过的牌放到消除区域
                    //eliminateBox.winGame(brand);
                    Cell cell = brand.cell;
                    cell.setBrand(null);
                    cell.setState(0);
                    //再判断一次所有图片的颜色
                    Start.map.compareAll();
                }
            }
        });
    }


    /**
     * 绘制当前图片
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if (this.isGray == true) {
            //绘制灰色图片
            g.drawImage(this.grayImage, this.x, this.y, null);
        } else {
            //绘制彩色图片
            g.drawImage(this.image, this.x, this.y, null);
        }

    }
}

