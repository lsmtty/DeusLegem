package org.wpsoft.dltel.executecode;

/**
 * 能力代码执行参数
 * Created by WinUP on 2016/1/27.
 */
public final class ExecuteParameter {
    private boolean isCancelNext;
    private boolean canRemove;
    private Object[] spellResult;

    /**
     * 是否取消下一个能力代码的执行
     *
     * @return 是否取消下一个能力代码的执行
     */
    public boolean isCancelNext() {
        return isCancelNext;
    }

    /**
     * 是否将返回这个执行参数的能力代码从执行服务中移除
     *
     * @return 是否将返回这个执行参数的能力代码从执行服务中移除
     */
    public boolean canRemove() {
        return canRemove;
    }

    /**
     * 执行参数
     *
     * @return 执行参数
     */
    public Object[] getSpellResult() {
        return spellResult;
    }

    /**
     * 获得一个新的能力代码执行参数
     *
     * @param isCancelNext 是否取消下一个能力代码的执行
     * @param canRemove    是否将返回这个执行参数的能力代码从执行服务中移除
     * @param spellResult  执行参数
     */
    public ExecuteParameter(boolean isCancelNext, boolean canRemove, Object... spellResult) {
        this.isCancelNext = isCancelNext;
        this.canRemove = canRemove;
        this.spellResult = spellResult;
    }
}
