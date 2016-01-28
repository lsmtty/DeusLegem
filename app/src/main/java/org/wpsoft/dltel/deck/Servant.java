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

    public Deck getDeck() {
        return deck;
    }

    public ServantDefinition getDefinition() {
        return definition;
    }

    Servant setDeck(Deck deck) {
        this.deck = deck;
        return this;
    }

    public Servant destroy() {
        for (SkillCode skillCode : servantCodes) {
            ExecuteService.getInstance().cancelRegister(skillCode);
        }
        return this;
    }

    public Servant(String name, String[] skillCodes, int[] skillTimePoints) {
        definition = new ServantDefinition(name);
        servantCodes = new ServantCode[skillCodes.length];
        for (int i = 0; i < skillCodes.length; i++) {
            servantCodes[i] = ServantCode.loadServantCode(skillCodes[i], SkillCodeType.Servant, skillTimePoints[i], this);
            ExecuteService.getInstance().register(servantCodes[i]);
        }
    }


}
