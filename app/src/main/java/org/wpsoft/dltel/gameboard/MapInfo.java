package org.wpsoft.dltel.gameboard;

import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.types.CGSize;

import java.util.*;

/**
 * 地图信息类
 * Created by 思敏 on 2016/1/24.
 */
public final class MapInfo {
    private int[][] mapModel;//地图(1为water 2为land 4为山脉)
    private CCTMXTiledMap ccMap;
    private int rowNumber;      //地图行数
    private int colNumber;      //地图列数
    private static final int COST_Land = 10;//平地移动路径评分
    private static final int COST_Mountain = 10;//山地间移动路径评分
    private static final int COST_ClimbMountain = 30;//爬山的路径评分
    private static final int COST_DownMountain = 20; //下山的路径评分

    @Deprecated
    public MapInfo(int[][] mapModel) {
        this.mapModel = mapModel;
        colNumber = mapModel[0].length;
        rowNumber = mapModel.length;
    }

    /**
     * 获得一张新的地图
     *
     * @param mapPath 地图文件路径（必须位于assets/map下）
     */
    public MapInfo(String mapPath) {
        CCTMXTiledMap map = CCTMXTiledMap.tiledMap("map/" + mapPath);
        ccMap = map;
        String[] masks = new String[]{"Landscape", "Mountain", "Water"};
        CGSize mapSize = map.getMapSize();
        CGSize tileSize = map.getTileSize();
        for (String mask : masks) {
            for (HashMap<String, String> node : map.objectGroupNamed(mask).objects) {
                int row = (int) ((Integer.parseInt(node.get("x")) + 0.5) / tileSize.getWidth());
                int col = (int) mapSize.getHeight() - 1 - (int) ((Integer.parseInt(node.get("y")) + 0.5) / tileSize.getHeight());
                if (mask.equals("Landscape"))
                    mapModel[row][col] = 2;
                else if (mask.equals("Mountain"))
                    mapModel[row][col] = 4;
                else if (mask.equals("Water"))
                    mapModel[row][col] = 1;
            }
        }
        colNumber = mapModel[0].length;
        rowNumber = mapModel.length;
    }

    /**
     * 获取地图的可渲染对象
     *
     * @return 地图的可渲染对象
     */
    public CCTMXTiledMap getCcMap() {
        return ccMap;
    }

    /**
     * 获取地图行数
     *
     * @return 地图行数
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * 获取地图列数
     *
     * @return 地图列数
     */
    public int getColNumber() {
        return colNumber;
    }

    /**
     * 获取地图模型
     * 在地图模型中，0表示水域，1表示陆地，2表示山脉
     *
     * @return 地图模型
     */
    public int[][] getMapModel() {
        return mapModel;
    }

    /**
     * 获取从指定ID的点到目标ID的点的最适合路径
     *
     * @param start 起始点的ID
     * @param end   终点的ID
     * @param mask  可行走的点的mask
     * @return 目标路径
     */
    public LinkedList<Integer> navigate(int start, int end, int mask) {
        if (start < 0 || end > mapModel[0].length * mapModel.length - 1)
            return null;
        else if ((mapModel[parseRowNumber(end)][parseColNumber(end)] & mask) == 0)
            return null;
        TileNode startTileNode = new TileNode(start, null);
        TileNode endTileNode = new TileNode(end, null);
        LinkedList<TileNode> openList = new LinkedList<>();
        LinkedList<TileNode> closeList = new LinkedList<>();
        openList.add(startTileNode);
        boolean isFind = false;
        TileNode tileNode = null;
        while (openList.size() > 0) {
            //取出开启列表中最低F值，即第一个存储的值的F为最低的
            tileNode = openList.get(0);
            //判断是否找到目标点
            if (tileNode.getTileId() == endTileNode.getTileId()) {
                isFind = true;
                break;
            } else {
                //上
                if ((parseRowNumber(tileNode.getTileId())) - 1 >= 0)
                    checkAStarPath(tileNode.getTileId() - colNumber, tileNode, endTileNode, mask, openList, closeList);
                //下
                if ((parseRowNumber(tileNode.getTileId()) + 1) <= rowNumber - 1)
                    checkAStarPath(tileNode.getTileId() + colNumber, tileNode, endTileNode, mask, openList, closeList);
                //左
                if ((parseColNumber(tileNode.getTileId())) - 1 >= 0)
                    checkAStarPath(tileNode.getTileId() - 1, tileNode, endTileNode, mask, openList, closeList);
                //右
                if ((parseColNumber(tileNode.getTileId())) + 1 <= colNumber - 1)
                    checkAStarPath(tileNode.getTileId() + 1, tileNode, endTileNode, mask, openList, closeList);
                //从开启列表中删除并添加到关闭列表中
                closeList.add(openList.remove(0));
                //开启列表中排序，把F值最低的放到最底端
                Collections.sort(openList);
            }
        }
        LinkedList<TileNode> resultList = new LinkedList<>();
        if (isFind) {
            TileNode targetNode = tileNode;
            while(targetNode.getAStarPreciousNode()!=null){
                resultList.add(targetNode);
                targetNode = targetNode.getAStarPreciousNode();
            }
        }
        openList.clear();
        closeList.clear();
        if (resultList.size() == 0)
            return null;
        else {
            LinkedList<Integer> answer = new LinkedList<>();
            for (TileNode e : resultList)
                answer.push(e.getTileId());
            return answer;
        }
    }

