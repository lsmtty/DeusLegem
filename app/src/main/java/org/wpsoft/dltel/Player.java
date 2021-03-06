package org.wpsoft.dltel;

import java.util.UUID;

/**
 * 表示一位玩家
 * Created by WinUP on 2016/1/19.
 */
public final class Player {
    private String name;
    private UUID id;
    private UUID token;

    public Player(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    /**
     * 获取玩家的名称
     * @return 玩家的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取玩家的ID
     * @return 玩家的ID
     */
    public UUID getId() {
        return id;
    }

    public UUID getToken() {
        return token;
    }

    public Player setToken(UUID token) {
        this.token = token;
        return this;
    }
}
