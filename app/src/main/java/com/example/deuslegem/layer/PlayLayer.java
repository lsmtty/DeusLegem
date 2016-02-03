package com.example.deuslegem.layer;

import android.util.Log;
import android.view.MotionEvent;

import android.view.animation.Interpolator;
import org.wpsoft.dltel.gameboard.MapInfo;
import com.example.deuslegem.utils.CommonUtils;

import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 思敏 on 2016/1/16.
 */
public class PlayLayer extends BaseLayer {
    private int tileNumber = -1;    //地格数
    private int tileInRow = -1;     //每行地格数
    private int tileInCol = -1;     //每列地格数
    private CCTMXTiledMap map;
    private CCSprite zombie;
    private CGPoint target;
    private int standTile = -1;
    private ArrayList<CGPoint> land;
    private ArrayList<CGPoint> water;
    private ArrayList<CGPoint> mountain;
    private ArrayList<Integer> landTile;
    private ArrayList<Integer> waterTile;
    private ArrayList<Integer> mountainTile;
    private boolean actionFinsih = true;
    private int[][] modelMap;      //抽象地图 1land 2.mountain 3water，为寻路算法设计
    private MapInfo star;             //A* 寻路算法类

    public PlayLayer(String mapName) {
        setIsTouchEnabled(true);
        init(mapName);
    }

    /**
     * 初始化界面元素
     * @param mapName
     */
    private void init(String mapName) {
        loadMap(mapName);
        loadZombie();
    }

    private void loadZombie() {
        zombie = CCSprite.sprite("image/z_1_attack_01.png");
        zombie.setFlipX(true);
        zombie.setAnchorPoint(0.5f, 0.5f);
        Log.i("message", land.get(0).toString());
        zombie.setPosition(CommonUtils.getPointByTileId(map, 127));
        zombie.setScale(0.2462);
        standTile = 127; //设置起始格子id为0
        this.addChild(zombie, 1);
    }

    private void loadMap(String mapName) {
        //getContentSize 获取地图像素为单位的宽和高
        //getMapSize 获取地图的宽和高上的图块数量****易混淆
        //getTileSize 获取图块以像素为单位的宽和高
        //map = CCTMXTiledMap.tiledMap("image/TestMap.tmx");
        map = CCTMXTiledMap.tiledMap("image/"+mapName);
        map.setAnchorPoint(0.5f, 0.5f);
        CGSize size = map.getContentSize();
        map.setPosition(size.getWidth() / 2, size.getHeight() / 2);
        map.setVisible(true);
        this.addChild(map, 0);
        CGSize mapSize = map.getMapSize();
        tileInRow = (int) mapSize.getHeight();
        tileInCol = (int) mapSize.getWidth();
        tileNumber = tileInCol * tileInRow;
        /*CCTMXLayer landLayer = map.layerNamed("LandLayer");
        CCTMXLayer waterLayer = map.layerNamed("WaterLayer");*/
        land = (ArrayList) CommonUtils.getMapPoints(map, "Landscape");
        landTile = (ArrayList) CommonUtils.getTileId(map, land);
        water = (ArrayList) CommonUtils.getMapPoints(map, "Water");
        waterTile = (ArrayList) CommonUtils.getTileId(map, water);
        mountain = (ArrayList) CommonUtils.getMapPoints(map, "Mountain");
        mountainTile = (ArrayList) CommonUtils.getTileId(map, mountain);
        //初始化模型地图
        modelMap = new int[tileInRow][tileInCol];
        int temp = 0;
        for (int i = 0; i < tileInRow; i++) {
            for (int j = 0; j < tileInCol; j++) {
                if (landTile.contains(temp))             //land tile
                    modelMap[i][j] = 2;
                else if (mountainTile.contains(temp))    //mountain tile
                    modelMap[i][j] = 4;
                else if (waterTile.contains(temp))       //water tile
                    modelMap[i][j] = 1;
                temp++;
            }
        }
        star = new MapInfo(modelMap);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        if (actionFinsih)  //判断移动是否结束，只有移动结束后才可以进一步操作
        {
            actionFinsih = false;
            if (event == null)
                Log.i("event", "event is null");
            target = this.convertTouchToNodeSpace(event); //转换成Cocos2d的坐标显示
            int targetTile = CommonUtils.getTileId(map, target);
            final LinkedList<Integer> path = star.navigate(standTile, targetTile, 6);
            if (path != null) {
                if (path.size() != 0) {
                    Log.i("startPoint", "" + standTile);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (Integer i : path) {
                                    //找到tile的坐标
                                    if (i == -1)
                                        break;
                                    CGPoint point = CommonUtils.getPointByTileId(map, i);
                                    Log.i("path", "" + i);
                                    zombie.setPosition(point);
                                    standTile = i;
                                    Thread.sleep(150);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    Log.i("endPoint", "" + targetTile);
                }
            }
            actionFinsih = true;
            return true;
        }
        return super.ccTouchesBegan(event);
    }
}
