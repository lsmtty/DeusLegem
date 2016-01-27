package org.wpsoft.dltel.spellskill;

/**
 * 技能咏唱参数
 * Created by WinUP on 2016/1/27.
 */
public final class SpellParameter {
    private boolean isCancelNext;
    private Object[] spellResult;

    /**
     * 是否取消该一个技能的执行
     * @return 是否取消该一个技能的执行
     */
    public boolean isCancelNext() {
        return isCancelNext;
    }

    /**
     * 咏唱参数
     * @return 咏唱参数
     */
    public Object[] getSpellResult() {
        return spellResult;
    }

    /**
     * 获得一个新的技能咏唱参数
     * @param isCancelNext 是否取消该一个技能的执行
     * @param spellResult 咏唱参数
     */
    public SpellParameter(boolean isCancelNext, Object... spellResult) {
        this.isCancelNext = isCancelNext;
        this.spellResult = spellResult;
    }
}
