package Project1;

/**
 *  Class PriorityQueue creates a priority queue for Grocery Lines
 *  
 *  @author Navid Galt
 *  INFS 519
 * 	Spring 2017
 */
class PriorityQueue<T extends Comparable<T>> extends SimpleQueue<T>
{
	
	/**
	 * Compares specified element to other elements in list if there are any
	 * and inserts it into this queue based on priority.
	 * 
	 * @param item value of node which is the line
	 * {@inheritDoc}
	 */
	@Override
	public boolean offer(T item) 
	{
		if(item==null)
	     {
	          return (Boolean) null;
	     }	
		
		ListItem<T> node = new ListItem<T>(item);
		
		if(this.isEmpty())
		{
			this.head = node;
			this.tail = node;
			this.numItems++;
			return true;
		}
		
		if(item.compareTo(head.getItem()) < 0)
		{
			node.setNext(head);
			this.head = node;
			numItems++;
			return true;
		}
		
		/**variable used to track head*/
		ListItem<T> previous = head;
		/**variable used to keep track of node after head*/
		ListItem<T> current = head.getNext();	
		do {
            if(item.compareTo(previous.getItem()) > 0 && current == null)
            {
                previous.setNext(node);
                this.tail = node;
                numItems++;
                return true;
            }
            if(item.compareTo(previous.getItem()) > 0 && item.compareTo(current.getItem()) < 0)
            {
                node.setNext(current);
                previous.setNext(node);
                numItems++;
                return true;
            }
            previous = current;
            current = current.getNext();
        }while(previous != null);
		
        throw new UnsupportedOperationException();
    }
	
	/** 
	 * Re-prioritizes/updates the queue after an item is added or removed;
	 * accepts an item and "updates" it's place in the queue (updates it's priority)
	 * 
	 * @param item value of node which is the line
	 */
	public void update(T item) 	
	{
		/**Variable used to keep track of node removed*/
		ListItem<T> lineRemoved = null;
		/**Variable to temporarily store old head*/
		ListItem<T> temp = head;

		if(item.compareTo(temp.getItem()) == 0)
		{
			head = temp.next;
			numItems--;
			lineRemoved = temp;
			lineRemoved.setNext(null);	
		}
		
		/**Variable to track head*/
		ListItem<T> previous = head;
			
		while(temp.getNext() != null)
		{
			temp = temp.getNext();
			if(item.compareTo(temp.getItem()) == 0)
			{
				previous.setNext(temp.getNext());
				numItems--;
				temp.setNext(null);
				lineRemoved = temp;
			}
			previous = temp;
		}
		
		if(lineRemoved != null)
		{
			offer(lineRemoved.item);
		}
	}
}
