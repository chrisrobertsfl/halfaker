package com.halfaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class Shoe {

	Integer numberOfDecks = 1;
	List<Card> cards = new ArrayList<>();
	Random seed = null;

	@SuppressWarnings("unchecked")
	public static Shoe create(Function<Shoe, Shoe>... setters) {
		return Stream.of(setters).reduce(setter -> setter, Function::andThen).apply(new Shoe());
	}

	public Shoe numberOfDecks(Integer numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
		return this;
	}

	public Shoe shuffle() {
		for (Integer i = 0; i < numberOfDecks; i++) {
			cards.addAll(Deck.create().getCards());
		}
		seed = new Random();
		return this;
	}

	public Card removeCard() {
		Integer randomCardPosition = seed.nextInt(cards.size());
		Card randomCard = cards.get(randomCardPosition);
		cards.remove(randomCard);
		return randomCard;
	}

}
