package org.wpsoft.dltel.executecode;

import android.support.annotation.Nullable;

/**
 * 表示一段能力代码
 * Created by WinUP on 2016/1/20.
 */
public abstract class SkillCode {
    private SkillCodeType type;
    private int spellTimePoint = 0;

    /**
     * 获取能力代码类型
     *
     * @return 能力代码类型
     */
    public SkillCodeType getType() {
        return type;
    }

    /**
     * 能力代码发动时间
     *
     * @return 发动时间
     */
    public int getSpellTimePoint() {
        return spellTimePoint;
    }

    /**
     * 执行能力代码
     *
     * @param parameter 执行参数
     * @return 执行结果
     */
    public abstract ExecuteParameter execute(ExecuteParameter parameter);

    /**
     * 确认能力代码是否被取消
     * @param parameter 上个能力代码的执行结果
     * @param type 当前能力代码的类型
     * @return 能力代码是否被取消
     */
    public static boolean checkCanceled(ExecuteParameter parameter, SkillCodeType type) {
        return (parameter.isCancelNext() && ((SkillCodeType) parameter.getExecuteResult()[0]).getValue() == type.getValue());
    }

    /**
     * 按名称生成能力代码
     *
     * @param name           能力代码名称（必须位于包org.wpsoft.dltel.skill内）
     * @param type           能力代码类型
     * @param spellTimePoint 能力代码触发时间点
     * @return 读取到的能力代码
     */
    @Nullable
    public static SkillCode loadSkillCode(String name, SkillCodeType type, int spellTimePoint) {
        try {
            SkillCode skillCode = (SkillCode) Class.forName("org.wpsoft.dltel.skill." + name).newInstance();
            skillCode.type = type;
            skillCode.spellTimePoint = spellTimePoint;
            return skillCode;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
