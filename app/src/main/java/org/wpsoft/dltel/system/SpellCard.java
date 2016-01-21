package org.wpsoft.dltel.system;

/**
 * 表示一张技能卡
 * Created by WinUP on 2016/1/19.
 */
public abstract class SpellCard extends Card {
    private boolean maintain;

    public SpellCard(int id, String imagePath, Player player, boolean maintain){
        super(id, imagePath, player);
        this.maintain = maintain;
    }

    /**
     * 获取该技能卡是否是持续技能卡
     * @return 该技能卡是否是持续技能卡
     */
    public boolean isMaintain() {
        return maintain;
    }
}