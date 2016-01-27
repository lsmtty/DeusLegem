package org.wpsoft.dltel.executecode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 能力代码执行服务
 * Created by WinUP on 2016/1/20.
 */
public final class ExecuteService {
    private static ExecuteService instance;
    private static final ArrayList<SkillCode> skills = new ArrayList<>();

    /**
     * 获得能力代码执行服务的唯一实例
     * @return 能力代码执行服务的唯一实例
     */
    public static ExecuteService getInstance() {
        if (instance == null) instance = new ExecuteService();
        return instance;
    }

    /**
     * 注册一段能力代码到执行服务
     * @param skill 要注册的能力代码
     */
    public synchronized SkillCode register(SkillCode skill) {
        if (!skills.contains(skill)) skills.add(skill);
        return skill;
    }

    /**
     * 执行指定时间点的所有能力代码
     * @param timePoint 目标时间点
     * @param parameter 执行参数
     * @return 执行结果
     */
    public ExecuteParameter executeTimePoint(ExecuteTimePoint timePoint, Object... parameter) {
        Stack<SkillCode> skillStack = new Stack<>();
        ExecuteParameter param = new ExecuteParameter(false, parameter);
        synchronized (skills){
            generateSkillStack(skillStack, timePoint);
            while (skillStack.size() > 0) {
                SkillCode skill = skillStack.peek();
                switch (skill.getType()) {
                    case Normal:
                        generateSkillStack(skillStack, ExecuteTimePoint.NormalSkillActiveBefore);
                        break;
                    case Summon:
                        generateSkillStack(skillStack, ExecuteTimePoint.SummonBefore);
                        break;
                    case Trap:
                        generateSkillStack(skillStack, ExecuteTimePoint.TrapActiveBefore);
                        break;
                    case InterferenceUnit:
                        generateSkillStack(skillStack, ExecuteTimePoint.InterferenceUnitActiveBefore);
                        break;
                }
                skill = skillStack.pop();
                param = skill.execute(param);
                switch (skill.getType()) {
                    case Normal:
                        generateSkillStack(skillStack, ExecuteTimePoint.NormalSkillActiveAfter);
                        break;
                    case Summon:
                        generateSkillStack(skillStack, ExecuteTimePoint.SummonAfter);
                        break;
                    case Trap:
                        generateSkillStack(skillStack, ExecuteTimePoint.TrapActiveAfter);
                        break;
                    case InterferenceUnit:
                        generateSkillStack(skillStack, ExecuteTimePoint.InterferenceUnitActiveAfter);
                        break;
                }
            }
        }
        return param;
    }

    /**
     * 生成能力代码执行队列（仅用于Private环境）
     * @param skillStack 上一层队列
     * @param timePoint 执行时间点
     */
    private void generateSkillStack(Stack<SkillCode> skillStack, ExecuteTimePoint timePoint) {
        for (SkillCode skill : skills) {
            if (((skill.getSpellTimePoint() & timePoint.getValue()) != 0) && (!skillStack.contains(skill)))
                skillStack.push(skill);
        }
    }

}
