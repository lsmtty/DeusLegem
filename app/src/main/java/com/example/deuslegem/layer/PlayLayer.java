package com.example.deuslegem.layer;

import android.net.LinkAddress;
import android.util.Log;
import android.view.MotionEvent;

import com.example.deuslegem.utils.CommonUtils;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;

/**
 * Created by 思敏 on 2016/1/16.
 */
public class PlayLayer extends  BaseLayer
{
    private CCTMXTiledMap map;
    private CCSprite zombie;
    private CGPoint target;
    private int standTile = -1;
    private ArrayList<CGPoint> land ;
    private ArrayList<CGPoint> water;
    private ArrayList<CGPoint> mountain;
    private ArrayList<Integer> landTile;
    private ArrayList<Integer> waterTile;
    private ArrayList<Integer> mountainTile;
    private boolean actionFinsih = true;
    public PlayLayer()
    {
        setIsTouchEnabled(true);
        init();
    }


    private void init() {
        loadMap();
        loadZombie();
    }

    private void loadZombie() {
        zombie = CCSprite.sprite("z_1_attack_01.png");
        zombie.setFlipX(true);
        zombie.setAnchorPoint(0.5f, 0.5f);
        zombie.setPosition(land.get(0));
        standTile = 0; //设置起始格子id为0
    }

    private void loadMap() {
        map = CCTMXTiledMap.tiledMap("image/TestMap.tmx");
        map.setAnchorPoint(0.5f, 0.5f);
        CGSize size = map.getContentSize();
        map.setPosition(size.getWidth() / 2, size.getHeight() / 2);
        CCTMXLayer layer = map.layerNamed("MapTest");
        land = (ArrayList)CommonUtils.getMapPoints(map,"land");
        landTile = (ArrayList) CommonUtils.getTileId(layer, land);
        water = (ArrayList)CommonUtils.getMapPoints(map,"water");
        waterTile = (ArrayList) CommonUtils.getTileId(layer, water);
        mountain = (ArrayList)CommonUtils.getMapPoints(map,"mountain");
        mountainTile = (ArrayList) CommonUtils.getTileId(layer, mountain);

        this.addChild(map);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        if(actionFinsih)
        {
            target = this.convertPrevTouchToNodeSpace(event); //转换成Cocos2d的坐标显示
            CCTMXLayer layer = map.layerNamed("Tile Layer 2");
            int targetTile = layer.tileGIDAt(target);
            Log.i("id", "第几个点" + targetTile);
            ArrayList<Integer> path = findPath(layer,standTile, targetTile);
            if(path!=null)
            {
                for (Integer i:path) {
                    //找到tile的坐标
                    CGPoint point = layer.tileset.rectForGID(i).origin;
                    zombie.setPosition(point);
                    standTile = i;
                }
            }
            return  true;
        }
        return super.ccTouchesBegan(event);
    }

    /**
     * 获得一条最短路径Id，如果不可达 返回Null
     * @param layer          图层
     * @param standTile      现在的位置
     * @param targetTile    目标地点
     * @return
     */
    private ArrayList<Integer> findPath(CCTMXLayer layer,int standTile, int targetTile)
    {
        return null;
    }
}
