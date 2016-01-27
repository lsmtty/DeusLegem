package org.wpsoft.dltel.spellskill;

import java.util.ArrayList;
import java.util.Stack;

/**
 * SpellSkillSystem - 持久技能循环列
 * Created by WinUP on 2016/1/20.
 */
public final class SpellService {
    private static SpellService instance;
    private static final ArrayList<Skill> skills = new ArrayList<>();

    public static SpellService getInstance() {
        if (instance == null) instance = new SpellService();
        return instance;
    }

    public synchronized Skill register(Skill skill) {
        if (!skills.contains(skill)) skills.add(skill);
        return skill;
    }

    public SpellParameter fireSpellTimePoint(SpellTimePoint timePoint, Object... parameter) {
        Stack<Skill> skillStack = new Stack<>();
        SpellParameter param = new SpellParameter(false, parameter);
        synchronized (skills){
            generateSkillStack(skillStack, timePoint);
            while (skillStack.size() > 0) {
                Skill skill = skillStack.peek();
                switch (skill.getType()) {
                    case Normal:
                        generateSkillStack(skillStack, SpellTimePoint.NormalSkillActiveBefore);
                        break;
                    case Summon:
                        generateSkillStack(skillStack, SpellTimePoint.SummonBefore);
                        break;
                    case MapTrap:
                        generateSkillStack(skillStack, SpellTimePoint.MapTrapActiveBefore);
                        break;
                    case TriggerTrap:
                        generateSkillStack(skillStack, SpellTimePoint.TriggerTrapActiveBefore);
                        break;
                }
                skill = skillStack.pop();
                param = skill.spell(param);
                switch (skill.getType()) {
                    case Normal:
                        generateSkillStack(skillStack, SpellTimePoint.NormalSkillActiveAfter);
                        break;
                    case Summon:
                        generateSkillStack(skillStack, SpellTimePoint.SummonAfter);
                        break;
                    case MapTrap:
                        generateSkillStack(skillStack, SpellTimePoint.MapTrapActiveAfter);
                        break;
                    case TriggerTrap:
                        generateSkillStack(skillStack, SpellTimePoint.TriggerTrapActiveAfter);
                        break;
                }
            }
        }
        return param;
    }

    private void generateSkillStack(Stack<Skill> skillStack, SpellTimePoint timePoint) {
        for (Skill skill : skills) {
            if (((skill.getSpellTimePoint() & timePoint.getValue()) != 0) && (!skillStack.contains(skill)))
                skillStack.push(skill);
        }
    }

    public void tryCancelSpell(Skill skill) {
        synchronized (skills) {
            if (skills.contains(skill)) skills.remove(skill);
        }
    }

}
