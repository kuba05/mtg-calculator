package calculations;

import java.util.List;
import java.util.stream.Stream;

public final class Gameplan {
	
	private List<RequirementForTurn> requirementsForTurns;
	private Stream<Hand> hands;
	private RequirementForTurn openingHandRequirement;
	private Decklist decklist;
	
	Gameplan (RequirementForTurn openingHandRequirement, List<RequirementForTurn> requirementsForTurns, Decklist decklist) {
		this.requirementsForTurns = requirementsForTurns;
		this.openingHandRequirement = openingHandRequirement;
		this.decklist = decklist;
	}
	
	public void prepare() {
		//a normal opening hand has a size of 7
		hands = Stream.of(new Hand(decklist,7));
		hands = openingHandRequirement.apply(hands);
	}
	
	public Stream<Hand> apply() {
		this.hands = requirementsForTurns.stream().reduce(
				this.hands,
				(Stream<Hand> hands, RequirementForTurn requirement) -> requirement.apply(hands),
				(Stream<Hand> a, Stream<Hand> b) -> Stream.concat(a, b)
		);
		return this.hands;
	}
}
