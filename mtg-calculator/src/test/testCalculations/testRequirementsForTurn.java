package test.testCalculations;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import calculations.Action;
import calculations.Decklist;
import calculations.Hand;
import calculations.RequirementForTurn;

public class testRequirementsForTurn {
	
	Decklist decklist = new Decklist();
	Hand mockedHandWithNonzeroProbability = Mockito.mock(Hand.class);
	List<Hand> testHands = Collections.nCopies(10, mockedHandWithNonzeroProbability);
	List<Hand> testLongerHands = Collections.nCopies(100, mockedHandWithNonzeroProbability);
	List<Hand> testLongHands = Collections.nCopies(100000, mockedHandWithNonzeroProbability);
			
	public testRequirementsForTurn() {
		//this mock works for any number of calls
		Mockito.doAnswer(invocation -> 1).when(mockedHandWithNonzeroProbability).getProbability();
	}
	
	@Test
	void testApplyForOneAction() {
		//define all mocks in one list
		List<Action> listOfActions = List.of(Mockito.mock(Action.class));
		//create the tested object
		RequirementForTurn requirementForTurn = new RequirementForTurn(listOfActions);
				
		int a = testHands.get(0).getProbability();
		Mockito.when(listOfActions.get(0).apply(Mockito.any())).thenReturn(testHands.stream()).thenReturn(testLongerHands.stream()).thenReturn(testLongHands.stream());
		
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testHands);
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongerHands);
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongHands);
		
		Mockito.verify(listOfActions.get(0), Mockito.times(3)).apply(Mockito.any());
	}
	
	@Test
	void testApplyForTwoAction() {
		//define all mocks in one list
		List<Action> listOfActions = List.of(Mockito.mock(Action.class), Mockito.mock(Action.class));
		//create the tested object
		RequirementForTurn requirementForTurn = new RequirementForTurn(listOfActions);
		
		//we will run the test twice to see if it influences next call in anyway
		Mockito.when(listOfActions.get(0).apply(Mockito.any())).thenReturn(testHands.stream()).thenReturn(testHands.stream());
		Mockito.when(listOfActions.get(1).apply(Mockito.any())).thenReturn(testLongerHands.stream()).thenReturn(testLongerHands.stream());
		
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongerHands);
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongerHands);
		
		Mockito.verify(listOfActions.get(0), Mockito.times(2)).apply(Mockito.any());
		Mockito.verify(listOfActions.get(1), Mockito.times(2)).apply(Mockito.any());
	}
	
	@Test
	void testApplyForThreeAction() {
		//define all mocks in one list
		List<Action> listOfActions = List.of(Mockito.mock(Action.class), Mockito.mock(Action.class), Mockito.mock(Action.class));
		//create the tested object
		RequirementForTurn requirementForTurn = new RequirementForTurn(listOfActions);
		
		//we will run the test twice to see if it influences next call in anyway
		Mockito.when(listOfActions.get(0).apply(Mockito.any())).thenReturn(testHands.stream()).thenReturn(testHands.stream());
		Mockito.when(listOfActions.get(1).apply(Mockito.any())).thenReturn(testLongerHands.stream()).thenReturn(testLongerHands.stream());
		Mockito.when(listOfActions.get(2).apply(Mockito.any())).thenReturn(testLongHands.stream()).thenReturn(testLongHands.stream());
		
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongHands);
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongHands);
		
		Mockito.verify(listOfActions.get(0), Mockito.times(2)).apply(Mockito.any());
		Mockito.verify(listOfActions.get(1), Mockito.times(2)).apply(Mockito.any());
	}
	
	@Test
	void testApplyForEmptyHands() {
		//define all mocks in one list
		List<Action> listOfActions = List.of(Mockito.mock(Action.class));
		//create the tested object
		RequirementForTurn requirementForTurn = new RequirementForTurn(listOfActions);
		
		//we will run the test twice to see if it influences next call in anyway
		Mockito.when(listOfActions.get(0).apply(Mockito.any())).thenReturn(Stream.empty()).thenReturn(Stream.empty());
		
		assertThat(requirementForTurn.apply(Stream.empty()).collect(Collectors.toList())).isEmpty();
		assertThat(requirementForTurn.apply(Stream.empty()).collect(Collectors.toList())).isEmpty();
		
		Mockito.verify(listOfActions.get(0), Mockito.times(2)).apply(Mockito.any());
	}
	
	@Test
	void testApplyForNoActions() {
		RequirementForTurn requirementForTurn = new RequirementForTurn(List.of());
		
		assertThat(requirementForTurn.apply(testHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testHands);
		assertThat(requirementForTurn.apply(testLongerHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongerHands);
		assertThat(requirementForTurn.apply(testLongHands.stream()).collect(Collectors.toList())).hasSameElementsAs(testLongHands);
	}
	
}
