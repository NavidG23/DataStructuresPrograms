package assignment4;

//Example of using unit tests for programming assignment. This is
//partially how your code will be graded. To run them on the command
//line, make sure that the junit .jar file is in the current or code directory.

//From the directory containing your code, A3Tests.java, and junit.jar:
//$> javac -cp .;junit.jar *.java
//$> java -cp .;junit.jar A4Tests

//Note: If your jar file isn't named junit.jar, make sure to change
//the command above appropriately

//Note: Linux and Mac users should separate library directories with : not ;

import org.junit.*; //junit
import static org.junit.Assert.*; //assert functions
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class A4Tests
{
	/**
	 * Checks numbers entered come out sorted.
	 */
	@Test(timeout=2000)
	public void A4_sort()
	{
		//generate 100 random numbers
		//(always generates the same random numbers)
		Random r = new Random(0);
		int[] studentNums = new int[100];
		int[] correctNums = new int[100];
		for(int i = 0; i < 100; i++) {
			studentNums[i] = r.nextInt();
			correctNums[i] = studentNums[i];
		}
		
		//make sure numbers come out sorted
		studentNums = A4.mergeSort(studentNums);
		Arrays.sort(correctNums);
		
		assertArrayEquals(correctNums, studentNums);
	}
	
	/**
	 * Checks reading and writing to a file
	 */
	@Test(timeout=2000)
	public void A4ReadAndWrite()
	{
		//generate some random numbers
		Random r = new Random();
		int[] nums = new int[(int)(r.nextDouble()*1000)+1];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt();
		}
		
		A4.writeNumbersToFile("temp.bat", nums);
		int[] fromFile = A4.getNumbersFromFile("temp.bat");
		(new File("temp.bat")).delete();
		
		assertArrayEquals(nums, fromFile);
	}
	
	public static void main(String args[])
	{
		org.junit.runner.JUnitCore.main("A4Tests");
	}
}

