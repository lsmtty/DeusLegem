package org.wpsoft.dltel.gameboard;


/**
 * 地图节点
 * Created by 思敏 on 2016/1/24.
 */
public final class TileNode implements Comparable<TileNode> {
    private int tileId;
    private TileNode aStarPreviousNode;    //父节点
    private int aStarG;  //g值
    private int aStarH;  //h值
    private int aStarF;  //f值

    public TileNode(int tileId, TileNode parentNode) {
        this.tileId = tileId;
        this.aStarPreviousNode = parentNode;
    }

    public int getTileId() {
        return tileId;
    }

    public TileNode getAStarPreciousNode() {
        return aStarPreviousNode;
    }

    void setAStarPreviousNode(TileNode aStarPreviousNode) {
        this.aStarPreviousNode = aStarPreviousNode;
    }

    public int getAStarG() {
        return aStarG;
    }

    void setAStarG(int aStatG) {
        this.aStarG = aStatG;
    }

    public int getAStarH() {
        return aStarH;
    }

    void setAStarH(int aStarH) {
        this.aStarH = aStarH;
    }

    public int getAStarF() {
        return aStarF;
    }

    void setAStarF(int aStarF) {
        this.aStarF = aStarF;
    }

    public String toString() {
        return "(" + tileId + "," + aStarF + ")";
    }

    @Override
    public int compareTo(TileNode another) {
        return this.getAStarF() - another.getAStarF();
    }
}

