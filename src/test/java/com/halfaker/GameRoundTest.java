package com.halfaker;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GameRoundTest {

	Dealer dealer;
	GameRound gameRound;
	
	@SuppressWarnings("unchecked")
	@Before
	public void beforeEachTest()
	{
		dealer = Dealer.create(
				d -> d.name("Bob"),
				d -> d.shoe(Shoe.create(
						s -> s.numberOfDecks(4))))
				.shuffle();
		gameRound =  GameRound.create(
				gr -> gr.dealer(dealer),
				gr -> gr.addPlayer(Player.create(
						p -> p.name("Chris"))),
				gr -> gr.addPlayer(Player.create(
						p -> p.name("Anja"))));
	}
	
	@Ignore
	@Test
	public void initializeRound()
	{
		assertTrue("Each Participant should have 2 cards in their hand", gameRound
				.run()
				.getParticipants()
				.stream()
				.allMatch(p -> p.getNumberOfCards() == 2));
	}
	
	@Ignore
	@Test
	public void numberOfPlayerRoundsShouldTotalNumberOfPlayers()
	{
		gameRound.run();
		assertTrue("Number of player rounds should be number of players", 
				gameRound.getParticipants().size() == gameRound.getPlayerRounds().size() + 1);
				
				
				
	}
}
