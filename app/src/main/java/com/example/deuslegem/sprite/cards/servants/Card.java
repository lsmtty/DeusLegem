package com.example.deuslegem.sprite.cards.servants;

import org.cocos2d.nodes.CCSprite;

/**
 * 卡牌基类
 * Created by 思敏 on 2016/1/16.
 */
public abstract  class Card extends CCSprite
{
    private int Cost;     //卡牌花费
    private  String url;  //贴图地址
    private String name;  //开牌名称
}
