package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** * <p>Dynamic Array</p> 
* <p>Creates a new Array that doubles in size every time the max size of input integers is reached. </p> 
* @author Navid Galt
* @version A2, Assignment #2 */

class DynamicArray
{   
	/** Sets a size of 100 to the array created. */
    private int MAX_SIZE = 100;
    /** Uses a variable to keep track of the current size of the array at any given time. */
    private int CURR_SIZE = -1;
    /** Variable used to house the variables. */
    private int[] data;
    
    /** Variable used for the binary search */
    public static String a[];
    public static int i=0;
    
    //variable data holds array's new size after max capacity is filled
    public DynamicArray()
    {        
        this.data = new int[MAX_SIZE];
    }	 
    
    /** AddValue holds the algorithm that doubles the size of the array.
     * @param v the size of the array
     * @return the final size of the array */
    public void AddValue(int v)
    {
        this.CURR_SIZE ++;
        if(CURR_SIZE == MAX_SIZE-1)
        {
            int[] temp = this.data;            
            this.MAX_SIZE *= 2;
            this.data = null; //resets the data back to original to clear memory
            this.data = new int[this.MAX_SIZE]; //new size for array
            
            for(int i = 0; i < this.CURR_SIZE; i++)
            {
                this.data[i] = temp[i];
            }              
        }
        this.data[this.CURR_SIZE] = v; //passing
    }   
    
    /** Getter used to get value from array at provided index.
     * @param index number in array
     * @returns the value in array */
    // 
    public int GetValue(int index)
    {
        return this.data[index];
    }    
    
    /** Setter used to set value of array at provided index.
     * @param index place in array
     * @param value the number in that index of array
     */
    public void SetValue(int index, int value)
    {
        this.data[index] = value;
    }    
    
    /** Getter that grabs the current index of array.
     * @return the index */
    public int GetCurrentIndex()
    {
        return this.CURR_SIZE;
    }
    
    /** Getter for max size of array.
     * @return the max size of array */
    public int GetMaxSize()
    {
        return this.MAX_SIZE;
    }
}
/** * <p>Class used to store bubble sort algorithm, variables and getters/setters.</p>  
*/ 
class BubbleSortAlgo
{   
	/** Variable created for the "sorted array" and is pulled multiple times throughout the program */
    private DynamicArray SortedArray;
    
    /** Constructor of sorted array.
     * @param d variable used*/
    public BubbleSortAlgo(DynamicArray d)
    {        
        this.SortedArray = BubbleSort(d);
    }
    /** Getter that grabs bubble sorted array.
     * @return the sorted array */
    public DynamicArray GetSortedArray()
    {
        return this.SortedArray;
    }
    
    /** Algorithm for bubble sort, swaps the next value in the array putting the
     * lower value first all the way to end of array.
     * @param d_array variable used for this function
     * @return d_array array that's sorted
     * Bubble sort algorithm obtained from:
	  (Source: https://www.youtube.com/watch?v=F13_wsHDIG4)*/
  
    public DynamicArray BubbleSort(DynamicArray d_array)
    { 
        int i, j, temp = 0;
        
    	for(i=0; i < d_array.GetCurrentIndex()-1; i++);
    	{
    		for(j=0; j< d_array.GetCurrentIndex()-1-i; j++)
    		{
    			if(d_array.GetValue(j) > d_array.GetValue(j+1))
    			{
    				temp= d_array.GetValue(j);
    				d_array.SetValue(j, d_array.GetValue(j+1));
    				d_array.SetValue(j+1, temp);
    						
    			}
    		}
    	}
    	return d_array;
    }
}

/** * <p>Merge Sort algorithm, sorts by using recursion, splits array into pieces sorts them individually
 * and then returns them back to one long sorted array</p> 
* Source: (http://www.codecodex.com/wiki/Merge_sort#Pseudocode)*/
// 
class MergeSortAlgo
{
	/** Constructor for the merge sorted array.
	 * @param d variable used for this function*/
    public MergeSortAlgo(DynamicArray d)
    {
        MergeSort(d);
    }    
    
    /** Merges the array back into one.
     * @param left left side of array
     * @param right right side of array
     * @return the sorted array */
    public DynamicArray Merge(DynamicArray left, DynamicArray right)
    {
        DynamicArray result = new DynamicArray();
        while(left.GetCurrentIndex() > 0 && right.GetCurrentIndex() > 0)
        {
            if(left.GetValue(0) <= right.GetValue(0))
            {
                result.AddValue(left.GetValue(0));
                DynamicArray tmp = new DynamicArray();
                for(int i=1;i<left.GetCurrentIndex();i++)
                {
                    tmp.AddValue(left.GetValue(i));
                }
                left = tmp;                
            }
            else
            {
                result.AddValue(right.GetValue(0));
                DynamicArray tmp = new DynamicArray();
                for(int i=1;i<right.GetCurrentIndex();i++)
                {
                    tmp.AddValue(right.GetValue(i));
                }
                right = tmp;
            }                       
        }
        if(left.GetCurrentIndex() > 0)
        {
            for(int i = 0; i < left.GetCurrentIndex(); i++)
            {
                 result.AddValue(   left.GetValue(i)    );
            }
        }
        if(right.GetCurrentIndex() > 0)
        {
            for(int i = 0; i < right.GetCurrentIndex(); i++)
            {
                 result.AddValue(   right.GetValue(i)    );
            }
        }
        return result;            
    }
    
