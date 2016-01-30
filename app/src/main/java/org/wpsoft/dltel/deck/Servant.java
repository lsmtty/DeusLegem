package org.wpsoft.dltel.deck;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.executecode.ExecuteService;
import org.wpsoft.dltel.executecode.SkillCode;
import org.wpsoft.dltel.executecode.SkillCodeType;
import org.wpsoft.dltel.skill.ServantCode;

/**
 * 从者
 * Created by WinUP on 2016/1/25.
 */
public final class Servant extends CCSprite {
    private Deck deck;
    private ServantDefinition definition;
    private SkillCode[] servantCodes;

    /**
     * 获取从者所在的牌组
     *
     * @return 从者所在的牌组
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * 获取从者参数定义
     *
     * @return 从者参数定义
     */
    public ServantDefinition getDefinition() {
        return definition;
    }

    /**
     * 设置从者所在的牌组
     *
     * @param deck 从者所在的牌组
     */
    Servant setDeck(Deck deck) {
        this.deck = deck;
        return this;
    }

    public Servant attack(Servant servant) {
        servant.getDefinition().setLifePoint(-definition.getAttackPoint(), true);
        definition.setLifePoint(servant.getDefinition().getAttackPoint() / 2, true);
        if (servant.getDefinition().getLifePoint() < 1) servant.destroy();
        if (definition.getLifePoint() < 1) destroy();
        return this;
    }

    /**
     * 销毁从者
     */
    public Servant destroy() {
        for (SkillCode skillCode : servantCodes) {
            ExecuteService.getInstance().cancelRegister(skillCode);
        }
        return this;
    }

    /**
     * 获得一个新的从者
     *
     * @param name            从者名称
     * @param skillCodes      从者的所有能力代码
     * @param skillTimePoints 这些能力代码的发动时间点
     */
    public Servant(String name, String[] skillCodes, int[] skillTimePoints) {
        definition = new ServantDefinition(name);
        servantCodes = new ServantCode[skillCodes.length];
        for (int i = 0; i < skillCodes.length; i++) {
            servantCodes[i] = ServantCode.loadServantCode(skillCodes[i], SkillCodeType.Servant, skillTimePoints[i], this);
            ExecuteService.getInstance().register(servantCodes[i]);
        }
    }


}
