package org.wpsoft.dltel.gameboard;

import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 地图信息类
 * Created by 思敏 on 2016/1/24.
 */
public class MapInfo {
    private int[][] mapModel;//地图(0为water 1为land 2为山脉)
    private CCTMXTiledMap ccMap;
    private int rowNumber;      //地图行数
    private int colNumber;      //地图列数
    private List<TileNode> openList;//开启列表
    private List<TileNode> closeList;//关闭列表
    private static final int COST_Land = 10;//平地移动路径评分
    private static final int COST_Mountain = 10;//山地间移动路径评分
    private static final int COST_ClimbMountain = 30;//爬山的路径评分
    private static final int COST_DownMountain = 20; //下山的路径评分

    public MapInfo(int[][] mapModel) {
        this.mapModel = mapModel;
        openList = new ArrayList<>();
        closeList = new ArrayList<>();
        colNumber = mapModel[0].length;
        rowNumber = mapModel.length;
    }

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
                    mapModel[row][col] = 1;
                else if (mask.equals("Mountain"))
                    mapModel[row][col] = 2;
                else if (mask.equals("Water"))
                    mapModel[row][col] = 0;
            }
        }
        openList = new ArrayList<>();
        closeList = new ArrayList<>();
        colNumber = mapModel[0].length;
        rowNumber = mapModel.length;
    }

    public CCTMXTiledMap getCcMap() {
        return ccMap;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public ArrayList<Integer> search(int start, int end) {
        if (start < 0 || end > mapModel[0].length * mapModel.length - 1)
            return null;
        else if (mapModel[getRowNumberById(end)][getColNumberById(end)] == 0)
            return null;
        TileNode sTileNode = new TileNode(start, null);
        TileNode eTileNode = new TileNode(end, null);
        openList.add(sTileNode);
        ArrayList<TileNode> resultList = search(sTileNode, eTileNode);
        refreshList();
        if (resultList.size() == 0)
            return null;
        else {
            ArrayList<Integer> target = new ArrayList<>();
            for (TileNode i : resultList) {
                target.add(i.getTileId());
            }
            return target;
        }

    }

    private void refreshList() {
        openList.clear();
        closeList.clear();
    }

    //查找核心算法
    private ArrayList<TileNode> search(TileNode sTileNode, TileNode eTileNode) {
        ArrayList<TileNode> resultList = new ArrayList<>();
        boolean isFind = false;
        TileNode TileNode = null;
        while (openList.size() > 0) {
            // System.out.println(openList);
            //取出开启列表中最低F值，即第一个存储的值的F为最低的
            TileNode = openList.get(0);
            //判断是否找到目标点
            if (TileNode.getTileId() == eTileNode.getTileId()) {
                isFind = true;
                break;
            }
            //上
            if ((getRowNumberById(TileNode.getTileId())) - 1 >= 0) {
                int targetId = TileNode.getTileId() - colNumber;
                checkPath(targetId, TileNode, eTileNode, checkCost(TileNode.getTileId(), targetId));
            }
            //下
            if ((getRowNumberById(TileNode.getTileId()) + 1) <= rowNumber - 1) {
                int targetId = TileNode.getTileId() + colNumber;
                checkPath(targetId, TileNode, eTileNode, checkCost(TileNode.getTileId(), targetId));
            }
            //左
            if ((getColNumberById(TileNode.getTileId())) - 1 >= 0) {
                int targetId = TileNode.getTileId() - 1;
                checkPath(targetId, TileNode, eTileNode, checkCost(TileNode.getTileId(), targetId));
            }
            //右
            if ((getColNumberById(TileNode.getTileId())) + 1 <= colNumber - 1) {
                int targetId = TileNode.getTileId() + 1;
                checkPath(targetId, TileNode, eTileNode, checkCost(TileNode.getTileId(), targetId));
            }

            //从开启列表中删除
            //添加到关闭列表中
            closeList.add(openList.remove(0));
            //开启列表中排序，把F值最低的放到最底端
            Collections.sort(openList);
            //System.out.println(openList);
        }
        if (isFind) {
            getPath(resultList, TileNode);
        }
        return resultList;
    }

    private int checkCost(int startId, int targetId) {
        if (mapModel[getRowNumberById(startId)][getColNumberById(startId)] == 1) {
            if (mapModel[getRowNumberById(targetId)][getColNumberById(targetId)] == 2)
                return COST_ClimbMountain;
            else
                return COST_Land;
        } else {
            if (mapModel[getRowNumberById(targetId)][getColNumberById(targetId)] == 1)
                return COST_DownMountain;
            else
                return COST_Mountain;
        }
    }

    //查询此路是否能走通
    private boolean checkPath(int id, TileNode parentTileNode, TileNode eTileNode, int cost) {
        TileNode TileNode = new TileNode(id, parentTileNode);
        //查找地图中是否能通过，暂时的判断条件是有水不能过
        if (mapModel[getRowNumberById(id)][getColNumberById(id)] == 0) {
            closeList.add(TileNode);
            return false;
        }
        //查找关闭列表中是否存在
        if (isListContains(closeList, id) != -1) {
            return false;
        }
        //查找开启列表中是否存在
        int index = -1;
        if ((index = isListContains(openList, id)) != -1) {
            //G值是否更小，即是否更新G，F值
            if ((parentTileNode.getG() + cost) < openList.get(index).getG()) {
                TileNode.setParentNode(parentTileNode);
                countG(TileNode, eTileNode, cost);
                countF(TileNode);

                openList.set(index, TileNode);
            }
        } else {
            //添加到开启列表中
            TileNode.setParentNode(parentTileNode);
            count(TileNode, eTileNode, cost);
            openList.add(TileNode);
        }
        return true;
    }

    //集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
    private int isListContains(List<TileNode> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            TileNode TileNode = list.get(i);
            if (TileNode.getTileId() == id) {
                return i;
            }
        }
        return -1;
    }

    //从终点往返回到起点
    private void getPath(List<TileNode> resultList, TileNode TileNode) {
        if (TileNode.getParentNode() != null) {
            getPath(resultList, TileNode.getParentNode());
        }
        resultList.add(TileNode);
    }

    //计算G,H,F值
    private void count(TileNode TileNode, TileNode eTileNode, int cost) {
        countG(TileNode, eTileNode, cost);
        countH(TileNode, eTileNode);
        countF(TileNode);
    }

    //计算G值,计算到离开起始点的消耗
    private void countG(TileNode TileNode, TileNode eTileNode, int cost) {
        if (TileNode.getParentNode() == null) {
            TileNode.setG(cost);
        } else {
            TileNode.setG(TileNode.getParentNode().getG() + cost);
        }
    }

    //计算H值，计算离目标节点的消耗，采用曼哈顿距离计算
    private void countH(TileNode TileNode, TileNode eTileNode) {
        TileNode.setF((Math.abs(getColNumberById(TileNode.getTileId()) - getColNumberById(eTileNode.getTileId())) +
                Math.abs(getRowNumberById(TileNode.getTileId() - getRowNumberById(eTileNode.getTileId())))) * 10);
    }

    //计算F值
    private void countF(TileNode TileNode) {
        TileNode.setF(TileNode.getG() + TileNode.getH());
    }

    //获取到节点是第几行
    public int getRowNumberById(int id) {
        return id / colNumber;
    }

    //获取节点是第几列
    public int getColNumberById(int id) {
        return id % colNumber;
    }
}
