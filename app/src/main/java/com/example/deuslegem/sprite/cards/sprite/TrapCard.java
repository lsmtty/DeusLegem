package com.example.deuslegem.sprite.cards.sprite;

import org.cocos2d.types.CGPoint;

/**
 * 陷阱卡牌抽象类
 * Created by 思敏 on 2016/1/16.
 */
public abstract  class TrapCard extends  Card
{
    public TrapCard(int cost, String url, String name, boolean user)
    {
        super(cost,url,name,user);
    }

    /**
     * 放置陷阱
     * @param target  放置地点，可能不止一个
     */
    public abstract void putTrap(CGPoint [] target);

    @Override
    public void play(CGPoint [] target) {
        putTrap(target);
    }
}
