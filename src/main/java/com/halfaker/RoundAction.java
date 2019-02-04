package com.halfaker;

public interface RoundAction {
    RoundAction UNKNOWN = new RoundAction() {

            @Override
            public Boolean execute(Dealer dealer, Player player) {
            	return true;
            }
    };

    RoundAction HIT = new RoundAction() {
            @Override
            public Boolean execute(Dealer dealer, Player player) {
                    player.addCard(dealer.dealCard());
                    if (player.isBust()) {
                            System.out.println(String.format("\t%s -> Busted with %d", player.getLastCard().get(), player.getHandValue()));
                            return false;
                    }
                    return true;
            }
    };

    RoundAction STAND = new RoundAction() {
            @Override
            public Boolean execute(Dealer dealer, Player participant) {
                    return false;
            }

    };

    Boolean execute(Dealer dealer, Player player);

}
