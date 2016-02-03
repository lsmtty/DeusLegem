package org.wpsoft.dltel.gameboard;

/**
 * 游戏流程状态
 * Created by WinUP on 2016/2/3.
 */
public enum GamePhase {
    BeforeStart(0),
    MyStartPhase(1),
    MyDrawPhase(2),
    MyPlayPhase(3),
    MyEndPhase(4),
    MatchStartPhase(5),
    MatchDrawPhase(6),
    MatchPlayPhase(7),
    MatchEndPhase(8),
    AfterEnd(9);

    private int value;

    public void moveNext() {
        value++;
        if (value == 9) value = 1;
    }

    public int getValue() {
        return value;
    }

    GamePhase(int value) {
        this.value = value;
    }
}
