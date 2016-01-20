package org.wpsoft.dltel.sss;

/**
 * SpellSkillSystem - 消息监听器
 * Created by WinUP on 2016/1/20.
 */
public abstract class Listener {
    private MessageType listenerType;

    public MessageType getListenerType() {
        return listenerType;
    }

    public Listener(MessageType listenerType) {
        this.listenerType = listenerType;
    }

    public abstract boolean listen(String message);
}
