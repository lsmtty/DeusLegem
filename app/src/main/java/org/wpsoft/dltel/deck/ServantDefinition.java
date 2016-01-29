package org.wpsoft.dltel.deck;

/**
 * 从者属性定义
 * Created by WinUP on 2016/1/26.
 */
public final class ServantDefinition {
    private String name;
    private int maxLifePoint;
    private int lifePoint;
    private int maxAttackPoint;
    private int attackPoint;
    private int minAttackRange;
    private int maxAttackRange;

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxLifePoint(int maxLifePoint, boolean append) {
        if (append)
            this.maxLifePoint = this.maxLifePoint + maxLifePoint;
        else
            this.maxLifePoint = maxLifePoint;
    }

    public void setLifePoint(int lifePoint, boolean append) {
        if (append)
            this.lifePoint = this.lifePoint + lifePoint;
        else
            this.lifePoint = lifePoint;
    }

    public void setMaxAttackPoint(int maxAttackPoint, boolean append) {
        if (append)
            this.maxAttackPoint = this.maxAttackPoint + maxAttackPoint;
        else
            this.maxAttackPoint = maxAttackPoint;
    }

    public void setAttackPoint(int attackPoint, boolean append) {
        if (append)
            this.attackPoint = this.attackPoint + attackPoint;
        else
            this.attackPoint = attackPoint;
    }

    public void setMinAttackRange(int minAttackRange, boolean append) {
        if (append)
            this.minAttackRange = this.minAttackRange + minAttackRange;
        else
            this.minAttackRange = minAttackRange;
    }

    public void setMaxAttackRange(int maxAttackRange, boolean append) {
        if (append)
            this.maxAttackRange = this.maxAttackRange + maxAttackRange;
        else
            this.maxAttackRange = maxAttackRange;
    }

    public String getName() {
        return name;
    }

    public int getMaxLifePoint() {
        return maxLifePoint;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public int getMaxAttackPoint() {
        return maxAttackPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getMinAttackRange() {
        return minAttackRange;
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
    }

    public ServantDefinition(String name) {

    }
}
