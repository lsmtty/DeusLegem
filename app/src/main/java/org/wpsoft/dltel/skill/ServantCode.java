package org.wpsoft.dltel.skill;

import org.wpsoft.dltel.deck.Servant;
import org.wpsoft.dltel.executecode.SkillCode;
import org.wpsoft.dltel.executecode.SkillCodeType;

/**
 * 从者能力代码
 * Created by WinUP on 2016/1/28.
 */
public abstract class ServantCode extends SkillCode {
    private Servant servant;

    public Servant getServant() {
        return servant;
    }

    public static SkillCode loadServantCode(String name, SkillCodeType type, int spellTimePoint, Servant servant) {
        SkillCode skillCode = SkillCode.loadSkillCode(name, type, spellTimePoint);
        if (!(skillCode instanceof ServantCode)) return null;
        ServantCode servantCode = (ServantCode) skillCode;
        servantCode.servant = servant;
        return servantCode;
    }
}
