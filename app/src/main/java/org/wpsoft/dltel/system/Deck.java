package org.wpsoft.dltel.system;

import android.support.annotation.Nullable;
import org.wpsoft.dltel.sss.SpellService;
import org.wpsoft.dltel.sss.SpellTimePoint;

import java.util.LinkedList;

/**
 * 牌组
 * Created by WinUP on 2016/1/25.
 */
public final class Deck {
    private LinkedList<Card> unused = new LinkedList<>();
    private LinkedList<Card> hand = new LinkedList<>();
    private LinkedList<Card> cemetery = new LinkedList<>();
    private LinkedList<Card> servantHall = new LinkedList<>();
    private Player player;

    private int count;

    public int getCount() {
        return count;
    }

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
            case InServantHall:
                servantHall.remove(card);
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

    public Deck(Card[] cards, Player player){
        for (Card card: cards) {
            card.setDeck(this);
            unused.push(card);
        }
        count = cards.length;
        this.player = player;
    }

}
