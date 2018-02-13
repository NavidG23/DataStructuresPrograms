package Project1;

/**
 *  Class Person represent one person in line for the Grocery Store simulation. This class will allow this person
 *  to function properly in line/how a person would function in the typical grocery line.
 *  
 *  @author Navid Galt
 *  INFS 519
 * 	Spring 2017
 */

class Person 
{
	/**Variable used to represent number of items one shopper has*/
	private int numberItems;
	
	/**
	 *  Sets up a person with a given number
	 *  of items.
	 *  
	 *  @param numItems the number of items the person starts with
	 */
	public Person(int numItems) 
	{
		this.numberItems = numItems;
	}
	
	/**
	 *  Gets how many items the person still has.
	 *  
	 *  @return how many items the person still has.
	 */
	public int getNumItems() 
	{
		return numberItems;
	}
	
	/**
	 *  Removes one item from this person (i.e. "checks out" one item from this person.)
	 */
	public void removeItem() 
	{
		numberItems -= 1;
	}
	
	/**
	 *  Indicates whether or not this person has any more items left to "check out".
	 *  
	 *  @return whether or not there this person still has more items to check out
	 */
	public boolean done() 
	{
		if(numberItems == 0)
		{
			return true;
		}
		else
		return false;
	}
	
	/**
	 *  Makes a string representation of this person.  
	 *  
	 *  @return the string representing the person and their items
	 */
	public String toString() 
	{
		return "Person("+getNumItems()+")";
	}
}
