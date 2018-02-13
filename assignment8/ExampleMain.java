package assignment8;

/**
 * Class card is used to represent playing cards. Cards have suits and values. This class will interact with class CoolHash to create a table of cards.
 * 
 * @author Professor Russell
 * INFS 519
 * Spring 2017
 */
class ExampleMain {
	public static void main(String[] args) 
	{
		Card aceOfClubs = new Card(Suit.CLUBS, CardValue.ACE);
		System.out.println("\nString value of the Ace of Clubs is: " + aceOfClubs);
		System.out.println("Value of the Ace of Clubs is: " + aceOfClubs.getValue());
		System.out.println("Suit of the Ace of Clubs is: " + aceOfClubs.getSuit());
		System.out.println("Hashcode of the Ace of Clubs is: " + aceOfClubs.hashcode());
		
		Card aceOfClubs2 = new Card(Suit.CLUBS, CardValue.ACE);
		System.out.println("Ace of Clubs equal to Ace of Clubs? " + aceOfClubs.equals(aceOfClubs2));
		
		Card twoOfHearts = new Card(Suit.HEARTS, CardValue.TWO);
		System.out.println("Ace of Clubs equal to Two of Hearts? " + aceOfClubs.equals(twoOfHearts));
		
		Card joker = new Card(null, CardValue.JOKER);
		System.out.println("\nString value of Joker is: " + joker);
		System.out.println("Value of the Joker is: " + joker.getValue());
		System.out.println("Suit of Joker is: " + joker.getSuit());
		System.out.println("Hashcode of the Joker is: " + joker.hashcode());
		
		System.out.println("\nMaking a cool new hash table:");
		CoolHash ch = new CoolHash(3);
		System.out.println(ch);
		
		System.out.println("Size of the hash table: " + ch.getSize());
		System.out.println("Number of elements: " + ch.getNumElements());
		System.out.println("Hash table contains Ace of Clubs? " + ch.contains(aceOfClubs));
		
		System.out.println("\nAdding the Ace of Clubs:");
		ch.addCard(aceOfClubs);
		System.out.println(ch);
		
		System.out.println("Hash table contains Ace of Clubs? " + ch.contains(aceOfClubs));
		
		System.out.println("\nAdding the Ace of Clubs again:");
		ch.addCard(aceOfClubs2);
		System.out.println(ch);
		
		System.out.println("Adding the Two of Hearts:");
		ch.addCard(twoOfHearts);
		System.out.println(ch);
		
		System.out.println("Adding the Ten of Diamonds:");
		ch.addCard(new Card(Suit.DIAMONDS, CardValue.TEN));
		System.out.println(ch);
		
		System.out.println("Adding the Six of Clubs:");
		ch.addCard(new Card(Suit.CLUBS, CardValue.SIX));
		System.out.println(ch);
		
		System.out.println("Rehashing to Size 7:");
		ch.rehash(7);
		System.out.println(ch);
		
		System.out.println("Removing the Ace of Clubs:");
		ch.removeCard(aceOfClubs);
		System.out.println(ch);
		
		System.out.println("Hash table contains Ace of Clubs? " + ch.contains(aceOfClubs));
		
		System.out.println("\nAdding back the Ace of Clubs:");
		ch.addCard(aceOfClubs);
		System.out.println(ch);
		
		System.out.println("Hash table contains Ace of Clubs? " + ch.contains(aceOfClubs));
	}
	
}
