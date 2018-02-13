package Project1;

import java.util.Iterator;

/**
 *  A data structure (based on a queue) to represent a grocery store line. This class is a type of SimpleQueue
 *  specifically designed to order a group of people. Grocery lines can only contain people and can be compared 
 *  to each other based on the number of items left to process in the line. The lines also have id numbers.
 *  
 *  @author Navid Galt
 *  INFS 519
 * 	Spring 2017
 */
class GroceryLine extends SimpleQueue<Person> implements Comparable<GroceryLine> 
{
	/**Global variable used to represent line number, used later for line order*/
	private int identifier = 0;
	
	/**
	 *  Creates a grocery store line with a given ID.
	 *  
	 *  @param id the grocery line id
	 */
	public GroceryLine(int id) 
	{
		this.identifier = id;
	}
	
	/**
	 *  Returns the ID of the grocery line.
	 *  
	 *  @return the ID of the line.
	 */
	public int getId() 
	{
		return identifier;
	}
	
	/**
	 *  Sums up all items for all people in the line.
	 *  
	 *  @return the number of items in the line.
	 */
	public int itemsInLine() 
	{
		int counter = 0;
		Iterator<Person> iter = iterator();
		while(iter.hasNext())
		{	
			Person p1 = iter.next();
			counter += p1.getNumItems();
		}
		return counter;
	}
	
	/**
	 *  Compare one grocery line to another based on
	 *  the number of items in the line and then, if
	 *  the two lines are tied, then compare by their id.
	 *  
	 *  @param otherLine the other grocery line to compare to this line
	 *  @return less than 0 if this line is "less than" otherLine,
	 *  greater than 0 if this line is "greater than" other line,
	 *  and 0 if the two lines are equal
	 */
	public int compareTo(GroceryLine otherLine) 
	{
		if(this.itemsInLine() < otherLine.itemsInLine())
		{
			return -1;
		}
		if(this.itemsInLine() > otherLine.itemsInLine())
		{
			return 1;
		}
		if(this.getId() < otherLine.getId())
		{
			return -1;
		}
		if(this.getId() > otherLine.getId())
		{
			return 1;
		}
		return 0;
	}
	
	/**
	 *  Processes (removes) one item from the first person in line. If the person has no more
	 *  items they are removed from the line.
	 */
	public void processItem() 
	{
		Person people = peek();
		if(people != null)
		{
			people.removeItem();
			if(people.done())
			{
				poll();
			}
		}
	}

	/**
	 *  Converts the line to a string.
	 *  
	 *  @return result a string representation of the line
	 */
	public String toString() 
	{
		String result =""; 
		
		result += getId() + ": ";
		for(Iterator<Person> i = iterator(); i.hasNext();)
		{
			result += i.next().toString() + " "; 
		}
		result += "= " + size() + " shopper(s) with " + itemsInLine() + " item(s)";
				
	    return result;
	}
}
