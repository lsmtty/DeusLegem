package org.wpsoft.dltel.gameboard;


/**
 * 地图节点
 * Created by 思敏 on 2016/1/24.
 */
public final class TileNode implements Comparable<TileNode> {
    private int tileId;
    private TileNode parentNode;    //父节点
    private int g;  //g值
    private int h;  //h值
    private int f;  //f值

    public TileNode(int tileId, TileNode parentNode) {
        this.tileId = tileId;
        this.parentNode = parentNode;
    }

    public int getTileId() {
        return tileId;
    }

    public TileNode getParentNode() {
        return parentNode;
    }

    void setParentNode(TileNode parentNode) {
        this.parentNode = parentNode;
    }

    public int getG() {
        return g;
    }

    void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    void setF(int f) {
        this.f = f;
    }

    public String toString() {
        return "(" + tileId + "," + f + ")";
    }

    @Override
    public int compareTo(TileNode another) {
        return this.getF() - another.getF();
    }
}

