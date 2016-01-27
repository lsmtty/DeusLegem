package org.wpsoft.dltel.spellskill;

import android.support.annotation.Nullable;
import org.wpsoft.dltel.deck.Card;
import org.wpsoft.dltel.deck.SkillType;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * 表示一个技能
 * Created by WinUP on 2016/1/20.
 */
public abstract class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    private SkillType type;
    private int spellTimePoint = 0;

    /**
     * 获取技能类型
     *
     * @return 技能类型
     */
    public SkillType getType() {
        return type;
    }

    /**
     * 技能发动时间
     *
     * @return 发动时间
     */
    public int getSpellTimePoint() {
        return spellTimePoint;
    }

    /**
     * 咏唱技能
     *
     * @param parameter 咏唱参数
     * @return 咏唱结果
     */
    public abstract SpellParameter spell(SpellParameter parameter);

    /**
     * 从文件读取技能
     *
     * @param name 文件名称(assets/skill/下)
     * @param type 技能类型
     * @return 读取到的技能
     */
    @Nullable
    public static Skill loadSkill(String name, SkillType type, int spellTimePoint) {
        try {
            ObjectInputStream skillFile = new ObjectInputStream(new FileInputStream("file:///android_asset/skill/" + name));
            Skill skill = (Skill) skillFile.readObject();
            skill.type = type;
            skill.spellTimePoint = spellTimePoint;
            return skill;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
