package assignment8;

/**
 *  This class will perform the hashing for the assignment. Creates a table of keys and values. Keys being the hashcode of the cards
 *  and the values being the card's information.
 *  
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 */

public class CoolHash
{
	/**
	 * Hash table - Array used to hold cards
	 */
	Card[] cardArray;
	
	/**
	 * Variable used to track size of array
	 */
	private int cardArraySize;
	
	/**
	 * Variable used to track number of items in array
	 */
	int itemsInCardArray = 0;
	
	/**
	 * Constructor of CoolHash which accepts a size for the hash table
	 * @param size of array
	 */
	public CoolHash(int size)
	{
		for(int i = 0; i < size; i++)
		{
			cardArray = new Card[size];
			this.cardArraySize = size;
				
		}
	}
	
	/**
	 * Method addCard places a card to the hash table.
	 * 
	 * @param card reference of class Card
	 * @return true if card could be put in table
	 * @return false if card could not be placed in table either because there is no room left or the card already exists
	 */
	public boolean addCard(Card card)
	{
		/**
		 * Variable to hold calculation to wrap around array
		 */
		int index = Math.abs(card.hashcode() % cardArraySize);
		
		/**
		 * Temporary variable to house card at spot in index
		 */
		Card cardToAdd = new Card(card.suit, card.value);
		
		if(itemsInCardArray >= cardArraySize)
		{
			return false;
		}
				
		for(int i = 0; i < cardArray.length; i++)
		{
			if(cardArray[index]== null)
			{
				cardArray[index] = cardToAdd;
				itemsInCardArray++;
				return true;
			}
			if(cardArray[index].itsATombstone == true)
			{
				cardArray[index] = cardToAdd;
				itemsInCardArray++;
				return true;
			}
			if(cardArray[index] != null && cardArray[index].equals(cardToAdd))
			{
				return false;
			}
			if(cardArray[index] != null && !(cardArray[index].equals(cardToAdd)))
			{
				index = Math.abs((index+1) % cardArraySize);
			}
		}
		return false;
	}

	/**
	 * Method removeCard removes a card from the hash table and leaves a tombstone in its place.
	 * This method returns the card removed, or null if no card was removed.
	 * 
	 * @param cardToBeDeleted the card the user wants to delete
	 * @return cardToBeDeleted card specified to be deleted by the user
	 * @return null if card is not in table
	 */
	public Card removeCard(Card cardToBeDeleted)
	{
		/**
		 * Variable to hold calculation to wrap around array
		 */
		int index = Math.abs(cardToBeDeleted.hashcode() % cardArraySize);
		
		if(cardArray[index] == null)
		{
			return null;
		}
		for(int i = 0; i < cardArray.length; i++)
		{
			if(cardArray[index].equals(cardToBeDeleted))
			{
				cardArray[index].makeTombstone();
				itemsInCardArray--;
				return cardToBeDeleted;
			}
			else
			{
				index = Math.abs((index+1) % cardArraySize);
			}
		}
		System.out.println("Card specified is not in Array");
		return null;
	}
	
	/**
	 * Method contains determines if a card is in the hash table.
	 * 
	 * @param cardSearch card user wants to search for
	 * @return true if the card was in the hash table
	 * @return false if the card was not in the hash table
	 */
	public boolean contains(Card cardSearch)
	{
		/**
		 * Variable to hold calculation to wrap around array
		 */
		int index = Math.abs(cardSearch.hashcode() % cardArraySize);
		
		//if array is empty or card is not in array return null
		if(cardArray[index] == null)
		{
			return false;
		}
		
		for(int i = 0; i < cardArray.length; i++)
		{
			if(cardArray[index].equals(cardSearch))
			{
				if(cardArray[index].itsATombstone == false)
				{
					return true;
				}
			}
			else
			{
				index = Math.abs((index+1) % cardArraySize);
			}
		}
		return false;
		
	}
	/**
	 * Method rehash when given a new table size will make a new larger table and move items from the old table to the larger table
	 * one at a time (starting at the first index of the table and moving forward). Tombstones are not be moved when rehashing.
	 * 
	 * @param newSize size of new table user inputs
	 * @return true if the table can be rehashed
	 * @return false it can't be resized because the user specified a new table that will not fit all of old tables values
	 */
	public boolean rehash(int newSize)
	{
		if(newSize < itemsInCardArray)
		{
			return false;
		}
		
		Card[] oldTable = cardArray;
		this.cardArray = new Card[newSize];
		
		cardArraySize = newSize;
		for(int i = 0; i < oldTable.length; i++)
		{
			if(oldTable[i]!= null && oldTable[i].itsATombstone == false)
			{
				addCard(oldTable[i]);
			}
		}
		return true;	
	}
	/**
	 * Method getSize returns the size of the table (how many elements could be in the table) 
	 * 
	 * @return cardArraySize size of array
	 */
	public int getSize()
	{
		return cardArraySize;
	}
	
	/**
	 * Method getNumElements returns the number of elements (how many elements are in the table), this should not count tombstones.
	 * 
	 * @return itemsInCardArray returns how many elements are in array
	 */
	public int getNumElements()
	{
		return itemsInCardArray;
	}
	
	/**
	 * Method toString returns the hash table in a nice readable format (see the example output for the format expected). 
	 * Note "TOMBSTONE" is used to denote a tombstone and null is used for open spaces.
	 * 
	 * @return output String of what array contains
	 */
	public String toString()
	{
		String output = "";
		
		for(int i = 0; i < cardArray.length; i++)
		{
			if(cardArray[i] == null)
			{
				output += "[" + i + "] : " + "null\n";
			}
			else
			{
				output += "[" + i + "] : " + cardArray[i].toString() + "\n";
			}
			
		}return output;
	}
}

