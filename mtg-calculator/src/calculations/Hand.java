package calculations;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class Hand {
	private Map<Card, Integer> cardsInHand;
	
	private Map<Card, Integer> cardsInPlay;

	private Map<Card, Integer> cardsInGraveyard;
	
	private int probability = 1;

	/*
	 * Hand is an object that stores all the cards that were drawn/played.
	 * it also stores its own probability of occurring.
	 */
	public Hand (Decklist decklist, int numberOfCardDrawn) {
		cardsInHand = new HashMap<>();
		cardsInPlay = new HashMap<>();
		
		for (Card card : decklist.getCards()) {
			cardsInHand.put(card, 0);
			cardsInPlay.put(card, 0);
		}
	}
	
	public Hand duplicate() {
		//TODO
		return this;
	}
	
	public int getProbability() {
		return probability;
	}
	
	public Stream<Hand> drawACard() {
		/*
		 * Return all hands where the board state is the same as here but a card drawn was drawn there.
		 */
		return null;
	};
	
	public Stream<Hand> endTheTurn() {
		/*
		 * Return all hands where the board state is the same as here but the turn was ended there.
		 */
		return null;
	};
	
	public Stream<Hand> findCardInHand(Card card) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in hand.
		 * 
		 * This card will be put into the graveyard. To prevent this and put it into play instead, specify parameter "consume".
		 */
		return findCardInHand(card, true);
	};
	
	public Stream<Hand> findCardInHand(Card card, boolean consume) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in hand.
		 * 
		 * This card will be put into the graveyard. To prevent this and put it into play instead, set parameter "consume" to false.
		 */
		return null;
	};
	
	public Stream<Hand> findCardInPlay(Card card) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in play.
		 * 
		 * This card will be left in play. To prevent this and put it into graveyard instead, specify parameter "consume".
		 */
		return findCardInPlay(card, false);
	};
	
	public Stream<Hand> findCardInPlay(Card card, boolean consume) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in play.
		 * 
		 * This card will be left in play. To prevent this and put it into graveyard instead, set parameter "consume" to true.
		 */
		
		//the card either is in play or is not in play
		//there's no random element here (content of play is predetermined)
		if (cardsInPlay.get(card) >= 0) {
			if (consume) {
				cardsInPlay.put(card, cardsInPlay.get(card)-1);
				cardsInGraveyard.put(card, cardsInGraveyard.get(card)+1);
			}
			return Stream.of(this);
		} else {
			return Stream.empty();
		}
	};
	
	public Stream<Hand> findCardInGrave(Card card) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in graveyard.
		 * 
		 * This card will be left in graveyard. To prevent this and put it into exile instead, specify parameter "consume".
		 */
		return findCardInGrave(card, false);
	};
	
	public Stream<Hand> findCardInGrave(Card card, boolean consume) {
		/*
		 * Return all hands where the board state is the same as here but there is a specific card already in graveyard.
		 * 
		 * This card will be left in your graveyard. To prevent this and put it into exile instead, set parameter "consume" to true.
		 */
		
		//the card either is in graveyard or is not in graveyard
		//there's no random element here (content of graveyard is predetermined)
		
		if (cardsInGraveyard.get(card) >= 0) {
			if (consume) {
				cardsInGraveyard.put(card, cardsInGraveyard.get(card)-1);
			}
			return Stream.of(this);
		} else {
			return Stream.empty();
		}
	};
}
