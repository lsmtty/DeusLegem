package org.wpsoft.dltel;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.spellskill.Skill;
import org.wpsoft.dltel.spellskill.SpellService;
import org.wpsoft.dltel.system.CardState;
import org.wpsoft.dltel.system.CardType;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 表示一张卡牌
 * Created by 思敏 on 2016/1/16.
 */
public final class Card extends CCSprite {
    private int id;
    private String imagePath;
    private Deck deck;
    private CardState state;
    private CardType type;
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
        beforeUsingCheckSkill.spell(isSystemAutoCheck);
        boolean answer = (boolean) usingCheckSkill.spell();
        afterUsingCheckSkill.spell(isSystemAutoCheck, answer);
        return answer;
    }

    /**
     * 使用这张卡牌
     */
    public Card use() {
        SpellService.getInstance().spell(useSkill);
        return this;
    }

    /**
     * 将卡牌送去墓地
     */
    public void destroy() {
        SpellService.getInstance().tryCancelSpell(useSkill);
        deck.sendToCemetery(this);
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
    Card setDeck(Deck deck) {
        this.deck = deck;
        return this;
    }

    /**
     * 获取卡牌当前的状态
     *
     * @return 卡牌当前的状态
     */
    public CardState getState() {
        return state;
    }

    /**
     * 设置卡牌当前的状态
     *
     * @param state 目标状态
     */
    Card setState(CardState state) {
        this.state = state;
        return this;
    }

    /**
     * 获取卡牌类型
     *
     * @return 卡牌类型
     */
    public CardType getType() {
        return type;
    }

    /**
     * 获得一张新的卡牌
     *
     * @param id                        卡牌ID
     * @param imagePath                 卡牌图像路径
     * @param type                      卡牌类型
     * @param beforeUsingCheckSkillPath 卡牌使用检查前技能的路径
     * @param usingCheckSkillPath       卡牌使用检查技能的路径
     * @param afterUsingCheckSkillPath  卡牌使用检查后技能的路径
     * @param useSkillPath              卡牌使用技能路径
     */
    public Card(int id, String imagePath, CardType type, String beforeUsingCheckSkillPath, String usingCheckSkillPath, String afterUsingCheckSkillPath, String useSkillPath) {
        super(imagePath);
        this.id = id;
        this.imagePath = imagePath;
        this.type = type;
        try {
            ObjectInputStream skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill" + beforeUsingCheckSkillPath));
            this.beforeUsingCheckSkill = (Skill) skillFile.readObject();
            skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill" + usingCheckSkillPath));
            this.usingCheckSkill = (Skill) skillFile.readObject();
            skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill" + afterUsingCheckSkillPath));
            this.afterUsingCheckSkill = (Skill) skillFile.readObject();
            skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill" + useSkillPath));
            this.useSkill = (Skill) skillFile.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}