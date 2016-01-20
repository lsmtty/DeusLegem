package org.wpsoft.dltel.system;

import org.cocos2d.nodes.CCSprite;

/**
 * 表示一张卡牌
 * Created by 思敏 on 2016/1/16.
 */
public abstract class Card extends CCSprite {
    private int id;
    private String imagePath;
    private Player player;

    /**
     * 使用条件检查前进行的准备工作，例如高亮画面某些区域
     * @param isSystemAutoCheck True时表示这是系统抽牌后的自动检查。False时表示这是用户即将使用时的检查
     */
    public abstract void BeforeUsingCheck(boolean isSystemAutoCheck);

    /**
     * 使用条件检查
     * @return 是否可以使用这张卡牌
     */
    public abstract boolean UsingCheck();

    /**
     * 使用条件检查后进行的收尾工作，例如取消画面某些区域的高亮
     * @param isSystemAutoCheck True时表示这是系统抽牌后的自动检查。False时表示这是用户即将使用时的检查
     * @param checkingResult 使用条件检查结果
     */
    public abstract void AfterUsingCheck(boolean isSystemAutoCheck, boolean checkingResult);

    /**
     * 即将使用这张卡牌但还没有用出时进行的工作
     */
    public abstract void BeforeUsing();

    /**
     * 使用这张卡牌
     */
    public abstract void Using();

    /**
     * 卡牌效果全部触发完成后进行的工作
     * @return True时卡牌将直接销毁，False时卡牌将进入循环触发检测队列
     */
    public abstract boolean AfterUsing();


    /**
     * 当卡牌在循环触发检测队列中时，每次循环进行的工作
     * @return True时卡牌继续参与下次循环，False时卡牌将直接销毁
     */
    public abstract boolean Looping();

    /**
     * 获得一张新的卡牌。卡牌将会被随机分配一个新的UUID
     * @param imagePath 卡牌图像的路径
     * @param player 卡牌所属的玩家
     */
    public Card(int id, String imagePath, Player player) {
        super(imagePath);
        this.id = id;
        this.imagePath = imagePath;
        this.player = player;
    }

    /**
     * 获取卡牌的ID
     * @return 卡牌的ID
     */
    public int getId() {
        return id;
    }

    /**
     * 获取卡牌图像的路径
     * @return 获取卡牌图像的路径
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *获取卡牌所属的玩家
     * @return 卡牌所属的玩家
     */
    public Player getPlayer() {
        return player;
    }
}
