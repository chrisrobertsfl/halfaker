package com.halfaker;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
            return cards;
    }

    public void setCards(List<Card> cards) {
            this.cards = cards;
    }

    public static Deck create() {
            Deck deck = new Deck();
            for (Rank rank : Rank.values()) {
                    for (Suit suit : Suit.values()) {
                            deck.addCard(new Card(rank, suit));
                    }
            }

            return deck;
    }

     void addCard(Card card) {
            cards.add(card);
    }
}