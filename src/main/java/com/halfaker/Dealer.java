package com.halfaker;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class Dealer implements Participant {

	String name;
	Shoe shoe;

	@SuppressWarnings("unchecked")
	public static Dealer create(Function<Dealer, Dealer>... setters) {
		return Stream.of(setters).reduce(setter -> setter, Function::andThen).apply(new Dealer());
	}

	public Dealer name(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dealer shoe(Shoe shoe) {
		this.shoe = shoe;
		return this;
	}

	public Dealer shuffle() {
		shoe.shuffle();
		return this;

	}
	
	@Override
	public Integer getHandValue() {
		return hand.value();
	}
	
	Hand hand = new Hand();

	public Dealer addToHand(List<Card> newCards) {
		newCards.forEach(hand);
		return this;
	}

	public Integer value() {
		return hand.value();
	}

	Card dealCard() {
		return shoe.removeCard();
	}

	public Participant addCard(Card newCard) {
		hand.accept(newCard);
		return this;
	}

	public Optional<Card> getFirstCard() {
		return hand.getFirstCard();
	}

	public Set<Integer> getHandValues() {
		return hand.values();
	}

	@Override
	public Integer getNumberOfCards() {
		return hand.getNumberOfCards();
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
	public Optional<Card> getLastCard() {
		return hand.getLastCard();
	}


}
