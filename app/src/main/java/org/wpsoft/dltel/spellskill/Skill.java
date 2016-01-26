package org.wpsoft.dltel.spellskill;

import org.wpsoft.dltel.deck.Card;

import java.io.Serializable;

/**
 * 表示一个技能
 * Created by WinUP on 2016/1/20.
 */
public abstract class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    private Card card;

    /**
     * 获取技能对应的卡牌
     * @return 技能对应的卡牌
     */
    public Card getCard() {
        return card;
    }

    /**
     * 设置技能对应的卡牌
     * @param card 目标卡牌
     */
    public Card setCard(Card card){
        this.card = card;
        return card;
    }

    /**
     * 咏唱技能
     * @param parameter 咏唱参数
     * @return 咏唱结果
     */
    public abstract Object spell(Object... parameter);
}
