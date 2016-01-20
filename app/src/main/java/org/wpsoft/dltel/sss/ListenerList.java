package org.wpsoft.dltel.sss;

import java.util.LinkedList;

/**
 * SpellSkillSystem - 消息监听器列表
 * Created by WinUP on 2016/1/20.
 */
public final class ListenerList {
    private static LinkedList<Listener> listenerList = new LinkedList<>();
    private static LinkedList<Listener> removeList = new LinkedList<>();
    private static LinkedList<Listener> addList = new LinkedList<>();

    /**
     * 添加一个消息监听器
     *
     * @param target 要添加的消息监听器
     */
    public static void add(Listener target) {
        if (!listenerList.contains(target)) addList.add(target);
    }

    /**
     * 删除一个消息监听器
     *
     * @param target 要删除的消息监听器
     */
    public static void remove(Listener target) {
        if (listenerList.contains(target)) removeList.add(target);
    }

    /**
     * 应用所有添加操作
     */
    static void updateAdding() {
        if (addList.size() > 0) {
            for (Listener e : addList) listenerList.add(e);
            addList.clear();
        }
    }

    /**
     * 应用所有删除操作
     */
    static void updateRemoving() {
        if (removeList.size() > 0) {
            for (Listener e : removeList) listenerList.remove(e);
            removeList.clear();
        }
    }

    /**
     * 广播消息
     *
     * @param message 要广播的消息
     * @param type    消息类型
     */
    static void broadcastMessage(String message, MessageType type) {
        for (Listener e : listenerList)
            if (e.getListenerType() == type)
                e.listen(message);
    }

}
