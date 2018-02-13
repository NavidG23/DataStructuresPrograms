package assignment5;

//Example of using unit tests for programming assignment 7.  This is
	// partially how your code will be graded.  To run them on the command
	// line, make sure that the junit-4.11.jar is in the current or code
	// directory.

	// From the directory containing PA2Tests.java and junit-4.11.jar:
	// $> javac -cp .;.\codeDirectory;junit-4.11.jar *.java
	// $> java -cp .;.\codeDirectory;junit-4.11.jar A5Tests
	// note: Linux & Mac users should separate library directories with : not ; and use / not \

	import org.junit.*; //junit
	import static org.junit.Assert.*; //assert functions
	import java.util.*;

	public class A5Tests
	{
		private Integer[] intArray;
		private String[] strArray;
		
		/**
		 * Reset the integer list
		 */
		@Before
		public void makeIntegerList()
		{
			intArray = new Integer[10];
			for(int i = 0; i < 10; i++)
				intArray[i] = Integer.valueOf(i);
		}
		
		/**
		 * Reset the string list
		 */
		@Before
		public void makeStringList()
		{
			strArray = new String[10];
			for(int i = 0; i < 10; i++)
				strArray[i] = ""+i;
		}
		
		/**
		 * Check pre-order (k-ary tree, k=3)
		 */
		@Test(timeout=2000)
		public void PreWalk1()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 3);
			String result = tree.toStringPreOrder().replaceAll("\\s","");
			assertEquals("0145627893", result);
		}
		
		/**
		 * Check pre-order (k-ary tree, k=3)
		 */
		@Test(timeout=2000)
		public void PreWalk2()
		{
			SimpleKTree<String> tree = new SimpleKTree<>(strArray, 3);
			String result = tree.toStringPreOrder().replaceAll("\\s","");
			assertEquals("0145627893", result);
		}
		
		/**
		 * Check pre-order (binary tree)
		 */
		@Test(timeout=2000)
		public void PreWalk3()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 2);
			String result = tree.toStringPreOrder().replaceAll("\\s","");
			assertEquals("0137849256", result);
		}
		
		/**
		 * Check pre-order (k-ary tree, k=10)
		 */
		@Test(timeout=2000)
		public void PreWalk4()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 10);
			String result = tree.toStringPreOrder().replaceAll("\\s","");
			assertEquals("0123456789", result);
		}
		
		/**
		 * Check post-order (k-ary tree, k=3)
		 */
		@Test(timeout=2000)
		public void PostWalk1()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 3);
			String result = tree.toStringPostOrder().replaceAll("\\s","");
			assertEquals("4561789230", result);
		}
		
		/**
		 * Check post-order (k-ary tree, k=3)
		 */
		@Test(timeout=2000)
		public void PostWalk2()
		{
			SimpleKTree<String> tree = new SimpleKTree<>(strArray, 3);
			String result = tree.toStringPostOrder().replaceAll("\\s","");
			assertEquals("4561789230", result);
		}
		
		/**
		 * Check post-order (binary tree)
		 */
		@Test(timeout=2000)
		public void PostWalk3()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 2);
			String result = tree.toStringPostOrder().replaceAll("\\s","");
			assertEquals("7839415620", result);
		}
		
		/**
		 * Check post-order (binary tree)
		 */
		@Test(timeout=2000)
		public void PostWalk4()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 10);
			String result = tree.toStringPostOrder().replaceAll("\\s","");
			assertEquals("1234567890", result);
		}
		
		/**
		 * Check height (k-ary tree, k=3)
		 */
		@Test(timeout=2000)
		public void height1()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 3);
			assertEquals(2, tree.height());
		}
		
		/**
		 * Check height (binary tree)
		 */
		@Test(timeout=2000)
		public void height2()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 2);
			assertEquals(3, tree.height());
		}
		
		/**
		 * Check height (k-ary tree, k = 10)
		 */
		@Test(timeout=2000)
		public void height3()
		{
			SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 10);
			assertEquals(1, tree.height());
		}
		
		/**
		 * Check height (size 1 tree)
		 */
		@Test(timeout=2000)
		public void height4()
		{
			Integer[] oneInt = new Integer[1];
			oneInt[0] = new Integer(1);
			for(int k = 2; k < 1000; k++)
			{
				SimpleKTree<Integer> tree = new SimpleKTree<>(oneInt, k);
				assertEquals(0, tree.height());
			}
		}
		
		/**
		 * Check invalid k exception
		 */
		@Test(timeout=2000)
		public void constructor1()
		{
			try
			{
				SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 1);
				assertTrue(false);
			}
			catch(RuntimeException e)
			{
				
			}
		}
		
		/**
		 * Check invalid k exception
		 */
		@Test(timeout=2000)
		public void constructor2()
		{
			try
			{
				SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 0);
				assertTrue(false);
			}
			catch(RuntimeException e)
			{
				
			}
		}
		
		/**
		 * Check invalid k exception
		 */
		@Test(timeout=2000)
		public void constructor3()
		{
			try
			{
				SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, -10);
				assertTrue(false);
			}
			catch(RuntimeException e)
			{
				
			}
		}
		
		/**
		 * Check invalid k exception
		 */
		@Test(timeout=2000)
		public void constructor4()
		{
			try
			{
				SimpleKTree<Integer> tree = new SimpleKTree<>(intArray, 10000);
			}
			catch(RuntimeException e)
			{
				assertTrue(false);
			}
		}

		public static void main(String args[])
		{
			org.junit.runner.JUnitCore.main("A5Tests");
		}
	}


