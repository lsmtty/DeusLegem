package org.wpsoft.dltel.deck;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.spellskill.Skill;
import org.wpsoft.dltel.spellskill.SpellParameter;

/**
 * 表示一张卡牌
 * Created by 思敏 on 2016/1/16.
 */
public final class Card extends CCSprite {
    private int id;
    private String imagePath;
    private Deck deck;
    private Skill beforeUsingCheckSkill;
    private Skill usingCheckSkill;
    private Skill afterUsingCheckSkill;
    private Skill useSkill;

    /**
     * 卡牌使用条件检查
     *
     * @param isSystemAutoCheck 该检查是由系统自动发起（例如抽牌后的自动检查），还是用户即将使用时的检查
     * @return 使用条件检查结果
     */
    public boolean usingCheck(boolean isSystemAutoCheck) {
        SpellParameter parameter = new SpellParameter(false);
        beforeUsingCheckSkill.spell(parameter);
        boolean answer = usingCheckSkill.spell(parameter).isCancelNext();
        afterUsingCheckSkill.spell(parameter);
        return !answer;
    }

    /**
     * 使用这张卡牌
     */
    public Card use() {
        if (!usingCheck(true)) return this;
        deck.moveCard(this, CardState.Spelling);
        useSkill.spell(new SpellParameter(false));
        deck.moveCard(this, CardState.InCemetery);
        return this;
    }

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
     *
     * @param deck 目标牌组
     */
    public Card setDeck(Deck deck) {
        this.deck = deck;
        return this;
    }

    /**
     * 获取卡牌当前的状态
     *
     * @return 卡牌当前的状态
     */
    public CardState getState() {
        return deck.getCardState(this);
    }

    /**
     * 设置卡牌当前的状态
     *
     * @param state 目标状态
     */
    public Card setState(CardState state) {
        deck.setCardState(this, state);
        return this;
    }

    /**
     * 获得一张新的卡牌
     *
     * @param id                        卡牌ID
     * @param imagePath                 卡牌图像路径
     * @param beforeUsingCheckSkillPath 卡牌使用检查前技能的路径
     * @param usingCheckSkillPath       卡牌使用检查技能的路径
     * @param afterUsingCheckSkillPath  卡牌使用检查后技能的路径
     * @param useSkillPath              卡牌使用技能路径
     */
    public Card(int id, String imagePath, String beforeUsingCheckSkillPath, String usingCheckSkillPath, String afterUsingCheckSkillPath, String useSkillPath) {
        super(imagePath);
        this.id = id;
        this.imagePath = imagePath;
        try {
            this.beforeUsingCheckSkill = Skill.loadSkill(beforeUsingCheckSkillPath, SkillType.Normal, 1);
            this.usingCheckSkill = Skill.loadSkill(afterUsingCheckSkillPath, SkillType.Normal, 1);
            this.afterUsingCheckSkill = Skill.loadSkill(usingCheckSkillPath, SkillType.Normal, 1);
            this.useSkill = Skill.loadSkill(useSkillPath, SkillType.Normal, 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}