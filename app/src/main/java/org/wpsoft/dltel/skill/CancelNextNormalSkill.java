package org.wpsoft.dltel.skill;

import org.wpsoft.dltel.executecode.ExecuteParameter;
import org.wpsoft.dltel.executecode.SkillCode;
import org.wpsoft.dltel.executecode.SkillCodeType;

/**
 * 取消下一个普通技能的执行
 * Created by WinUP on 2016/1/30.
 */
public final class CancelNextNormalSkill extends SkillCode {
    @Override
    public ExecuteParameter execute(ExecuteParameter parameter) {
        if (checkCanceled(parameter, getType()))
            return new ExecuteParameter(false, true);
        else
            return new ExecuteParameter(true, true, SkillCodeType.Normal);
    }
}
