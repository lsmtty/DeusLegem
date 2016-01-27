package org.wpsoft.dltel.deck;

/**
 * 从者属性定义
 * Created by WinUP on 2016/1/26.
 */
public final class ServantDefinition {
    private String name;
    private int lifePoint;
    private int attackPoint;
    private int minAttackRange;
    private int maxAttackRange;

    public String getName() {
        return name;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public ServantDefinition(String name){

    }
}
