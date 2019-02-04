package com.halfaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class PlayerRound {

	
    static final Map<String, RoundAction> ROUND_ACTIONS = new HashMap<>();

    static {
    	ROUND_ACTIONS.put("h", RoundAction.HIT);
    	ROUND_ACTIONS.put("s", RoundAction.STAND);
    }


	Player player;
	Dealer dealer;
	List<Participant> participants = new ArrayList<>();

	public Integer showPlayerHand() {
		System.out.println(String.format("Player %s you have %s:", player.getName(), player.getHandValues()));
		return player.showCards();

	}

	@SuppressWarnings("unchecked")
	public static PlayerRound create(Function<PlayerRound, PlayerRound>... setters) {
		return Stream.of(setters).reduce(setter -> setter, Function::andThen).apply(new PlayerRound());
	}

	public PlayerRound player(Player player) {
		this.player = player;
		participants.add(player);
		return this;
	}

	public PlayerRound dealer(Dealer dealer) {
		this.dealer = dealer;
		return this;
	}

	public Integer showDealerHand() {
		System.out.println(String.format("Dealer %s shows a %s", dealer.getName(), dealer.getFirstCard().get()));
		return dealer.getNumberOfCards();

	}

	public PlayerRound run() {
		System.out.println();
		showDealerHand();
		showPlayerHand();
		if (player.hasBlackjack()) {
			System.out.println(String.format("---- You win %s! ----", player.getName()));
			return this;
		}
		while(playerTakesAction());
		return this;
	}

	 @SuppressWarnings("unchecked")
	Boolean playerTakesAction() {
		 Inquiry actionPresenter = Inquiry.create(
				 i -> i.question("h)it, s)tand"),
				 i -> i.addValidResponse("h"),
				 i -> i.addValidResponse("s"));
		 return executeResponse(actionPresenter.inquire());
	}
	 
	 Boolean executeResponse(String response) {
		 RoundAction action = ROUND_ACTIONS.containsKey(response) ? ROUND_ACTIONS.get(response) : RoundAction.UNKNOWN;
         return action.execute(dealer, player);
	 }

}