package com.halfaker;

import java.util.Optional;

public interface Participant {

	Integer getNumberOfCards();
	Participant addCard(Card card);
	Boolean hasBlackjack();
	Boolean isBust();
	Integer getHandValue();
	Optional<Card> getLastCard();
}
