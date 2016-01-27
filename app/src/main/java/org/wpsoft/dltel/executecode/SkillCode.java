package org.wpsoft.dltel.executecode;

import android.support.annotation.Nullable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * 表示一段能力代码
 * Created by WinUP on 2016/1/20.
 */
public abstract class SkillCode implements Serializable {
    private static final long serialVersionUID = 1L;
    private SkillCodeType type;
    private int spellTimePoint = 0;

    /**
     * 获取能力代码类型
     *
     * @return 能力代码类型
     */
    public SkillCodeType getType() {
        return type;
    }

    /**
     * 能力代码发动时间
     *
     * @return 发动时间
     */
    public int getSpellTimePoint() {
        return spellTimePoint;
    }

    /**
     * 执行能力代码
     *
     * @param parameter 执行参数
     * @return 执行结果
     */
    public abstract ExecuteParameter execute(ExecuteParameter parameter);

    /**
     * 从文件读取能力代码
     *
     * @param name 文件名称(assets/skill/下)
     * @param type 能力代码类型
     * @return 读取到的能力代码
     */
    @Nullable
    public static SkillCode loadSkill(String name, SkillCodeType type, int spellTimePoint) {
        try {
            ObjectInputStream skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill/" + name));
            SkillCode skill = (SkillCode) skillFile.readObject();
            skill.type = type;
            skill.spellTimePoint = spellTimePoint;
            return skill;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
