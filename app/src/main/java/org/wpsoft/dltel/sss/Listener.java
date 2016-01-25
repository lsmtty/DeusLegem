package org.wpsoft.dltel.sss;

/**
 * SpellSkillSystem - 消息监听器
 * Created by WinUP on 2016/1/20.
 */
public abstract class Listener {
    private int listenerType;

    public int getListenerType() {
        return listenerType;
    }

    public Listener(int listenerType) {
        this.listenerType = listenerType;
    }

    public abstract void listen(String message);
}
