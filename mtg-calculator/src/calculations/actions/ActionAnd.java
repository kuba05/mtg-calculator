package calculations.actions;

import calculations.Action;
import calculations.Hand;

import java.util.stream.Stream;

public class ActionAnd extends Action {

	private Action left;
	private Action right;
	
	public ActionAnd (Action left, Action right) {
		/*
		 * applies two conditions at once
		 */
		this.left = left;
		this.right = right;
	}
	
	@Override
	protected Stream<Hand> compute(Hand hand) {
		return right.apply(left.apply(Stream.of(hand)));
	}

}
