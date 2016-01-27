package org.wpsoft.dltel.deck;

import android.support.annotation.Nullable;
import org.wpsoft.dltel.Player;
import org.wpsoft.dltel.spellskill.SpellService;
import org.wpsoft.dltel.spellskill.SpellTimePoint;

import java.util.*;

/**
 * 牌组
 * Created by WinUP on 2016/1/25.
 */
public final class Deck {
    private LinkedList<Card> unused = new LinkedList<>();
    private LinkedList<Card> hand = new LinkedList<>();
    private LinkedList<Card> spelling = new LinkedList<>();
    private LinkedList<Card> cemetery = new LinkedList<>();
    private LinkedList<Servant> servantHall = new LinkedList<>();
    private Hashtable<Card, CardState> cardState = new Hashtable<>();
    private Player player;
    private int count;

    /**
     * 获取未使用的卡牌的列表
     *
     * @return 未使用的卡牌的列表
     */
    public List<Card> getUnusedList() {
        return Collections.unmodifiableList(unused);
    }

    /**
     * 获取手牌列表
     *
     * @return 手牌列表
     */
    public List<Card> getHandList() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * 获取正在发动的卡牌的列表
     *
     * @return 正在发动的卡牌的列表
     */
    public List<Card> getSpellingList() {
        return Collections.unmodifiableList(spelling);
    }

    /**
     * 获取墓地中的卡牌的列表
     *
     * @return 墓地中的卡牌的列表
     */
    public List<Card> getCemeteryList() {
        return Collections.unmodifiableList(cemetery);
    }

    /**
     * 获取未使用的从者的列表
     *
     * @return 未使用的从者的列表
     */
    public List<Servant> getServantHallList() {
        return Collections.unmodifiableList(servantHall);
    }

    /**
     * 获取卡牌状态
     *
     * @param card 目标卡牌
     * @return 卡牌状态
     */
    public CardState getCardState(Card card) {
        if (!cardState.containsKey(card)) return CardState.Disappearing;
        return cardState.get(card);
    }

    /**
     * 修改卡牌状态
     *
     * @param card  目标卡牌
     * @param state 目标状态
     */
    public Card setCardState(Card card, CardState state) {
        cardState.put(card, state);
        return card;
    }

    /**
     * 获取牌组中牌的数量
     *
     * @return 牌组中牌的数量
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取牌组属于的玩家
     *
     * @return 牌组属于的玩家
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 从牌组中删除卡牌（不更新卡牌总数）
     *
     * @param card 要删除的卡牌
     */
    private Card removeCard(Card card) {
        switch (card.getState()) {
            case InCemetery:
                cemetery.remove(card);
                break;
            case InHand:
                hand.remove(card);
                break;
            case Spelling:
                spelling.remove(card);
                break;
            case Unused:
                unused.remove(card);
        }
        return card;
    }

    /**
     * 抽牌
     *
     * @return 抽到的卡牌
     */
    @Nullable
    public Card draw() {
        if (unused.size() < 1) return null;
        boolean answer = SpellService.getInstance().fireSpellTimePoint(SpellTimePoint.DrawBefore).isCancelNext();
        if (answer) return null;
        Card card = moveCard(unused.pop(), CardState.InHand);
        answer = SpellService.getInstance().fireSpellTimePoint(SpellTimePoint.DrawAfter).isCancelNext();
        if (answer) {
            moveCard(card, CardState.Unused);
            return null;
        }
        return card;
    }

    /**
     * 设置卡牌到指定状态，同时移动到相应的队列
     *
     * @param card        目标卡牌
     * @param targetState 目标状态
     */
    Card moveCard(Card card, CardState targetState) {
        removeCard(card);
        switch (targetState) {
            case Unused:
                unused.push(card);
                break;
            case InHand:
                hand.push(card);
                break;
            case InCemetery:
                cemetery.push(card);
                break;
            case Spelling:
                spelling.push(card);
        }
        setCardState(card, targetState);
        return card;
    }

    /**
     * 提取从者
     *
     * @param name 从者名称
     * @return 提取得到的从者
     */
    @Nullable
    public Servant extractServant(String name) {
        Servant answer = null;
        for (Servant e : servantHall) {
            if (e.getDefinition().getName().equalsIgnoreCase(name)) {
                answer = e;
                break;
            }
        }
        if (answer != null) servantHall.remove(answer);
        return answer;
    }

    /**
     * 声明一个牌组
     *
     * @param cards    所有属于这个牌组的卡牌
     * @param servants 所有属于这个牌组的额从者
     * @param player   拥有这个牌组的玩家
     */
    public Deck(Card[] cards, Servant[] servants, Player player) {
        for (Card card : cards)
            unused.push(card.setDeck(this).setState(CardState.Unused));
        for (Servant servant : servants)
            servantHall.push(servant.setDeck(this));
        count = cards.length;
        this.player = player;
    }
}
