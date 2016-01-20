package org.wpsoft.dltel.system;

import org.wpsoft.dltel.sss.Listener;
import org.wpsoft.dltel.sss.ListenerList;

import java.util.LinkedList;

/**
 * 表示一个技能
 * Created by WinUP on 2016/1/20.
 */
public final class Skill {
    private LinkedList<Listener> listener = new LinkedList<>();
    private Card card;

    public Card getCard() {
        return card;
    }

    public void cancel() {
        for (Listener e : listener)
            ListenerList.remove(e);
        listener.clear();
    }

    private Skill() {
    }

    public static Skill getSkill(Listener[] listeners, Card card) {
        Skill target = new Skill();
        target.card = card;
        for (Listener e : listeners) {
            target.listener.add(e);
            ListenerList.add(e);
        }
        return target;
    }

}
