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

<<<<<<< HEAD
    /**
     * 获取对应的从者
     *
     * @return 对应的从者
     */
=======
>>>>>>> 1436d27ecf5a2c73b4f23606ee004b87cda16d57
    public Servant getServant() {
        return servant;
    }

<<<<<<< HEAD
    /**
     * 按名称生成从者能力代码
     *
     * @param name           能力代码名称（必须位于包org.wpsoft.dltel.skill内）
     * @param type           能力代码类型
     * @param spellTimePoint 能力代码触发时间点
     * @param servant        对应的从者
     * @return 读取到的能力代码
     */
=======
>>>>>>> 1436d27ecf5a2c73b4f23606ee004b87cda16d57
    public static SkillCode loadServantCode(String name, SkillCodeType type, int spellTimePoint, Servant servant) {
        SkillCode skillCode = SkillCode.loadSkillCode(name, type, spellTimePoint);
        if (!(skillCode instanceof ServantCode)) return null;
        ServantCode servantCode = (ServantCode) skillCode;
        servantCode.servant = servant;
        return servantCode;
    }
}
