package com.halfaker;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class Player implements Participant {

	Hand hand = new Hand();

		public String getName() {
		return name;
	}

	String name;

	@SuppressWarnings("unchecked")
	public static Player create(Function<Player, Player>... setters) {
		return Stream.of(setters).reduce(setter -> setter, Function::andThen).apply(new Player());
	}

	public Player name(String name) {
		this.name = name;
		return this;
	}

	public Participant addCard(Card newCard) {
		hand.accept(newCard);
		return this;
	}

	public Set<Integer> getHandValues() {
		return hand.values();
	}

	@Override
	public Integer getNumberOfCards() {
		return hand.getNumberOfCards();
	}

	public Integer showCards() {	
	return hand.showCards();
	}

	@Override
	public Boolean hasBlackjack() {
		return hand.hasBlackjack();
	}

	@Override
	public Boolean isBust() {
		return hand.value() > 21;
	}

	@Override
	public Integer getHandValue() {
		return hand.value();
	}

	@Override
	public Optional<Card> getLastCard() {
		return hand.getLastCard();
	}
	


}
