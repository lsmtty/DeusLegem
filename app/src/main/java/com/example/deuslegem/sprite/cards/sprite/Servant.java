package com.example.deuslegem.sprite.cards.sprite;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * Created by 思敏 on 2016/1/16.
 */
public abstract class Servant extends CCSprite{
    //Max指的是正常状态（未叠加当前回合增/减益Buff的属性值） current(当前回合叠加增/减益Buff的属性值)
    private  String name;         //名字
    private CGPoint standPoint;   //地图位置
    private int lifePointMax;     //最大血量
    private int lifePointCurrent; //当前血量
    private int movePointMax;     //最大移动距离
    private int movePointCurrent; //当前最大移动距离
    private int attackPointNormal;//攻击点数
    private int attackCurrent;    //当前攻击点数
    private int attackDistanceMax;//最大攻击距离
    private int attackDistanceCurrent;   //当前攻击距离
    private int attackTimesMax;   //可攻击次数
    private int attackTimesCurrent;//当前最大攻击次数
    private int remainAttackTimes;//剩余攻击次数
    private boolean user;         //属于的玩家  true or false

    public Servant(String url,String name,CGPoint standPoint, int lifePointMax, int movePointMax,int attackPoint,
                   int attackDistance,int attackTimes,boolean user)
    {
        super(url);
        this.name = name;
        this.standPoint = standPoint;
        this.lifePointMax = this.lifePointCurrent = lifePointMax;
        this.movePointMax = this.movePointCurrent = movePointMax;
        this.attackPointNormal = this.attackCurrent = attackPoint;
        this.attackDistanceMax = this.attackDistanceCurrent = attackDistance;
        this.attackTimesMax = this.attackTimesCurrent = this.remainAttackTimes = attackTimes;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public CGPoint getStandPoint() {
        return standPoint;
    }

    public int getLifePointMax() {
        return lifePointMax;
    }

    public int getLifePointCurrent() {
        return lifePointCurrent;
    }

    public int getMovePointMax() {
        return movePointMax;
    }

    public int getMovePointCurrent() {
        return movePointCurrent;
    }

    public int getAttackPointNormal() {
        return attackPointNormal;
    }

    public int getAttackCurrent() {
        return attackCurrent;
    }

    public int getAttackDistanceMax() {
        return attackDistanceMax;
    }

    public int getAttackDistanceCurrent() {
        return attackDistanceCurrent;
    }

    public int getAttackTimesMax() {
        return attackTimesMax;
    }

    public int getAttackTimesCurrent() {
        return attackTimesCurrent;
    }

    public int getRemainAttackTimes() {
        return remainAttackTimes;
    }

    public boolean isUser() {
        return user;
    }

    public void setStandPoint(CGPoint standPoint) {
        this.standPoint = standPoint;
    }

    public void setLifePointMax(int lifePointMax) {
        this.lifePointMax = lifePointMax;
    }

    public void setLifePointCurrent(int lifePointCurrent) {
        this.lifePointCurrent = lifePointCurrent;
    }

    public void setMovePointMax(int movePointMax) {
        this.movePointMax = movePointMax;
    }

    public void setMovePointCurrent(int movePointCurrent) {
        this.movePointCurrent = movePointCurrent;
    }

    public void setAttackPointNormal(int attackPointNormal) {
        this.attackPointNormal = attackPointNormal;
    }

    public void setAttackCurrent(int attackCurrent) {
        this.attackCurrent = attackCurrent;
    }

    public void setAttackDistanceMax(int attackDistanceMax) {
        this.attackDistanceMax = attackDistanceMax;
    }

    public void setAttackDistanceCurrent(int attackDistanceCurrent) {
        this.attackDistanceCurrent = attackDistanceCurrent;
    }

    public void setAttackTimesMax(int attackTimesMax) {
        this.attackTimesMax = attackTimesMax;
    }

    public void setAttackTimesCurrent(int attackTimesCurrent) {
        this.attackTimesCurrent = attackTimesCurrent;
    }

    public void setRemainAttackTimes(int remainAttackTimes) {
        this.remainAttackTimes = remainAttackTimes;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public BornListener getBornListener() {
        return bornListener;
    }

    public DieListener getDieListener() {
        return dieListener;
    }

    public interface  DieListener //死亡接口
    {
        void die();
    }

    public interface  BornListener  //出生接口
    {
        void born();
    }

    /**
     * 攻击
     * @param target
     */
    private void attack(CCSprite target)
    {
        while(remainAttackTimes > 0 && this.getAttackCurrent()> 0)
        {
            attackTrigger();
            attackAnimate();
            remainAttackTimes--;
        }
    }
    protected abstract void attackTrigger(); //攻击时触发的特殊效果
    protected abstract void attackAnimate(); //播放攻击动画？是否应该放在这儿

    /**
     * 被攻击
     * @param attacker
     */
    private void attacked(CCSprite attacker)
    {

        if(attacker instanceof  Servant)
        {
            this.setLifePointCurrent(this.lifePointCurrent - ((Servant)attacker).getAttackCurrent());
            if(this.getLifePointCurrent() <= 0)
            {
                this.destory();
            }
        }
    }

    private  BornListener bornListener;//出生监听
    private  DieListener dieListener; // 死亡的监听
    public void setBornListener(BornListener bornListener)
    {
        this.bornListener = bornListener;
    }
    public void setDieListener(DieListener dieListener)
    {
        this.dieListener = dieListener;
    }

    /**
     * 创建
     */
    public void create()
    {
        if(bornListener!=null)
            bornListener.born();
    }

    /**
     * 销毁
     * @return
     */
    public void destory()
    {
        if(dieListener!=null)
            dieListener.die();
        this.removeSelf();
    }
}
