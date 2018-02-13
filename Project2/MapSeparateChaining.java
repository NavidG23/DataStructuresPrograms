package Project2;

/**
 * Class MapSeparateChaining creates a hash table that will be able to read in list of companies 
 * and put them into an array/table with keys that translate to a company's hash code. Companies that have identical keys will
 * then be put into a linked list at the same index.
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 * 
 * @param <K> generic key (company name in this case)
 * @param <V> generic value (complaint id's in this case)
 */
public class MapSeparateChaining<K,V>
{
	/**
	 * Hash table - Array used to hold names
	 */
	private HashEntry<K,V>[] hashEntries;
	
	/**
	 * Variable used to track size of array
	 */
	private int tableSize = 0;
	
	/**
	 * Variable used to track number of companies in table
	 */
	int numItems = 0;

	/**
	 * Private inner class creating list of companies, holding the key and value of company also has next used for identifying next company.
	 */
	private class HashEntry<K,V>
	{
		K key;
		V value;
		HashEntry<K, V> next;

		HashEntry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}	      
	}	
	/**
	 * Constructor for separate chaining class reads in a capacity for table
	 * 
	 * @param capacity size of table
	 */
	@SuppressWarnings("unchecked")
	public MapSeparateChaining(int capacity) 
	{
		this.tableSize = capacity;
		hashEntries = new HashEntry[capacity];
	}
	
	/**
	 * Method searchKey finds company's key which is being searched for
	 * 
	 * @param key company being searched for
	 * @return company
	 */
	public boolean searchKey(K key)
	{
		return get(key)!=null;
	}
	
	/**
	 * The put method adds a key and value
	 * @param key company name
	 * @param value list of complaint ids
	 */
	@SuppressWarnings("unchecked")
	public void put(K key, V value) 
	{
		if(numItems >= tableSize - 1)
		{
			rehash(tableSize * 2);
		}
		int index = Math.abs(key.hashCode() % tableSize);
		if(hashEntries[index] == null)
		{
			hashEntries[index] = new HashEntry<K,V>(key, value);
		}
		else
		{
			HashEntry<K, V> tempHead = hashEntries[index];
			while(tempHead != null)
			{	
				if(tempHead.key.equals(key))
				{
					tempHead.value = value;
					break;
				}
				if(tempHead.next == null)
				{
					tempHead.next = new HashEntry<K,V>(key, value);
					break;
				}
				tempHead = tempHead.next;
			}
		}
		numItems++;
	}
	
	/**
	 * The get method, gets the searched company's complaint ids from the list of companies
	 * 
	 * @param key company name
	 * @return null if array spot is empty
	 * @return node.value if it is not empty
	 */
	@SuppressWarnings("unchecked")
	public V get(K key)
	{
		  int index = Math.abs(key.hashCode() % tableSize);
		  
          if (hashEntries[index] == null)
          {
                return null;
          }
          HashEntry<K,V> node = hashEntries[index];
          
          while(node != null)
          {
        	  if(node.key.equals(key))
        	  {
        		  return node.value;
        	  }
        	  node = node.next;
          }
        return null;
	}
	
	/**
	 * Method rehash is used to double array/table size and move all elements in old table,
	 * to their appropriate spot in the new table
	 * 
	 * @param newSize new capacity of table
	 */
	@SuppressWarnings("unchecked")
    public void rehash(int newSize) 
    {
    	tableSize = tableSize * 2;
    	HashEntry<K,V>[] oldTable = hashEntries;
    	hashEntries = new HashEntry[tableSize];

    	for(HashEntry<K,V> company : oldTable)
    	{
    		if(company != null)
    		{
    			put(company.key, company.value);
    			numItems++;
    		}
    	}
    }
}
