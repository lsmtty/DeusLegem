package org.wpsoft.dltel;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.system.Player;

/**
 * 从者
 * Created by WinUP on 2016/1/25.
 */
public final class Servant extends CCSprite {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }



}
