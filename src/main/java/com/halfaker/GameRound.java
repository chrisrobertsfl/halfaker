package com.halfaker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class GameRound {

	@SuppressWarnings("unchecked")
	public static GameRound create(Function<GameRound, GameRound>...setters) {
		return Stream.of(setters)
				.reduce(setter -> setter, Function::andThen)
				.apply(new GameRound());
	}
	
	Dealer dealer;
	List<Player> players = new ArrayList<>();
	List<Participant> participants = new ArrayList<>();

	public GameRound dealer(Dealer dealer) {
		this.dealer = dealer;
		return this;
	}

	public GameRound addPlayer(Player player) {
		players.add(player);
		return this;
	}

	@SuppressWarnings("unchecked")
	public GameRound run() {
		
		participants.addAll(players);
		participants.add(dealer);
		for (int i = 0; i < 2; i++)
		{
			participants.stream()
			.forEach(p -> p.addCard(dealer.dealCard()));
		}
		
		players.stream()
		.forEach(p -> PlayerRound.create(round -> round.dealer(dealer), round -> round.player(p)).run());
		return this;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public int getDealerRound() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<PlayerRound> getPlayerRounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
