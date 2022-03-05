package calculations.actions;

import calculations.Action;
import calculations.Card;
import calculations.Hand;

import java.util.stream.Stream;

public class ActionDrawCard extends Action {

	private Card card;
	
	public ActionDrawCard (Card card) {
		/*
		 * applies two conditions at once
		 */
		this.card = card;
	}
	
	@Override
	protected Stream<Hand> compute(Hand hand) {
		return hand.findCardInHand(card);
	}

}
