package com.example.deuslegem.engine;

import org.cocos2d.layers.CCTMXObjectGroup;

import java.util.List;
import java.util.Random;

/**
 * Created by 思敏 on 2016/1/18.
 */
public class MatchController
{
    private MatchController()
    {

    }

    public static MatchController controller = new MatchController();

    public static MatchController getInstance()
    {
        return  controller;
    }

    public static boolean isStart;// 游戏是否开始

    private CCTMXObjectGroup map; //游戏地图
    public static List<Integer> myCards;   //牌库中的卡牌
    public static List<Integer> myServants;   //我的从者列表
    public boolean isMyTurn;            //是否为我方行动时间

    private void setMap(CCTMXObjectGroup map)
    {
        this.map = map;
    }
    private void setMyCards(List<Integer> myCards)
    {
        this.myCards = myCards;
    }

    /**
     * 牌库中添加卡牌
     * @param id  卡牌id
     * @param random    是否为随机添加
     */
    private void addCard(Integer id,boolean random)
    {
        if(myCards!=null)
        {
            if(random)
            {
                int length = myCards.size();
                myCards.add(new Random(length).nextInt(),length);
            }
            else
            {
                myCards.add(id);
            }
        }
    }
    /**
     * 添加Servant
     * @param id
     */
    private void addServant(Integer id)
    {
        if(myServants!=null)
        {
            myServants.add(id);
        }
    }

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

    //回合开始 服务器控制
    private void turnStart()
    {
        isStart = true;
    }

    //回合结束
    private void turnOver()
    {
        isStart = false;
    }
}
