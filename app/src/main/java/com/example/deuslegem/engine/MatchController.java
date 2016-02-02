package com.example.deuslegem.engine;

import com.example.deuslegem.layer.PlayLayer;
import com.example.deuslegem.utils.MotionControl;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCRotoZoomTransition;
import org.wpsoft.dltel.Player;
import org.wpsoft.dltel.deck.Card;
import org.wpsoft.dltel.deck.Deck;
import org.wpsoft.dltel.deck.Servant;

import java.util.List;
import java.util.UUID;

/**
 * 游戏控制类
 * Created by 思敏 on 2016/1/18.
 */
public class MatchController {
    private Player self;
    private Player competitor;
    public static boolean isStart;// 游戏是否开始
    public static List<Integer> myCards;   //牌库中的卡牌
    public static List<Integer> myServants;   //我的从者列表
    public boolean isMyTurn;               //是否为我方行动时间
    private Deck selfDeck;
    private Deck competitorDeck;
    private Card[] selfCards;
    private Card[] competitorCards;
    private Servant[] selfServants;
    private Servant[] competitorServants;
    private final CCScene ccScene;          //对战场景
    private final MotionControl motionControl;    //动画控制类

    /**
     * 创建对局控制器,由服务器传入数据创建
     *
     * @param self
     * @param competitor
     */
    public MatchController(Player self, Player competitor, String mapName) {
        this.self = self;
        this.competitor = competitor;
        initDeck();
        ccScene = CCScene.node();
        ccScene.addChild(new PlayLayer(mapName));
        //再加入两个臭显示卡牌的Layer
        CCRotoZoomTransition transition = CCRotoZoomTransition.transition(1.5f, ccScene);
        CCDirector.sharedDirector().replaceScene(transition);
        motionControl = new MotionControl(ccScene);
    }

     /**
     * CF
     * 创建双方牌组，?牌组数据如何获得 ？
     */
    private void initDeck() {
        //需要从服务器中获得玩家的卡组信息，待实现
        initCards();
        initServants();
        selfDeck = new Deck(selfCards, selfServants, self);
        competitorDeck = new Deck(competitorCards, competitorServants, competitor);
    }

    /**
     * 初始化双方从者
     */
    private void initServants() {

        selfServants = new Servant[20];
        competitorServants = new Servant[20];
    }

    /**
     * 初始化双方卡牌
     */
    private void initCards() {
        selfCards = new Card[30];
        competitorCards = new Card[30];
    }

    /**
     * 抽牌
     * @param userId
     */
    public void drawDard(UUID userId) {
        if(userId==self.getId())
            selfDeck.draw();
        else
            competitorDeck.draw();
    }

    /**
     * 判断是否为本方随从
     * @return
     */
    private boolean isMyServant(Servant servant)
    {
        if(servant.getDeck().getPlayer().getId()==self.getId())
            return  true;
        else
            return false;
    }
    /*
    //应该不需要单例模式，每次对局都需要重新创新
    public static MatchController controller = new MatchController();

    public static MatchController getInstance()
    {
        return  controller;
    }
*/


    //失败
    public void failed(boolean user) {
        //发送判定消息到服务器，服务器裁定胜负,同时结束匹配
       /* sendData();
        matchComplete();*/
    }

   /* private void matchComplete()
    {
        if(getData());
        {

        }
    }

    //发送数据到服务器
    private void sendData()
    {
        
    }*/

    /**
     * 判断是否为自己回合
     *
     * @return
     */
    public boolean isMyTurn() {
        return isMyTurn;
    }

    //回合开始 服务器控制
    private void turnStart() {
        isMyTurn = true;
    }

    //回合结束
    private void turnOver() {
        isMyTurn = false;
    }
}
