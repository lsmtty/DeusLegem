package org.wpsoft.dltel.executecode;

/**
 * 能力代码发动时间
 * Created by WinUP on 2016/1/24.
 */
public enum ExecuteTimePoint {
    NotSpell(0),
    Now(1),
    GameStart(2),
    DrawBefore(4),
    DrawAfter(8),
    InterferenceUnitActiveBefore(16),
    InterferenceUnitActiveAfter(32),
    TrapActiveBefore(64),
    TrapActiveAfter(128),
    NormalSkillActiveBefore(256),
    NormalSkillActiveAfter(512),
    SummonBefore(1024),
    SummonAfter(2048),
    ServantMoveBefore(4096),
    ServantMoveAfter(8192),
    ServantAttackBefore(16384),
    ServantAttackAfter(32768),
    ServantDiedBefore(65536),
    ServantDiedAfter(131072),
    BlockOwnerChangeBefore(262144),
    BlockOwnerChangeAfter(524288);

    private int value;

    /**
     * 获得能力代码发动时间对应的数值
     * @return 发动时间对应的数值
     */
    public int getValue(){
        return this.value;
    }

    /**
     * 声明新的能力代码发动时间
     * @param i 发动时间对应的数值
     */
    ExecuteTimePoint(int i) {
        this.value = i;
    }
}


