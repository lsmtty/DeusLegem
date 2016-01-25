package org.wpsoft.dltel.sss;

/**
 * 技能发动时间
 * Created by WinUP on 2016/1/24.
 */
public enum SpellTimePoint {
    Now(1, true),
    GameStart(1, false),
    BeforeDraw(2, true),
    AfterDraw(2, false),
    BeforeUseTriggerTrapCard(4, true),
    AfterUseTriggerTrapCard(4, false),
    BeforeUseMapTrapCard(8, true),
    AfterUseMapTrapCard(8, false),
    BeforeUseSkillCard(16, true),
    AfterUseSkillCard(16, false),
    BeforeUseSummonCard(32, true),
    AfterUseSummonCard(32, false),
    BeforeTriggerTrapActive(64, true),
    AfterTriggerTrapActive(64, false),
    BeforeMapTrapActive(128, true),
    AfterMapTrapActive(128, false),
    BeforeSkillCardActive(256, true),
    AfterSkillCardActive(256, false),
    BeforeSummon(512, true),
    AfterSummon(512, false),
    BeforeServantMove(1024, true),
    AfterServantMove(1024, false),
    BeforeServantAttack(2048, true),
    AfterServantAttack(2048, false),
    BeforeServantDied(4096, true),
    AfterServantDies(4096, false),
    BeforeBlockOwnerChange(8192, true),
    AfterBlockOwnerChange(8192, false);

    private int value;
    private boolean isBefore;

    public int getValue(){
        return this.value;
    }

    public boolean isBefore() {
        return isBefore;
    }

    SpellTimePoint(int i, boolean isBefore) {
        this.value = i;
        this.isBefore = isBefore;
    }
}


