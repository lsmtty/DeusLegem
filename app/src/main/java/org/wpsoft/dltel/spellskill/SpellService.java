package org.wpsoft.dltel.spellskill;

import java.util.Stack;

/**
 * SpellSkillSystem - 持久技能循环列
 * Created by WinUP on 2016/1/20.
 */
public final class SpellService {
    private static SpellService instance;
    private static Stack<Skill> spellList = new Stack<>();

    public static SpellService getInstance() {
        if (instance == null) instance = new SpellService();
        return instance;
    }

    public synchronized boolean fireSpellTimePoint(SpellTimePoint timePoint) {
        return true;
    }

    public synchronized void spell(Skill target, Object... parameter) {
        spellList.push(target);

    }

    public synchronized void tryCancelSpell(Skill target) {

    }

    private void startSpell() {

    }

    private void checkSpellList(SpellTimePoint timePoint) {

    }

}
