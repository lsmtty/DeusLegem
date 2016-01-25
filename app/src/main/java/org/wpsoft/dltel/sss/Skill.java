package org.wpsoft.dltel.sss;

import org.wpsoft.dltel.system.Card;

/**
 * 表示一个技能
 * Created by WinUP on 2016/1/20.
 */
public abstract class Skill extends Listener {
    private Card card;

    public Card getCard() {
        return card;
    }

    @Override
    public void listen(String message) {

    }

    public abstract void spell();

    public Skill(int listenerType, Card card) {
        super(listenerType);
        this.card = card;
        ListenerList.add(this);
    }

}
