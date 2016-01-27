package org.wpsoft.dltel.message;

import java.util.LinkedList;

/**
 * 消息监听器列表
 * Created by WinUP on 2016/1/20.
 */
final class ListenerList {
    private static final LinkedList<Listener> listenerList = new LinkedList<>();
    private static final LinkedList<Listener> removeList = new LinkedList<>();
    private static final LinkedList<Listener> addList = new LinkedList<>();

    /**
     * 添加一个消息监听器
     *
     * @param target 要添加的消息监听器
     */
    synchronized static void add(Listener target) {
        if (!listenerList.contains(target))
            synchronized (addList) {
                addList.add(target);
            }
    }

    /**
     * 删除一个消息监听器
     *
     * @param target 要删除的消息监听器
     */
    synchronized static void remove(Listener target) {
        if (listenerList.contains(target))
            synchronized (removeList) {
                removeList.add(target);
            }
    }

    /**
     * s
     * 应用所有添加操作
     */
    private static void updateAdding() {
        synchronized (addList) {
            if (addList.size() > 0) {
                for (Listener e : addList) listenerList.add(e);
                addList.clear();
            }
        }
    }

    /**
     * 应用所有删除操作
     */
    private static void updateRemoving() {
        synchronized (removeList) {
            if (removeList.size() > 0) {
                for (Listener e : removeList) listenerList.remove(e);
                removeList.clear();
            }
        }
    }

    /**
     * 广播消息
     *
     * @param message 要广播的消息
     * @param type    消息类型int（采用按位比较确认监听器类型）
     */
    static void broadcastMessage(String message, int type) {
        updateRemoving();
        updateAdding();
        for (Listener e : listenerList)
            if ((e.getListenerType() & type) != 0)
                e.listen(message);
    }

}
