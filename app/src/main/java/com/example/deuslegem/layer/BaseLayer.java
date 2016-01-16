package com.example.deuslegem.layer;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

/**
 * Created by 思敏 on 2016/1/16.
 */
public class BaseLayer extends CCLayer
{
    protected CGSize winSize;
    public BaseLayer()
    {
        this.winSize = CCDirector.sharedDirector().getWinSize();
    }
}
