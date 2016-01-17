package com.example.deuslegem.layer;

import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.types.CGSize;

/**
 * Created by 思敏 on 2016/1/16.
 */
public class PlayLayer extends  BaseLayer
{
    private CCTMXTiledMap map;
    public PlayLayer()
    {
        init();
    }

    private void init() {
        loadMap();
    }

    private void loadMap() {
        map = CCTMXTiledMap.tiledMap("image/map_day.tmx");
        map.setAnchorPoint(0.5f,0.5f);
        CGSize size = map.getContentSize();
        map.setPosition(size.getWidth()/2,size.getHeight()/2);
        this.addChild(map);
    }
}
