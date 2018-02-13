package Project1;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Class SimpleQueue is a generic data structure that is used later in the implementation of the
 *  GroceryLine class
 *  
 *  @author Navid Galt
 *  INFS 519
 * 	Spring 2017
 */
class SimpleQueue<T> extends AbstractQueue<T> 
{
	/**Variable used for first element in linked list*/
	protected ListItem<T> head;
	/**Variable used for size of linked list*/
	protected int numItems = 0;
	/**Variable used for last element in the linked list*/
	protected ListItem<T> tail;
	
	/**
	 * Inner private class for a Node
	 * 
	 * @param <T> this describes the type parameter which is generic
	 */
	protected class ListItem<T>
	{
		/**value inside node "generic"*/
		protected T item;
		/**pointer to next node in linked list*/
		protected ListItem<T> next;
		
		ListItem()
		{
			item = null;
			next = null;
		}
			
		/**
		 * Constructor that takes in a defined value
		 * 
		 * @param item holds whatever value the user is entering
		 */
		ListItem(T item)
		{ 
			this.item = item;
			next = null;
		}
		/**Gets value for item
		 * 
		 * @return current value
		 */
		public T getItem() 
		{
			return item;
		}
		/**
		 * Sets value for item
		 * 
		 * @param item value to set
		 */
		public void setItem(T item) 
		{
			this.item = item;
		}
		/**
		 * Getter
		 * 
		 * @return next gets next value in list
		 */
		public ListItem<T> getNext() 
		{
			return next;
		}
		/**
		 * Setter
		 * 
		 * @param next sets next value in list
		 */
		public void setNext(ListItem<T> next) 
		{
			this.next = next;
		}	
	}

	/**
	 * Inserts the specified element into this queue
	 * 
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
	 * Peek method allows user to see first value, returns null if empty
	 * 
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
	 * Retrieves and removes the head of this queue
	 * 
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
	 * Method used to determine size of list
	 * 
	 * @return numItems number of elements in list
	 */
	public int size()
	{
		return numItems;
	}
	
	/**
	 * Method used to determine if list is empty
	 * 
	 * @return true if list is empty, false if items in list
	 */
	public boolean isEmpty()
	{
		return head == null;
	}
	

	/**
	 * Iterator method is used to iterate through the list
	 * 
	 * @return Iterator one iterator
	 */
	public Iterator<T> iterator()
	{
		return new Iterator<T>() 
		{
			/**variable current used to hold position for current node*/
			ListItem<T> current = null;
			/**
			 * Returns true if the iteration has more elements
			 * 
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
			 * 
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
			 * 
			 *  @throws UnsupportedOperationException if not supported
			 */
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
	}
}