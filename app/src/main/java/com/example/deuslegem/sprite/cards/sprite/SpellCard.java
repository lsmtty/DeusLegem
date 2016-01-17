package com.example.deuslegem.sprite.cards.sprite;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 魔法卡牌抽象类
 * Created by 思敏 on 2016/1/16.
 */
public abstract  class SpellCard extends Card
{
    public SpellCard(int cost, String url, String name, boolean user)
    {
        super(cost,url,name,user);
    }
    //使用魔法卡牌
    public abstract void takeSpell(CGPoint [] target);
    public abstract void takeSpell(CCSprite [] target);

    @Override
    public void play(CGPoint[] target) {
        takeSpell(target);
    }

    @Override
    public void play(CCSprite[] target) {
        takeSpell(target);
    }
}
