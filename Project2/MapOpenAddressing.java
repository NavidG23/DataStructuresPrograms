package Project2;

/**
 * Class MapOpenAddressing creates a hash table that will be able to read in list of companies 
 * and put them into an array/table with keys that translate to a company's hash code.
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 * 
 * @param <K> Generic key (company name in this case)
 * @param <V> generic value (complaint id's in this case)
 */
public class MapOpenAddressing<K,V>
{
	/**
	 * Hash table - Array used to hold names
	 */
	HashEntry<K,V>[] hashEntries;
	
	/**
	 * Variable used to track size of array
	 */
	private int tableSize = 0;
	
	/**
	 * Variable used to track number of entries in array
	 */
	public int numItems = 0;
	
	/**
	 * Private inner class - creates an array with hash entries of Object K, V which equate to company and complaint id respectively 
	 * @author Navid Galt
	 *
	 */
	private class HashEntry<K,V>
	{
	      private K keyOfTable;
	      private V valueInTable;
	 
	      HashEntry(K key, V value) 
	      {
	            this.keyOfTable = key;
	            this.valueInTable = value;
	      }   
	      /**
	       * Returns company name
	       * @return
	       */
	      public K getKey() 
	      {
	            return keyOfTable;
	      }
	      /**
	       * Gets value - complaint id
	       * @return
	       */
	      public V getValue() 
	      {
	            return valueInTable;
	      }
	}
	
	/**
	 * Constructor of MapOpenAddressing which accepts a size for the hash table.
	 * 
	 * @param capacity size of array
	 */
	@SuppressWarnings("unchecked")
	public MapOpenAddressing(int capacity) 
	{
		this.tableSize = capacity;
		hashEntries = new HashEntry[tableSize];	
	}
	
	/**
	 * The put method adds a key and value to the table when called.
	 * 
	 * @param key company name
	 * @param value list of complaint ids
	 */
	@SuppressWarnings("unchecked")
	public void put(K key, V value) 
	{
		if(numItems >= tableSize - 1)
		{
			rehash();
		}
		int index = Math.abs(key.hashCode() % tableSize);
		while (hashEntries[index] != null && !hashEntries[index].getKey().equals(key))
		{
            index = (index + 1) % tableSize;
		}
		hashEntries[index] = new HashEntry(key, value);
		numItems++;
	}
	
	/**
	 * The get method, gets the key of company name from the list of companies.
	 * 
	 * @param key name of company
	 * @return null if array spot is empty
	 * @return value if it is not empty
	 */
	public V get(K key) 
	{
		  int index = Math.abs(key.hashCode() % tableSize);
          while (hashEntries[index] != null && !hashEntries[index].getKey().equals(key)) 
          {
        	  index = (index + 1) % tableSize;
          }
          if (hashEntries[index] == null)
          {
                return null;
          }
          else
          {
        	  return hashEntries[index].getValue();
          }
	}
	
	/**
	 * Method rehash is used to double array/table size and move all elements in old table,
	 * to their appropriate spot in the new table.
	 */
    public void rehash() 
    {
    	int oldCapacity = tableSize;
    	HashEntry<K,V> oldTable[] = hashEntries;
		
		int newCapacity = oldCapacity * 2 + 1;
		@SuppressWarnings("unchecked")
		HashEntry<K,V> newTable[] = (HashEntry<K,V>[]) new HashEntry[newCapacity];
		
		for(int i = 0; i < oldTable.length; i++)
		{		
			if(oldTable[i] != null)
			{
				int index = Math.abs(oldTable[i].getKey().hashCode() % newCapacity);
				newTable[index] = oldTable[i];
			}
		}	
		hashEntries = newTable;
		tableSize = newCapacity;
    }
}
