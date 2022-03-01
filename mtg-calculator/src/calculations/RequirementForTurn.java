package calculations;

import java.util.List;
import java.util.stream.Stream;

public final class RequirementForTurn {
	/*
	 * actions are all required at once.
	 * if that shouldn't be the case (e.g. only one of them is required), use specific Action instead (e.g. ActionOr)
	 * */
	private List<Action> actions;
	public RequirementForTurn(List<Action> requiredActions) {
		actions = requiredActions;
	}
	
	/*
	 * calculates probability of requirements being fulfilled
	 */
	public Stream<Hand> apply(Stream<Hand> hands) {
		
		//actions should be applied in the given order
		//therefore each action chances the hands that will be presented to the next one 
		return actions.stream().reduce(
				hands,
				(Stream<Hand> workingHands, Action action) -> action.apply(workingHands),
				//We do need to use a combiner
				(streamA, streamB) -> Stream.concat(streamA, streamB)
		);
	}
}
