package com.xxj.model;

/**
 * 单元格类
 * 有两种状态：0 无牌；1 有牌
 */
public class Cell {

    public Integer getState() {
        return state;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    private Layer layer;

    private Integer state = 0;

    private Brand brand;


}
