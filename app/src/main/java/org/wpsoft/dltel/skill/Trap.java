package org.wpsoft.dltel.skill;

import com.example.deuslegem.bean.TileNode;
import org.wpsoft.dltel.executecode.SkillCode;
import org.wpsoft.dltel.executecode.SkillCodeType;

/**
 * 表示一个陷阱
 * Created by WinUP on 2016/1/27.
 */
public abstract class Trap extends SkillCode {
    private TileNode tileNode;

    /**
     * 获取陷阱能力代码对应的地图节点
     *
     * @return 陷阱能力代码对应的地图节点
     */
    public TileNode getTileNode() {
        return tileNode;
    }

    /**
     * 按名称生成陷阱能力代码
     *
     * @param name           能力代码名称（必须位于包org.wpsoft.dltel.skill内）
     * @param type           能力代码类型
     * @param spellTimePoint 能力代码触发时间点
     * @param tileNode       陷阱能力代码对应的地图节点
     * @return 读取到的能力代码
     */
    public static SkillCode loadTrap(String name, SkillCodeType type, int spellTimePoint, TileNode tileNode) {
        SkillCode skillCode = SkillCode.loadSkillCode(name, type, spellTimePoint);
        if (!(skillCode instanceof Trap)) return null;
        Trap trap = (Trap) skillCode;
        trap.tileNode = tileNode;
        return trap;
    }
}
