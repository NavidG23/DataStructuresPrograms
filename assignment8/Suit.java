package assignment8;

/**
 * Enum Suit houses the card's suit
 * 
 * @author Professor Russell
 * INFS 519
 * Spring 2017
 */
enum Suit {
	HEARTS(0), CLUBS(1), DIAMONDS(2), SPADES(3);
	
	private int value;
	
	private Suit(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}