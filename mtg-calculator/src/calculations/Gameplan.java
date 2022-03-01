package calculations;

import java.util.List;
import java.util.stream.Stream;

public final class Gameplan {
	
	private List<RequirementForTurn> requirementsForTurns;
	private Stream<Hand> hands;
	
	Gameplan (List<RequirementForTurn> requirementsForTurns, Decklist decklist) {
		this.requirementsForTurns = requirementsForTurns;
		this.hands = Stream.of(new Hand(decklist,7));
	}
}
