package com.example.deuslegem.sprite.cards.sprite;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 卡牌抽象类
 * Created by 思敏 on 2016/1/16.
 */
public abstract  class Card extends CCSprite
{
    private int cost;     //卡牌花费
    private String url;   //贴图地址
    private String name;  //卡牌名称
    private boolean user; //属于的用户 true  or   false标志的两个用户

    public abstract void get();     // 获得时触发的事件
    public abstract void hold();    // 持有时触发的事件
    public abstract void play();
    public abstract void play(CGPoint [] target);
    public abstract void play(CCSprite [] target);   // 打出时触发的事件
    public abstract void discard(); // 丢弃时触发的事件

    public Card(int cost, String url, String name, boolean user) {
        super(url);
        this.url = url;
        this.cost = cost;
        this.name = name;
        this.user = user;
    }
}
