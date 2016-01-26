package org.wpsoft.dltel;

import android.support.annotation.Nullable;
import org.wpsoft.dltel.spellskill.SpellService;
import org.wpsoft.dltel.spellskill.SpellTimePoint;
import org.wpsoft.dltel.system.CardState;
import org.wpsoft.dltel.system.Player;

import java.util.LinkedList;

/**
 * 牌组
 * Created by WinUP on 2016/1/25.
 */
public final class Deck {
    private LinkedList<Card> unused = new LinkedList<>();
    private LinkedList<Card> hand = new LinkedList<>();
    private LinkedList<Card> cemetery = new LinkedList<>();
    private LinkedList<Servant> servantHall = new LinkedList<>();
    private Player player;
    private int count;

    /**
     * 获取牌组中牌的数量
     * @return 牌组中牌的数量
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取牌组属于的玩家
     * @return 牌组属于的玩家
     */
    public Player getPlayer() {
        return player;
    }

    private Card removeCard(Card card){
        switch (card.getState()){
            case InCemetery:
                cemetery.remove(card);
                break;
            case InHand:
                hand.remove(card);
                break;
            case Unused:
                unused.remove(card);
        }
        return card;
    }

    @Nullable
    public Card draw(){
        if (unused.size() < 1) return null;
        boolean answer = SpellService.getInstance().fireSpellTimePoint(SpellTimePoint.BeforeDraw);
        if (!answer) return null;
        Card card = unused.pop();
        hand.add(card);
        SpellService.getInstance().fireSpellTimePoint(SpellTimePoint.AfterDraw);
        return card;
    }

    Card sendToCemetery(Card card){
        removeCard(card).setState(CardState.InCemetery);
        return card;
    }

    public Deck(Card[] cards, Servant[] servants, Player player){
        for (Card card: cards) {
            card.setDeck(this);
            unused.push(card);
        }
        for (Servant servant : servants){
            servant.setPlayer(player);
            servantHall.push(servant);
        }
        count = cards.length;
        this.player = player;
    }

}
