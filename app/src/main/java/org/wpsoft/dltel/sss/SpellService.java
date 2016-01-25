package org.wpsoft.dltel.sss;

import java.util.Stack;

/**
 * SpellSkillSystem - 持久技能循环列
 * Created by WinUP on 2016/1/20.
 */
public final class SpellService {
    private static SpellService instance;
    private static Stack<Skill> spellList = new Stack<>();

    public static SpellService getInstance() {
        return instance;
    }

    public synchronized boolean fireSpellTimePoint(SpellTimePoint timePoint){
        return true;
    }

    public synchronized void spell(Skill target){
        spellList.push(target);

    }

    private void startSpell(){

    }

    private void checkSpellList(SpellTimePoint timePoint){

    }

}
