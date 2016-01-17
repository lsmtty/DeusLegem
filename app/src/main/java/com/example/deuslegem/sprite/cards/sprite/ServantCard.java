package com.example.deuslegem.sprite.cards.sprite;

import org.cocos2d.types.CGPoint;

/**
 * 从者卡牌抽象类
 * Created by 思敏 on 2016/1/16.
 */
public abstract  class ServantCard extends  Card
{
    public ServantCard(int cost, String url, String name, boolean user)
    {
        super(cost,url,name,user);
    }

    /**
     * 创建从者
     * @param target   目标节点列表 可能不止一个
     */
    public abstract void createServant(CGPoint [] target);

    @Override
    public void play(CGPoint [] target) {
        createServant(target);
    }
}
