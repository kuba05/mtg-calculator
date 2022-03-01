package calculations.actions;

import calculations.Action;
import calculations.Hand;

import java.util.stream.Stream;

public class ActionOr extends Action {

	private Action left;
	private Action right;
	
	public ActionOr (Action left, Action right) {
		/*
		 * applies two conditions, of which only one has to be satisfied
		 */
		this.left = left;
		this.right = right;
	}
	
	@Override
	protected Stream<Hand> compute(Hand hand) {
		return Stream.concat(left.apply(Stream.of(hand)), right.apply(Stream.of(hand)));
	}

}