    //查询此路是否能走通
    private boolean checkAStarPath(int id, TileNode parentTileNode, TileNode eTileNode, int mask, LinkedList<TileNode> openList, LinkedList<TileNode> closeList) {
        //计算消耗
        int cost;
        if (mapModel[parseRowNumber(parentTileNode.getTileId())][parseColNumber(parentTileNode.getTileId())] == 2)
            cost = mapModel[parseRowNumber(id)][parseColNumber(id)] == 4? COST_ClimbMountain : COST_Land;
        else
            cost = mapModel[parseRowNumber(id)][parseColNumber(id)] == 2 ? COST_DownMountain : COST_Mountain;
        TileNode TileNode = new TileNode(id, parentTileNode);
        //查找地图中是否能通过
        if ((mapModel[parseRowNumber(id)][parseColNumber(id)]  & mask) == 0) {
            closeList.add(TileNode);
            return false;
        }
        //查找关闭列表中是否存在
        if (advancedIndexOf(closeList, id) != -1) return false;
        //查找开启列表中是否存在
        int index = -1;
        if ((index = advancedIndexOf(openList, id)) != -1) {
            //G值是否更小，即是否更新G，F值
            if ((parentTileNode.getAStarG() + cost) < openList.get(index).getAStarG()) {
                TileNode.setAStarPreviousNode(parentTileNode);
                calculateAStarG(TileNode, cost);
                calculateAStarF(TileNode);
                openList.set(index, TileNode);
            }
        } else {
            //添加到开启列表中
            TileNode.setAStarPreviousNode(parentTileNode);
            calculateAStarG(TileNode, cost);
            calculateAStarH(TileNode, eTileNode);
            calculateAStarF(TileNode);
            openList.add(TileNode);
        }
        return true;
    }

    //集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
    private int advancedIndexOf(List<TileNode> list, int id) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getTileId() == id) return i;
        return -1;
    }

    //计算G值,计算到离开起始点的消耗
    private void calculateAStarG(TileNode TileNode, int cost) {
        if (TileNode.getAStarPreciousNode() == null) {
            TileNode.setAStarG(cost);
        } else {
            TileNode.setAStarG(TileNode.getAStarPreciousNode().getAStarG() + cost);
        }
    }

    //计算H值，计算离目标节点的消耗，采用曼哈顿距离计算
    private void calculateAStarH(TileNode TileNode, TileNode eTileNode) {
        TileNode.setAStarF((Math.abs(parseColNumber(TileNode.getTileId()) - parseColNumber(eTileNode.getTileId())) +
                Math.abs(parseRowNumber(TileNode.getTileId() - parseRowNumber(eTileNode.getTileId())))) * 10);
    }

    //计算F值
    private void calculateAStarF(TileNode TileNode) {
        TileNode.setAStarF(TileNode.getAStarG() + TileNode.getAStarH());
    }

    /**
     * 确定指定ID的节点位于地图的第几行
     *
     * @param id 目标ID
     * @return 位于地图的第几行
     */
    public int parseRowNumber(int id) {
        return id / colNumber;
    }

    /**
     * 确定指定ID的节点位于地图的第几列
     *
     * @param id 目标ID
     * @return 位于地图的第几列
     */
    public int parseColNumber(int id) {
        return id % colNumber;
    }
}
