package org.wpsoft.dltel.executecode;

/**
 * 能力代码类型
 * Created by WinUP on 2016/1/25.
 */
public enum SkillCodeType {
    /**
     * 一般能力代码
     */
    Normal(1),
    /**
     * 召唤用能力代码
     */
    Summon(2),
    /**
     * 陷阱用能力代码
     */
    Trap(4),
    /**
     * 干扰器用能力代码
     */
    InterferenceUnit(8),
    /**
     * 从者用能力代码
     */
    Servant(16),
    /**
     * 使用条件检查用能力代码
     */
    UsingCheck(32);

    private int value;

    /**
     * 获得能力代码类型对应的数值
     *
     * @return 类型对应的数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 声明新的能力代码类型
     *
     * @param i 类型对应的数值
     */
    SkillCodeType(int i) {
        this.value = i;
    }
}
