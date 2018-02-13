package assignment4;

/**
 * @author Navid Galt
 * INFS 519
 * Spring 2015
 */
 
import java.io.*;
import java.util.Scanner;

/**
 * Class A4 houses a program that is used to read in a selected file of integers, it 
 * will then put these integers into an array, sort these integers using merge sort 
 * and then output them to a file of choice, entered by the user.
 */
class A4
{
	/**
	 * Main class for A4, reads in file of integers, sorts integers in numerical/sequential order, then prints those out 
	 * to a filename of choice. If file contains non-integer values the program will inform the user and gracefully exit.
	 */
	public static void main(String[] args)
	{
		int[] data = getNumbersFromFile(args[0]);
		if(data == null)
		{
			System.out.println("Your file contains non-integers, please try again and check to make sure you are entering a file with integers only ");
			System.exit(0);
		}
		System.out.println("Before sorting: ");
		printArray(data);
		
		data = mergeSort(data);
		
		System.out.println("\nAfter sorting: ");
		printArray(data);
		
		writeNumbersToFile(args[1], data);
		System.out.println("\nData printed to file ");

	}
	/**
	 * Method printArray is used to loop through and print the array of integers in the main
	 * @param data variable used to hold array of integers
	 */
	public static void printArray (int [] data)
	{
		for(int i = 0; i<data.length; i++)
		{
			System.out.print(data[i] + "  ");
		}
	}

	/**
	 * Method GetNumberOfLinesInFile uses a loop to count amount of lines that are in the file.
	 * @param filename contents of file 
	 */
	public static int GetNumberOfLinesInFile(String filename)
	{
		/** Variable lines represents number of lines in file*/
		int lines = 0;
		/** Variable reader is used to represent file values*/
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader(new FileReader(filename));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			while (reader.readLine() != null) lines++;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * Method getNumbersFromFile uses method GetNumberOfLinesInFile to create an array of appropriate size and reads
	 * all the integers from the file into this array.
	 * @param filename contents of file*/ 
	public static int[] getNumbersFromFile(String filename)
	{
		/** Variable num_lines represents method GetNumberOfLinesInFile method*/
		int num_lines = GetNumberOfLinesInFile(filename);
		/** Variable array used to house array*/
		int[] array = new int[num_lines];
		try 
		{
			File file = new File(filename);
		
			Scanner scan = new Scanner(file);
			for(int i = 0 ; i < num_lines; i++)
			{
				if(scan.hasNextInt())
				{
					array[i] = scan.nextInt();
				}
				else
				{
					System.out.println("Your file had a non-integer character at line: " + (i+1));
					return null;
				}
			} return array;
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return null;
		} 
	} 

	/**
	 * Method writeNumbersToFile uses BufferedWriter to write out sorted array to file of choice, designated by the user.
	 *  @param data houses array
	 *  @param filename variable used for the name of the file
	 */
	public static void writeNumbersToFile(String filename, int[] data)
	{
		try
		{
			/** Variable arrayoutput used to represent BufferedWriter*/
			BufferedWriter arrayoutput = null;
			arrayoutput = new BufferedWriter(new FileWriter(filename));
			for(int i = 0; i < data.length; i++)
			{
				arrayoutput.write(Integer.toString(data[i]));
				arrayoutput.newLine();
			}
			arrayoutput.flush();
			arrayoutput.close();
		} catch(IOException ie) {
            ie.printStackTrace();
		}
	}

	/**
	 *  Method mergeSort uses recursion to sort the array/file.
	 *  @param numbers variable that houses the array
	 */
	public static int[] mergeSort(int[] numbers)
	{
		if (numbers.length <= 1)
		{
			return numbers;
		}
		/**Middle point of array*/
		int middle = numbers.length/2;
		/**Left side of array*/
		int [] left = new int [middle];
		/**Right side of array, uses if/else statement to determine odd/even split of array*/
		int [] right;
		if(numbers.length %2 == 0)
		{
			right = new int [middle];
		}
		else
		{
			right = new int [middle + 1];
		}
		
		int [] result = new int [numbers.length];
		
		for(int i = 0; i < middle; i++)
		{
			left [i] = numbers[i];
		}
		
		int x = 0;
		for(int j = middle; j < numbers.length; j++)
		{
			if( x < right.length)
			{
				right[x] = numbers[j];
				x++;
			}
			else
			{
				throw new IndexOutOfBoundsException("Index" + numbers + "is out of bounds!");
			}
		}
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		result = merge(left, right);
		
		return result;
	}
	/**
	 * Method merge appropriately merges sorted split array back into one sorted array
	 * @param left left side of array
	 * @param right right side of array
	 * @return result1 
	 */
	public static int [] merge(int[] left, int[] right)
	{
		/** Variable lengthResult contains left and right side of array*/
		int lengthResult = left.length + right.length;
		/** Variable result1 contains merged elements of left and right array*/
		int [] result1 = new int [lengthResult];
		/** Variable for left index */
		int leftArray = 0;
		/** Variable for right index*/
		int rightArray = 0;
		/** Variable for total index*/
		int arrayResult = 0;
		
		while(leftArray < left.length || rightArray < right.length)
		{
			if(leftArray < left.length && rightArray < right.length)
			{
				if(left[leftArray] <= right [rightArray])
				{
					result1[arrayResult] = left[leftArray];
					leftArray++;
					arrayResult++;
				}
				else
				{
					result1[arrayResult] = right[rightArray];
					rightArray++;
					arrayResult++;
				}
			}
			
			else if(leftArray < left.length)
			{
				result1[arrayResult] = left[leftArray];
				leftArray++;
				arrayResult++;
			}
			
			else if(rightArray < right.length)
			{
				result1[arrayResult] = right[rightArray];
				rightArray++;
				arrayResult++;
			}
		}
		return result1;
	}
}