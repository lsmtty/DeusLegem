package com.example.deuslegem.utils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFlipXTransition;

import java.util.ArrayList;

/**
 * Created by 思敏 on 2016/1/16.
 */
public class CommonUtils
{
    /**
     * 切换图层
     * @param newLayer 新进入的图层
     */
    public static void changeLayer(CCLayer newLayer)
    {
        CCScene scene  = CCScene.node();
        scene.addChild(newLayer);
        CCFlipXTransition transition = CCFlipXTransition.transition(2,scene,0);
        CCDirector.sharedDirector().replaceScene(transition);
    }

    /**
     * 创建了序列帧的动作
     * @param format          格式化的路径
     * @param num           帧数
     * @param isForever     是否永不停止的循环
     * @return
     */
    public static CCAction getAnimate(String format,int num,boolean isForever)
    {
        ArrayList<CCSpriteFrame> frames = new ArrayList<>();
        //String format="image/loading/loading_%02d.png";
        for(int i = 1;i<=num;i++)
        {
            CCSpriteFrame spriteFrame = CCSprite.sprite(String.format(format,i)).displayedFrame();
            frames.add(spriteFrame);
        }
        CCAnimation anim = CCAnimation.animation("",0.2f,frames);
        //序列帧一般必须永不停止的播放
        if(isForever)
        {
            CCAnimate animate  = CCAnimate.action(anim);
            CCRepeatForever repeatForever = CCRepeatForever.action(animate);
            return  repeatForever;
        }else
        {
            CCAnimate animate = CCAnimate.action(anim,false);
            return  animate;
        }
    }
}
