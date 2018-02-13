package Project2;

/**
 * This class keeps track of a (string) company name and a set of complaint IDs associated with that company.
 * 
 * The methods...
 * 1)provide a way to add a complaint id
 * 2)get a list of the complaint IDs
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 *
 */
public class Company
{
	/**
	 * Variable used to identify company name
	 */
	private String companyName;
	/**
	 * Variable used to identify set of complaint ID's associated with that company
	 */
	private String complaintIdentitySet;
	/**
	 * First element in Linked list
	 */
	private Node head;
	/**
	 * size of linked list
	 */
	private int numItems = 0;
	/**
	 * last element in the linked list
	 */
	private Node tail;
	/**
	 * Inner private class for Node 
	 * @param <E>
	 */
	private class Node
	{
		/**
		 * Value inside node "generic"
		 */
		private String singleIdentityNumber;
		/**
		 * pointer to next node in linked list
		 */
		private Node next;
		
		Node()
		{
			singleIdentityNumber = null;
			next = null;
		}
			
		/**
		 * Constructor that takes in a defined value
		 * @param item holds whatever value the user is entering
		 */
		Node(String id)
		{ 
			this.singleIdentityNumber = id;
			next = null;
		}
		/**Gets value for item
		 * @return current value
		 */
		public String getId() 
		{
			return singleIdentityNumber;
		}
		/**
		 * Sets value for item
		 * @param item value to set
		 */
		public void setId(String id) 
		{
			this.singleIdentityNumber = id;
		}
		/**
		 * Getter
		 * @return next gets next value in list
		 */
		public Node getNext() 
		{
			return next;
		}
		/**
		 * Setter
		 * @param next sets next value in list
		 */
		public void setNext(Node next) 
		{
			this.next = next;
		}	
	}
	
	/**
	 * Constructor that creates linked list of Company data.
	 * 
	 * @param name name of company
	 * @param complaintId number used to identify complaint
	 */
	public Company(String name, String complaintId) 
	{
		this.companyName = name;
		this.complaintIdentitySet = complaintId;
		head = new Node(complaintId);
		tail = head;
		numItems++;
		
	}
	
	/**
	 * Method addId will add a complaint id to a company's set of IDs (linked list).
	 * 
	 * @param id item value of item being added
	 * @throws NullPointerException if the specified element is null and this queue does not permit null elements
	 */
	public void addId(String id) 
	{
		if(id==null)
		{
			throw new NullPointerException();
		}
		Node node = new Node(id);
		if(this.isEmpty())
		{
			this.head = node;
			this.tail = node;
			this.numItems++;
		}
		else
		{
			this.tail.setNext(node);
			this.tail=node;
			this.tail.setNext(null);
			this.numItems++;
		}
	}
	/**
	 * Method getName returns the name of the company.
	 * 
	 * @return companyName name of company
	 */
	public String getName() 
	{
		return this.companyName;
	}
	/**
	 * Method getIds returns list of Ids.
	 * 
	 * @return listOfIds set of complaint Id's for company
	 */
	public String[] getIds() 
	{
		String[] listOfIds = new String[numItems];
		Node next = head;
		int i = 0;
		
		while(next != null)
		{
			listOfIds[i] = next.getId();
			i++;
			next = next.next;
		}
		return listOfIds;
	}
	/**
	 * Method equals determines if the company names are equal or not, if they are it returns true.
	 * Names can not be equal to anything that is not a name.
	 * 
	 * @param otherObject company being entered
	 */
	public boolean equals(Object otherObject) 
	{
		if(!(otherObject instanceof Company))
		{
			return false;
		}
		else
		{
			Company o = (Company) otherObject;
			return this.companyName.equals(o.getName());
		}
	}
	/**
	 * Method hashCode returns the hash code of the company name.
	 */
	public int hashCode() 
	{
		return companyName.hashCode();
	}
	
	/**
	 * Method used to determine if list is empty.
	 * 
	 * @return true if list is empty, false if items in list
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	/**
	 * Method used to determine size of list
	 * 
	 * @return numItems number of elements in list
	 */
	public int size()
	{
		return numItems;
	}
}