package org.wpsoft.dltel.deck;

/**
 * 卡牌状态
 * Created by WinUP on 2016/1/25.
 */
public enum CardState {
    /**
     * 还没有抽牌
     */
    Unused,
    /**
     * 在手牌中
     */
    InHand,
    /**
     * 正在解析
     */
    Analysing,
    /**
     * 在墓地中
     */
    InCemetery,
    /**
     * 不知道去哪里了
     */
    Disappearing
}
