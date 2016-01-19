package com.example.deuslegem.sprite.traps;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 陷阱牌基类
 * Created by 思敏 on 2016/1/18.
 */
public abstract class Trap extends CCSprite
{
    private int id;
    private String url;       //图片地址
    public boolean user;      //创建者
    public CGPoint point;     //创建地点
    public Trap(int id,String url,boolean user,CGPoint point)
    {
        super(url);
        this.id = id;
        this.user = user;
        this.point = point;
    }

    protected abstract void isTriggered(); //触发
}