    /** Divide the array up into pieces.
     * @param m gets values from class DynamicArray
     * @return the sorted list */
    public DynamicArray MergeSort(DynamicArray m)
    {
    	
        DynamicArray left = new DynamicArray();
        DynamicArray right = new DynamicArray();
        /** Variable initializes value for middle point in array */
        int middle = 0;
        
        if(m.GetCurrentIndex() <= 1)
        {
            return m;
        }
        else
        {
            middle = m.GetCurrentIndex() / 2;
            /** start to middle, add to left*/
            for(int i = 0; i < middle; i++)
            {
                left.AddValue(m.GetValue(i));
            }
            /** middle to end, add to right*/
            for(int i = middle; i < m.GetCurrentIndex()-1; i++)
            {
                right.AddValue(m.GetValue(i));
            }
            /**at this point, left contains all values from m between start and middle index*/
            left = MergeSort(left);
            /** at this point, right contains all values from m between middle and last index*/
            right = MergeSort(right);
            
            /**Merge will only get called after all left/right have been returned by checking if
            the size of m is <= 1*/
            DynamicArray result = Merge(left, right);
            
            return result;
            
            
        }        
    }    
}

/** * <p>Class that houses all the search algorithms</p> */
class Algorithms
{
	/** variable that is used to hold dynamic array */
    private DynamicArray d_array;
    
    /**Constructor
     * @param da the dynamic array*/
    public Algorithms(DynamicArray da)
    {
        this.d_array = da;
    }
    
    /** This method houses a binary search (logn algorithm) and returns the runtime.
     * @param array sorted array
     * @param searchItem variable to measure value
     * @return runtime of this algorithm
     * (Source:https://www.daniweb.com/programming/software-development/threads/53172/binary-search-on-a-text-file)*/
	public double opLogN(DynamicArray array, int searchItem)
	{       
		/** Variable initialized for beginning of array */
        int start = 0;
        /** Variable initialized for end of array */
        int end = array.GetCurrentIndex() - 1;
        /** Variable initialized for middle of array */
        int middle = 0;
     
        DynamicArray d_temp = array;        		
        /**Start Timer*/     
        long startBinary = System.nanoTime();         
        /**loop until found or end of list (Binary Search)*/
        while(start <= end)
        {
        	middle = (start+end) / 2;
        	if(d_temp.GetValue(middle)==(searchItem))
        	{
        		break;
        	}
        	if(searchItem < d_temp.GetValue(middle))
        	{
        		end = middle-1;
        	}
        	else
        	{
        		start = middle + 1;
        	}
        } 
 
        long endBinary = System.nanoTime();
        double runtime =  endBinary - startBinary;
        /**Timer Stopped*/
        return runtime;                
	}
	
	  /** Linear search operation using n algorithm speed.
     * @param array the dynamic array
     * @param search_query used to pass value to search for
     * @return runtime of operation 
     * (Source:http://www.programmingsimplified.com/java/source-code/java-program-for-linear-search)*/
	
	public double opN(DynamicArray array, int search_query)
	{
		/**Starts timer*/    
        long startTime = System.nanoTime();    
        for(int i = 0; i < array.GetCurrentIndex(); i++)
        {
             if( array.GetValue(i) == search_query )
             {
                 break;
             }
        }        
        long endTime = System.nanoTime();
        double runtime =  endTime - startTime;
        /**Timer Stopped*/
        return runtime;        
	}
	
	  /** Calls MergeSort algorithm we created and times it.
     * @return the runtime of Mergesort */
	public double opNlogN()
	{
        DynamicArray d_temp = this.d_array;          		
        /**Starts timer*/     
        long startBinary = System.nanoTime();                 
        MergeSortAlgo msa = new MergeSortAlgo(d_temp);         
        long endBinary = System.nanoTime();
        double runtime =  endBinary - startBinary;
        /**Timer Stopped*/      
        return runtime;    
    }
	
	  /** Calls BubbleSortAlgo constructor emulating our bubble sort algorithm.
     * @return the runtime of the BubbleSort */
	public double opNSquared()
	{
        DynamicArray d_temp = this.d_array;          		
        /**Starts timer*/     
        long startBinary = System.nanoTime();                 
        BubbleSortAlgo bsa = new BubbleSortAlgo(d_temp);         
        long endBinary = System.nanoTime();
        double runtime =  endBinary - startBinary;
        /**Timer Stopped*/       
        return runtime;           
	}    
}