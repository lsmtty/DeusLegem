package org.wpsoft.dltel;

import java.util.UUID;

/**
 * 游戏设置类
 * Created by WinUP on 2016/2/3.
 */
public final class GameDefinition {
    private static Player selfPlayer;

    public static Player getSelfPlayer() {
        return selfPlayer;
    }

    public static Player login(String username, String password){
        selfPlayer = new Player("WinUP", UUID.fromString("1D2FFEE8-B8A8-46F8-8D20-18987EBB1BF4"));
        selfPlayer.setToken(UUID.fromString("CE08618A-1380-4613-83BC-5C77744A8AF4"));
        //TODO: 实际使用时需要调用服务获取上面这些数据
        return selfPlayer;
    }
}
