package com.xxj.model;

import java.util.Random;

/**
 * 图层类
 * 二维表格
 */
public class Layer {

    public Layer getParent() {
        return parent;
    }

    public void setParent(Layer parent) {
        this.parent = parent;
    }

    private Layer parent;//当前图层的上一层

    public Integer getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Integer offsetX) {
        this.offsetX = offsetX;
    }

    private Integer offsetX;//偏移量

    public Integer getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    private Integer offsetY;//偏移量

    private Integer rowNum;//有多少行

    private Integer colNum;//有多少列

    private Integer capacity;//当前图层最多容纳的牌数量

    private Integer size;//当前图层有多少牌，当牌添加和减少时要动态改变

    private Cell[][] cells = null;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private Map map;

    public Layer(Integer rowNum, Integer colNum) throws Exception {
        this.rowNum = rowNum;

        this.colNum = colNum;

        this.capacity = rowNum * colNum;

        if (this.capacity % 3 != 0) {
            throw new Exception("容量不是3的倍数");
        }

        this.cells = new Cell[rowNum][colNum];

        this.size = 0;

        this.offsetX = 0;

        this.offsetY = new Random().nextInt(100);
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
