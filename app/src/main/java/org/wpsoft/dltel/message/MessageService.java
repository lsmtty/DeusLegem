package org.wpsoft.dltel.message;

import android.os.Looper;
import android.os.Message;
import android.os.Handler;

/**
 * 消息服务
 * Created by WinUP on 2016/1/20.
 */
public final class MessageService extends Thread {
    private volatile static MessageService instance;
    private Handler handler;
    private Looper looper;

    private MessageService() {
    }

    /**
     * 获得消息服务的唯一实例
     *
     * @return 消息服务的唯一实例, 双重校验锁写法
     */
    public static MessageService getInstance() {
        if (instance == null) {
            synchronized (MessageService.class) {
                if (instance == null) {
                    instance = new MessageService();
                    instance.start();
                }
            }
        }
        return instance;
    }

    /**
     * 添加一个消息监听器
     *
     * @param listener 要添加的消息监听器
     */
    public Listener listen(Listener listener) {
        ListenerList.add(listener);
        return listener;
    }

    /**
     * 去除一个消息监听器
     *
     * @param listener 要去除的消息监听器
     */
    public Listener cancelListen(Listener listener) {
        ListenerList.remove(listener);
        return listener;
    }

    /**
     * 发送一条消息
     *
     * @param message 消息正文
     * @param type    消息类型
     */
    public void sendMessage(String message, int type) {
        Message target = handler.obtainMessage();
        target.what = type;
        target.obj = message;
        handler.sendMessage(target);
    }

    /**
     * 停止消息服务
     */
    public void stopService() {
        if (looper != null) looper.quit();
    }

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        if (looper != null) handler = new MessageHandler(looper);
        Looper.loop();
    }

    class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            ListenerList.broadcastMessage((String) msg.obj, msg.what);
        }
    }
}
