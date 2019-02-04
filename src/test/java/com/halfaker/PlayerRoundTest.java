package com.halfaker;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PlayerRoundTest {

	Dealer bob;
	Player chris;
	PlayerRound round;
	
	@SuppressWarnings("unchecked")
	@Before
	public void beforeEachTest()
	{
		Shoe shoe = Shoe.create(
				s -> s.numberOfDecks(4));
		
		 bob = Dealer.create(
				d -> d.name("Bob"),
				d -> d.shoe(shoe));
		
		 chris = Player.create(
				p -> p.name("Chris"));
		 
		 round = PlayerRound.create(
					r -> r.player(chris),
					r -> r.dealer(bob));
	}
	
	@Test
	public void showInitialHandToPlayer() {
		bob.shuffle();
		chris.addCard(bob.dealCard()).addCard(bob.dealCard());
		assertTrue("Show initial hand to player",round.showPlayerHand() == 2);
	}
	
	@Test
	public void showInitialDealerHandToPlayer()
	{
		bob.shuffle();
		bob.addCard(bob.dealCard()).addCard(bob.dealCard());		
		assertTrue("Show initial dealer hand to player", round.showDealerHand() == 2);
	}
	
	@Test
	public void showRound()
	{
		bob.shuffle();
		chris.addCard(bob.dealCard()).addCard(bob.dealCard());
		bob.addCard(bob.dealCard()).addCard(bob.dealCard());		

		assertTrue("Show initial hand to player",round.showPlayerHand() == 2);
		assertTrue("Show initial dealer hand to player", round.showDealerHand() == 2);
		
	}
}
