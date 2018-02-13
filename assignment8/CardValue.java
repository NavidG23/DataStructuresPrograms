package assignment8;

/**
 * Enum CardValue houses the card's values.
 * 
 * @author Professor Russell
 * INFS 519
 * Spring 2017
 */
enum CardValue {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
	JACK(11), QUEEN(12), KING(13),
	JOKER(0);
	
	private int value;
	
	private CardValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}