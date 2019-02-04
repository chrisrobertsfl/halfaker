package com.halfaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Hand implements Consumer<Card> {

	List<Card> cards = new ArrayList<>();

	@Override
	public void accept(Card card) {
		cards.add(card);
	}

	public Set<Integer> values() {
		Set<Integer> values = new TreeSet<>( (left, right) -> left.compareTo(right) );
		values.add(value());
		values.add(minimunValue());
		return values;
	}

	Integer minimunValue() {

		Integer value = cards.stream().filter(c -> c.getRank() != Rank.ACE).mapToInt(c -> c.value()).sum();
		Integer numberOfAces = (int) (long) cards.stream().filter(c -> c.getRank() == Rank.ACE).count();
		return value + numberOfAces;
	}

	public Integer value() {
		Integer value = cards.stream().filter(c -> c.getRank() != Rank.ACE).mapToInt(c -> c.value()).sum();
		Integer numberOfAces = (int) (long) cards.stream().filter(c -> c.getRank() == Rank.ACE).count();
		value += numberOfAces;
		for (int i = 0; i < numberOfAces; i++) {
			if (value + 10 > 21) {
				return value;
			}
			value += 10;
		}
		return value;
	}

	public Integer getNumberOfCards() {
		return cards.size();
	}

	public Optional<Card> getFirstCard() {
		return optionalCardAtPosition(0);
	}

	public Optional<Card> getLastCard() {
		return optionalCardAtPosition(cards.size() - 1);
	}
	
	Optional<Card> optionalCardAtPosition(Integer position)
	{
		return cards.isEmpty() ? Optional.empty() : Optional.of(cards.get(position));
	}

	public Integer showCards() {
		Stream.of(cards)
		.map(c -> String.format("\t%s", c))
		.forEach(System.out::println);
		return getNumberOfCards();
	}
	
	public boolean hasAces()
	{
		return cards.stream().anyMatch(c -> c.getRank() == Rank.ACE);
	}
	
	public boolean hasBlackjack()
	{
		return getNumberOfCards() == 2 & hasAces() & value() == 21;
	}

}
