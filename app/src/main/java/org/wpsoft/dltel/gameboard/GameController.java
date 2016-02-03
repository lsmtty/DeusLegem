package org.wpsoft.dltel.gameboard;

import com.example.deuslegem.layer.PlayLayer;

import org.cocos2d.layers.CCScene;
import org.wpsoft.dltel.GameDefinition;
import org.wpsoft.dltel.Player;
import org.wpsoft.dltel.deck.Card;
import org.wpsoft.dltel.deck.Deck;
import org.wpsoft.dltel.deck.Servant;

/**
 * 游戏控制类
 * Created by 思敏 on 2016/1/18.
 */
public final class GameController {
    private GamePhase gamePhase;
    private Player matchPlayer;
    public boolean isGameRunning;
    public boolean isMyTurn;
    private Deck selfDeck;
    private Deck matchDeck;

    public Player getMatchPlayer() {
        return matchPlayer;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public GameController startGame() {
        isGameRunning = true;
        return this;
    }

    public void stopGame() {
        isGameRunning = false;
    }

    public Deck getSelfDeck() {
        return selfDeck;
    }

    public Deck getMatchDeck() {
        return matchDeck;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public GameController toNextPhase() {
        gamePhase.moveNext();
        switch (gamePhase) {
            case MyStartPhase:

            case MyDrawPhase:

            case MyPlayPhase:

            case MyEndPhase:

            case MatchStartPhase:

            case MatchDrawPhase:

            case MatchPlayPhase:

            case MatchEndPhase:

        }
        return this;
    }

    private GameController() {
    }

    public static GameController generateController(Player matchPlayer, String mapName, CCScene scene) {
        GameController target = new GameController();
        target.matchPlayer = matchPlayer;
        scene.addChild(new PlayLayer(mapName));
        target.selfDeck = new Deck(new Card[30], new Servant[5], GameDefinition.getSelfPlayer());
        target.matchDeck = new Deck(new Card[30], new Servant[5], matchPlayer);
        //TODO: 以上数据请从服务器获取
        target.gamePhase = GamePhase.BeforeStart;
        return target;
    }
}
