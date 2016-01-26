package org.wpsoft.dltel.deck;

import org.cocos2d.nodes.CCSprite;
import org.wpsoft.dltel.ServantDefinition;

/**
 * 从者
 * Created by WinUP on 2016/1/25.
 */
public final class Servant extends CCSprite {
    private Deck deck;
    private ServantDefinition definition;

    public Deck getDeck() {
        return deck;
    }

    public ServantDefinition getDefinition() {
        return definition;
    }

    Servant setDeck(Deck deck) {
        this.deck = deck;
        return this;
    }



}
