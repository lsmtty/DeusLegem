package com.example.deuslegem.sprite.cards.sprite;

import com.example.deuslegem.engine.MatchController;

import org.cocos2d.types.CGPoint;

/**
 * 中枢基类
 * Created by 思敏 on 2016/1/18.
 */
public abstract class BackBone extends  Servant
{
    public BackBone(int id,String url, String name, CGPoint standPoint, int lifePointMax, int movePointMax, int attackPoint, int attackDistance, int attackTimes, boolean user) {
        super(id,url, name, standPoint, lifePointMax, movePointMax, attackPoint, attackDistance, attackTimes, user);
    }

    @Override
    public void destory() {
        super.destory();
        MatchController.getInstance().failed(isUser());
    }

    public abstract void power();  //中枢能力
}
