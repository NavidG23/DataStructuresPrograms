package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** * <p>A2 class houses the reading file function and our main method.</p> */
public class A2 
{	
    /** Reads in file from argument in command prompt.
     * @param filename read the name of the file entered 
     * @return the array
     * @throws FileNotFoundException
			   Thrown when it does not find the specified file entered
     */
    public static DynamicArray ReadFromFileToDynamicArray(String filename)
    {
        Scanner sc;
        DynamicArray d_array = new DynamicArray();
		try {
			sc = new Scanner(new File(filename));
			int count=0;
			
	        while (sc.hasNextLine())
	        {
                d_array.AddValue(Integer.parseInt(""+sc.nextLine())); //parse.Int = helper functions
                count++;
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        System.out.println("Done with file reading function.");
        return d_array; 
    }
    
    /** Our main method calls all algorithms we created and prints runtime out to console.
     * @param args filename entered in command prompt */
	public static void main(String[] args)
	{
		
		DynamicArray d_array2 = ReadFromFileToDynamicArray(args[0]);
        
        Algorithms algo = new Algorithms(d_array2);
        
        System.out.println("The algorithm results from your file -->");
        /**Sorts*/
        /**does n^2 algorithm*/
        double n2_time = algo.opNSquared();
        /**do n*log n algorithm*/
        double nln_time = algo.opNlogN();
        
        BubbleSortAlgo bs = new BubbleSortAlgo(d_array2);
        DynamicArray sorted = bs.GetSortedArray();
        
        /**Searches*/
        /**Sorts array then does n algorithm*/
        double n_time = algo.opN(sorted, 25);        
        /**Sorts array then does log n algorithm*/
        double ln_time = algo.opLogN(sorted, 33);
   
        System.out.println( "Time required for O(n^2) -> " + n2_time + " ns" );
        System.out.println( "Time required for O(n*log(n)) -> " + nln_time + " ns" );
        System.out.println( "Time required for O(n) -> " + n_time + " ns" );
        System.out.println( "Time required for O(log(n)) -> " + ln_time + " ns" );
		
	}
}
	

