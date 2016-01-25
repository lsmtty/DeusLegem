package org.wpsoft.dltel.system;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.sss.Skill;

/**
 * 表示一张卡牌
 * Created by 思敏 on 2016/1/16.
 */
public abstract class Card extends CCSprite {
    private int id;
    private String imagePath;
    private Deck deck;
    private CardState state;

    public abstract CardType getCardType();

    /**
     * 使用条件检查前进行的准备工作，例如高亮画面某些区域
     *
     * @param isSystemAutoCheck True时表示这是系统抽牌后的自动检查。False时表示这是用户即将使用时的检查
     */
    public abstract void beforeUsingCheck(boolean isSystemAutoCheck);

    /**
     * 使用条件检查
     *
     * @return 是否可以使用这张卡牌
     */
    public abstract boolean usingCheck();

    /**
     * 使用条件检查后进行的收尾工作，例如取消画面某些区域的高亮
     *
     * @param isSystemAutoCheck True时表示这是系统抽牌后的自动检查。False时表示这是用户即将使用时的检查
     * @param checkingResult    使用条件检查结果
     */
    public abstract void afterUsingCheck(boolean isSystemAutoCheck, boolean checkingResult);

    /**
     * 使用这张卡牌
     */
    public abstract void use();

    /**
     * 获取卡牌的ID
     *
     * @return 卡牌的ID
     */
    public int getId() {
        return id;
    }

    /**
     * 获取卡牌图像的路径
     *
     * @return 获取卡牌图像的路径
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 获取卡牌所在的牌组
     *
     * @return 卡牌所在的牌组
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * 设置卡牌所在的牌组
     * @param deck 目标牌组
     */
    void setDeck(Deck deck){
        this.deck = deck;
    }

    /**
     * 获取卡牌当前的状态
     * @return 卡牌当前的状态
     */
    public CardState getState() {
        return state;
    }

    /**
     * 设置卡牌当前的状态
     * @param state 目标状态
     */
    void setState(CardState state) {
        this.state = state;
    }

    /**
     * 获得一张新的卡牌。卡牌将会被随机分配一个新的UUID
     *
     * @param imagePath 卡牌图像的路径
     */
    public Card(int id, String imagePath) {
        super(imagePath);
        this.id = id;
        this.imagePath = imagePath;
    }
}
