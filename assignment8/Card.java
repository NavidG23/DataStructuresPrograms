package assignment8;

/**
 * Class card is used to represent playing cards. Cards have suits and values. This class will interact with class CoolHash to create a table of cards.
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 */
public class Card
{
	/**
	 * Variable used to keep track of card suit
	 * */
	Suit suit;
	/**
	 * Variable used to keep track of card value
	 */
	CardValue value;
	/**
	 * Variable used to keep track of tombstone
	 */
	boolean itsATombstone = false;
	/**
	 * Constructor for class card,  accepts a suit and a card value.
	 * 
	 * @param suit of cards
	 * @param values of cards
	 */
	public Card(Suit suit, CardValue values)
	{
		this.suit = suit;
		this.value = values;
	}
	
	public void makeTombstone()
	{
		itsATombstone = true;
	}
	/**
	 * Computes the "value" of the card by adding the "suit" to the "value". Returns just the value of card if it is a joker.
	 * 
	 * @return hashcode computed using the suit and value of the card.
	 */
	public int hashcode()
	{
		int hashcode = 0;

		if(value.getValue() == 0)
		{
			hashcode = value.getValue();
			return hashcode;
		}
		else
		{
			hashcode = (suit.getValue() + value.getValue());
			return hashcode;
		}
	}
	/**
	 * Method getSuit returns the suit of the card, as an instance of Suit, or null if the card has no suit.
	 * 
	 * @return null for joker or if suit is out of bounds
	 * @return suit of card
	 */
	public Suit getSuit()
	{
		if(value.getValue() == 0)
		{
			return null;
		}
		else
		{
			return suit;
		}
	}
	/**
	 * Method getValue returns the value of the card, as an instance of CardValue, or null if the card has no value
	 * 
	 * @return null
	 * @return value of card
	 */
	public CardValue getValue()
	{
		if(value.getValue() < 0 || value.getValue() > 13)
		{
			return null;
		}
		else
		{
			return value;
		}
	}
	
	/**
	 * Method equals determines if the cards are equal or not, if they have the same suit and value it returns true.
	 * Cards can not be equal to anything that is not a card.
	 * 
	 */
	public boolean equals(Object card)
	{
		if(!(card instanceof Card))
		{
			return false;
		}
		Card ca = (Card) card;
		if(this.value.equals(ca.value) && this.suit == (ca.suit))
		{
			return true;
		}
		return false;
	}
	/**
	 * Method toString returns a string representation of the card in the format "[VALUE] of [SUIT]". 
	 */
	public String toString()
	{
		if(itsATombstone == true)
		{
			return "TOMBSTONE";
		}
		if(value.getValue() == 0)
		{
			return "JOKER";
		}
		else
		{
			return value + " of " + suit;
		}
	}
}
