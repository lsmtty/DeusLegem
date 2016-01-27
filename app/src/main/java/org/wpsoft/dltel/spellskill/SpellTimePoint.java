package org.wpsoft.dltel.spellskill;

/**
 * 技能发动时间
 * Created by WinUP on 2016/1/24.
 */
public enum SpellTimePoint {
    NotSpell(0),
    Now(1),
    GameStart(2),
    DrawBefore(4),
    DrawAfter(8),
    TriggerTrapActiveBefore(16),
    TriggerTrapActiveAfter(32),
    MapTrapActiveBefore(64),
    MapTrapActiveAfter(128),
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

    public int getValue(){
        return this.value;
    }

    SpellTimePoint(int i) {
        this.value = i;
    }
}


