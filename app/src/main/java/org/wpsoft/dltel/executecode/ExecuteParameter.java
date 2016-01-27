package org.wpsoft.dltel.executecode;

/**
 * 能力代码执行参数
 * Created by WinUP on 2016/1/27.
 */
public final class ExecuteParameter {
    private boolean isCancelNext;
    private Object[] spellResult;

    /**
     * 是否取消下一个能力代码的执行
     * @return 是否取消下一个能力代码的执行
     */
    public boolean isCancelNext() {
        return isCancelNext;
    }

    /**
     * 执行参数
     * @return 执行参数
     */
    public Object[] getSpellResult() {
        return spellResult;
    }

    /**
     * 获得一个新的能力代码执行参数
     * @param isCancelNext 是否取消下一个能力代码的执行
     * @param spellResult 执行参数
     */
    public ExecuteParameter(boolean isCancelNext, Object... spellResult) {
        this.isCancelNext = isCancelNext;
        this.spellResult = spellResult;
    }
}
