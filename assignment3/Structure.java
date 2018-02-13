package assignment3;

import java.util.*;

/**
 * 
 * INFS 519
 * Spring 2017
 * <p> Class Structure houses node class and creates a Queue singly linked list to hold elements being read in <\p>
 * @author Navid Galt
 * @link  Source of methods: (http://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html)
 */

class Structure<T> implements Queue<T>
{
	/**First element in Linked list*/
	private ListItem<T> head;
	/**size of linked list*/
	private int numItems = 0;
	/**last element in the linked list*/
	private ListItem<T> tail;
	
	/**
	 * Inner private class for Node 
	 * @param <E>
	 */
	private class ListItem<T>
	{
		/**value inside node "generic"*/
		private T item;
		/**pointer to next node in linked list*/
		private ListItem<T> next;
		
		ListItem()
		{
			item = null;
			next = null;
		}
			
		/**
		 * Constructor that takes in a defined value
		 * @param item holds whatever value the user is entering
		 */
		ListItem(T item)
		{ 
			this.item = item;
			next = null;
		}
		/**Gets value for item
		 * @return current value
		 */
		public T getItem() 
		{
			return item;
		}
		/**
		 * Sets value for item
		 * @param item value to set
		 */
		public void setItem(T item) 
		{
			this.item = item;
		}
		/**
		 * Getter
		 * @return next gets next value in list
		 */
		public ListItem<T> getNext() 
		{
			return next;
		}
		/**
		 * Setter
		 * @param next sets next value in list
		 */
		public void setNext(ListItem<T> next) 
		{
			this.next = next;
		}	
	}
	

	/**
	 * Retrieves and removes the head of this queue
	 * @throw NoSuchElementException if list is empty
	 * @return node head of list
	 */
	public T remove() 
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException();
		}
		ListItem<T> node = head;
		this.head = this.head.next;	
		numItems--;
		
		return node.getItem();
	}
	
	/**
	 * Retrieves and removes the head of this queue
	 * @return node the head of the list
	 * @return null if this list is empty
	 */
	public T poll()
	{
		if(this.isEmpty())
		{
			return null;
		}
		ListItem<T> node = head;
		this.head = this.head.next;	
		numItems--;
		
	return node.getItem();
		
	}

	/**
	 * Adds one node to linked list, increases list respectively.
	 * @param item value of item being added
	 * @throw NullPointerException if the specified element is null and this queue does not permit null elements
	 */
	public boolean add(T item)
	{
		boolean flag = false;
		if(item==null)
	    {
			throw new NullPointerException();
	    }
		ListItem<T> node = new ListItem<T>(item);
		if(this.isEmpty())
		{
			this.head = node;
			this.tail = node;
			this.numItems++;
			flag = true;
		}
		else
		{
			this.tail.setNext(node);
			this.tail=node;
			this.tail.setNext(null);
			this.numItems++;
			flag = true;
		}
		return flag;

	} 
	
	/**
	 * Inserts the specified element into this queue
	 * @param item value in list
	 */
	public boolean offer(T item)
	{
		if(item==null)
	     {
	          return (Boolean)null;
	     }
		ListItem<T> node = new ListItem<T>(item);
		boolean flag = false;
		if(this.isEmpty())
		{
			this.head = node;
			this.tail = node;
			this.numItems++;
			flag = true;
		}
		else
		{
			this.tail.setNext(node);
			this.tail=node;
			this.tail.setNext(null);
			this.numItems++;
			flag = true;
		}
		
		return flag;

		
	}
	
	/**
	 * Element method returns value in array if exists, if not throws exceptions
	 * @return item value of head node
	 * @throws NoSuchElementException if empty
	 */
	public T element()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		else
		{
			return head.item;
		}
	}
	
	/**
	 * Peek method allows user to see first value, returns null if empty
	 * @return item head of list
	 * @return null if list is empty
	 */
	public T peek()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return head.item;
		}
	}

	/**
	 * Reads in objects and converts to characters of string
	 * @return result a string representation of the object. 
	 */
	public String toString()
	{
		String result ="";
		for(Iterator<T> i = iterator(); i.hasNext();)
		{
			result += i.next().toString();
		}
		
	     return result;
	}
	
	/**
	 * Breaks the connection between nodes
	 */
	public void clear()
	{
		head = null;
		numItems=0;
	}
	
	/**
	 * Method used to determine if list is empty
	 * @return true if list is empty, false if items in list
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	
	/**
	 * Method used to determine size of list
	 * @return numItems number of elements in list
	 */
	public int size()
	{
		return numItems;
	}

	/**
	 * Iterator method is used to iterate through the list
	 * @returns Iterator one iterator
	 */
	public Iterator<T> iterator()
	{
		return new Iterator<T>() 
		{
			/**variable current used to hold position for current node*/
			ListItem<T> current = null;
			/**
			 * Returns true if the iteration has more elements
			 * @return true if more, false otherwise
			 */
			public boolean hasNext()
			{
				if(current == null)
				{
					return head != null;
				}
				else
				{
					return(current.next != null);
				}
			}
			
			/**Returns the current element and moves (if possible)
			 * to next element making it the current element. 
			 * @return next element in the iteration
			 */
			public T next() 
			{
				if(current == null)
				{
					current = head;
				}
				else
				{
					current = current.getNext();
				}
				
				return current.getItem();
			}
			
			/** remove is an unsupported method
			 *  @throws UnsupportedOperationException if not supported
			 */
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
	}
	
	/*-------------- DO NOT CHANGE ANYTHING BELOW THIS LINE --------------*/
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean addAll(Collection<? extends T> c)  
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean contains(Object o)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public Object[] toArray()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean containsAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean equals(Object o)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public int hashCode()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * DO NOT CHANGE THIS
	 */
	public <E> E[] toArray(E[] a)
	{
		throw new UnsupportedOperationException();
	}

}
