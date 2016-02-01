package com.example.deuslegem.layer;

import android.graphics.drawable.GradientDrawable;
import android.os.Debug;

import org.cocos2d.actions.instant.CCFlipY;
import org.cocos2d.actions.tile.CCTurnOffTiles;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.transitions.CCFlipAngularTransition;
import org.cocos2d.transitions.CCFlipYTransition;
import org.cocos2d.transitions.CCJumpZoomTransition;
import org.cocos2d.transitions.CCMoveInBTransition;
import org.cocos2d.transitions.CCPageTurnTransition;
import org.cocos2d.transitions.CCRadialCWTransition;
import org.cocos2d.transitions.CCRotoZoomTransition;
import org.cocos2d.transitions.CCShrinkGrowTransition;
import org.cocos2d.transitions.CCSplitColsTransition;
import org.cocos2d.transitions.CCSplitRowsTransition;
import org.cocos2d.transitions.CCTransitionScene;
import org.cocos2d.transitions.CCTurnOffTilesTransition;
import org.cocos2d.transitions.CCZoomFlipAngularTransition;
import org.cocos2d.transitions.CCZoomFlipXTransition;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 思敏 on 2016/1/30.
 */
public class SplashLayer extends BaseLayer
{
    private CCTransitionScene transition;
    public SplashLayer()
    {
        setIsTouchEnabled(false);
        initView();

    }

    private void initView() {
        CCSprite sprite = CCSprite.sprite("image/Deus Lesem.png");
        sprite.setAnchorPoint(0.5f, 0.5f);
        sprite.setScale(0.5f);
        sprite.setPosition(winSize.getWidth() / 2, winSize.getHeight() / 2);
        this.addChild(sprite, 0);
        jumpToMainScene();

    }
    private void jumpToMainScene() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                CCScene ccScene = CCScene.node();
                ccScene.addChild(new PlayLayer("TestMap.tmx"));
                //时间这里以秒为单位，而不是毫秒
                //transition = CCJumpZoomTransition.transition(2, ccScene); //跳跃进入和出现
                //Debug.startMethodTracing();
                transition = CCFadeTransition.transition(2,ccScene);  //渐隐效果 不错
                //transition = CCRadialCWTransition.transition(2,ccScene);//扇形消失
                //transition = CCFlipYTransition.transition(2,ccScene,0); //上下翻转   0或非0 最后为翻转方向
                //transition = CCZoomFlipAngularTransition.transition(2,ccScene,0);//变焦翻转  0或非0
                //transition = CCFlipAngularTransition.transition(2,ccScene,0);      //和变焦类似 0或非0
                //transition = CCZoomFlipXTransition.transition(2,ccScene,0);//按x轴切换
                //transition = CCRotoZoomTransition.transition(2,ccScene);     //旋转缩小淡出 旋转缩小进入 amazing
                //transition = CCMoveInBTransition.transition(2,ccScene);        //从下淡入遮盖
                //transition = CCTurnOffTilesTransition.transition(2,ccScene);     //莫名其妙
                //transition = CCSplitColsTransition.transition(2,ccScene);
                //transition = CCSplitRowsTransition.transition(5,ccScene);
                // transition = CCShrinkGrowTransition.transition(2,ccScene);        //交错动画
                //transition = CCPageTurnTransition.transition(5,ccScene,true);
                CCDirector.sharedDirector().replaceScene(transition);
                //Debug.stopMethodTracing();
            }
        }, 2000);
    }
}
