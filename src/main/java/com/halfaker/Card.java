package com.halfaker;

public class Card {
	 Suit suit;
     Rank rank;

     public Card(Rank rank, Suit suit) {
             this.rank = rank;
             this.suit = suit;
     }

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return String.format("[%s of %s]", rank, suit);
	}

	public Integer value() {
		return rank.getValue();
	}
}
