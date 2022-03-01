package calculations;

import java.util.stream.Stream;

public abstract class Action {
	/*
	 * for given hand apply your rules, returning a stream, which, potentially, contains many (e.g. in an OrAction) or no (action can not be satisfied on given hand) hand.
	 */
	public Stream<Hand> apply(Stream<Hand> hands) {
		return hands.parallel().flatMap(hand -> compute(hand));
	}
	
	abstract protected Stream<Hand> compute(Hand hand);
}
