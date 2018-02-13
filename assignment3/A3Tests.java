package assignment3;

//Example of using unit tests for programming assignment. This is
//partially how your code will be graded. To run them on the command
//line, make sure that the junit .jar file is in the current or code directory.

//From the directory containing your code, A3Tests.java, and junit.jar:
//$> javac -cp .;junit.jar *.java
//$> java -cp .;junit.jar A3Tests

//Note: If your jar file isn't named junit.jar, make sure to change
//the command above appropriately

//Note: Linux and Mac users should separate library directories with : not ;

import org.junit.*; //junit
import static org.junit.Assert.*; //assert functions
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class A3Tests
{
	public static int CAPACITY = 100;
	
	private static class Box
	{
		public static int uniqueId = 0;
		
		public int id;
		
		public Box()
		{
			this.id = uniqueId;
			uniqueId++;
		}
		
		public String toString()
		{
			return ""+id;
		}
	};
	
	private Structure<Box> a;
	
	@Before
	public void MakeStructure()
	{
		a = new Structure<>();
		Box.uniqueId = 0;
	}
	
	/**
	 * Check the Structure class - check size()
	 */
	@Test(timeout=2000)
	public void P1Structure01()
	{
		assertEquals(0, a.size());
		for(int i = 1; i < CAPACITY+1; i++)
		{
			a.add(new Box());
			assertEquals(i, a.size());
		}
		a.peek();
		for(int i = CAPACITY; i > 0; i--)
		{
			assertEquals(i, a.size());
			a.remove();
		}
		
		assertEquals(0, a.size());
		
		for(int i = 1; i < CAPACITY+1; i++)
		{
			a.offer(new Box());
			assertEquals(i, a.size());
		}
		a.peek();
		for(int i = CAPACITY; i > 0; i--)
		{
			assertEquals(i, a.size());
			a.poll();
		}
		assertEquals(0, a.size());
	}
	
	/**
	 * Check the Structure class - check add/remove adds & removes same element
	 */
	@Test(timeout=2000)
	public void P1Structure02()
	{
		Box pin = new Box();
		a.add(pin);
		Box pin2 = a.remove();
		assertEquals(pin.id, pin2.id);
	}
	
	/**
	 * Check the Structure class - check offer/poll adds & removes same element
	 */
	@Test(timeout=2000)
	public void P1Structure22()
	{
		Box pin = new Box();
		a.offer(pin);
		Box pin2 = a.poll();
		assertEquals(pin.id, pin2.id);
	}
	
	/**
	 * Check the Structure class - check add/remove adds & removes as a queue
	 */
	@Test(timeout=2000)
	public void P1Structure03()
	{
		Box pinAdd1 = new Box();
		a.add(pinAdd1);
		
		Box pinAdd2 = new Box();
		a.add(pinAdd2);
		
		Box pinRemove1 = a.remove();
		assertEquals(pinAdd1.id, pinRemove1.id);
		
		Box pinAdd3 = new Box();
		a.add(pinAdd3);
		
		Box pinRemove2 = a.remove();
		assertEquals(pinAdd2.id, pinRemove2.id);
		
		Box pinRemove3 = a.remove();
		assertEquals(pinAdd3.id, pinRemove3.id);
	}
	
	/**
	 * Check the Structure class - check offer/poll adds & removes as a queue
	 */
	@Test(timeout=2000)
	public void P1Structure23()
	{
		Box pinAdd1 = new Box();
		a.offer(pinAdd1);
		
		Box pinAdd2 = new Box();
		a.offer(pinAdd2);
		
		Box pinRemove1 = a.poll();
		assertEquals(pinAdd1.id, pinRemove1.id);
		
		Box pinAdd3 = new Box();
		a.offer(pinAdd3);
		
		Box pinRemove2 = a.poll();
		assertEquals(pinAdd2.id, pinRemove2.id);
		
		Box pinRemove3 = a.poll();
		assertEquals(pinAdd3.id, pinRemove3.id);
	}
	
	/**
	 * Check the Structure class - check NoSuchElementException for remove on empty Structure
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void P1Structure04()
	{
		a.remove();
		fail("Structure remove() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the Structure class - check NoSuchElementException for remove after add & remove twice
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void P1Structure05()
	{
		a.add(new Box());
		a.remove();
		a.remove();
		fail("Structure remove() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the Structure class - check null return for poll on empty Structure
	 */
	@Test(timeout=2000)
	public void P1Structure06()
	{
		assertTrue(a.poll() == null);
	}
	
	/**
	 * Check the Structure class - check return for poll after add & poll twice
	 */
	@Test(timeout=2000)
	public void P1Structure07()
	{
		a.add(new Box());
		assertTrue(a.poll() != null);
		assertTrue(a.poll() == null);
	}
	
	/**
	 * Check the Structure class - check NullPointerException for adding null
	 */
	@Test(expected=NullPointerException.class,timeout=2000)
	public void P1Structure09()
	{
		a.add(null);
		fail("Structure add() should throw NullPointerException when adding null items");
	}
	
	/**
	 * Check the Structure class - check NullPointerException for offering null
	 */
	@Test(expected=NullPointerException.class,timeout=2000)
	public void P1Structure11()
	{
		a.offer(null);
		fail("Structure offer() should throw NullPointerException when adding null items");
	}
	
	/**
	 * Check the Structure class - check element shows head
	 */
	@Test(timeout=2000)
	public void P1Structure12()
	{
		Box pinAdd1 = new Box();
		a.add(pinAdd1);
		
		Box pinAdd2 = new Box();
		a.add(pinAdd2);
		
		Box pinRemove1 = a.element();
		assertEquals(pinAdd1.id, pinRemove1.id);
		pinRemove1 = a.element();
		assertEquals(pinAdd1.id, pinRemove1.id);
		a.remove();
		
		Box pinAdd3 = new Box();
		a.add(pinAdd3);
		
		Box pinRemove2 = a.element();
		assertEquals(pinAdd2.id, pinRemove2.id);
		pinRemove2 = a.element();
		assertEquals(pinAdd2.id, pinRemove2.id);
		a.remove();
		
		Box pinRemove3 = a.element();
		assertEquals(pinAdd3.id, pinRemove3.id);
		pinRemove3 = a.element();
		assertEquals(pinAdd3.id, pinRemove3.id);
		a.remove();
	}
	
	/**
	 * Check the Structure class - check NoSuchElementException for element on empty
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void P1Structure13()
	{
		a.element();
		fail("Structure element() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the Structure class - check NoSuchElementException for element on empty after add and remove
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void P1Structure14()
	{
		a.add(new Box());
		a.remove();
		a.element();
		fail("Structure element() should throw NoSuchElementException when nothing remaining.");
	}
	
	/**
	 * Check the Structure class - check peek shows head
	 */
	@Test(timeout=2000)
	public void P1Structure15()
	{
		Box pinAdd1 = new Box();
		a.add(pinAdd1);
		
		Box pinAdd2 = new Box();
		a.add(pinAdd2);
		
		Box pinRemove1 = a.peek();
		assertEquals(pinAdd1.id, pinRemove1.id);
		pinRemove1 = a.peek();
		assertEquals(pinAdd1.id, pinRemove1.id);
		a.remove();
		
		Box pinAdd3 = new Box();
		a.add(pinAdd3);
		
		Box pinRemove2 = a.peek();
		assertEquals(pinAdd2.id, pinRemove2.id);
		pinRemove2 = a.peek();
		assertEquals(pinAdd2.id, pinRemove2.id);
		a.remove();
		
		Box pinRemove3 = a.peek();
		assertEquals(pinAdd3.id, pinRemove3.id);
		pinRemove3 = a.peek();
		assertEquals(pinAdd3.id, pinRemove3.id);
		a.remove();
	}
	
	/**
	 * Check the Structure class - check return value for peek on empty
	 */
	@Test(timeout=2000)
	public void P1Structure16()
	{
		assertTrue(a.peek() == null);
	}
	
	/**
	 * Check the Structure class - check return for peek on empty after add and remove
	 */
	@Test(timeout=2000)
	public void P1Structure17()
	{
		a.add(new Box());
		a.remove();
		assertTrue(a.peek() == null);
	}
	
	/**
	 * Check the Structure class - check toString() method
	 */
	@Test(timeout=2000)
	public void P1Structure18()
	{
		Box pin = new Box();
		a.add(pin);
		assertEquals(pin.toString(), a.toString().replaceAll("\\s",""));
		
		Box pin2 = new Box();
		a.add(pin2);
		assertEquals(pin.toString()+pin2.toString(), a.toString().replaceAll("\\s",""));
		
		a.remove();
		assertEquals(pin2.toString(), a.toString().replaceAll("\\s",""));
	}
	
	/**
	 * Check the Structure class - check clear method
	 */
	@Test(expected=NoSuchElementException.class,timeout=2000)
	public void P1Structure19()
	{
		assertEquals(0, a.size());
		a.add(new Box());
		a.add(new Box());
		assertEquals(2, a.size());
		a.clear();
		assertEquals(0, a.size()); //make sure size is 0
		a.remove(); //make sure NoSuchElementException is thrown
	}
	
	/**
	 * Check the Structure class - check isEmpty method
	 */
	@Test(timeout=2000)
	public void P1Structure20()
	{
		assertTrue(a.isEmpty());
		a.add(new Box());
		a.remove();
		assertTrue(a.isEmpty());
	}
	
	/**
	 * Check the Structure class - check iterator()
	 */
	@Test(timeout=2000)
	public void P1Structure21()
	{
		for(int i = 0; i < CAPACITY; i++)
			a.add(new Box());
			
		Iterator<Box> it = a.iterator();
		for(int i = 0; i < CAPACITY; i++)
		{
			assertTrue(it.hasNext());
			assertEquals(it.next().id, i);
		}
		
		assertFalse(it.hasNext());
	}
	
	public static void main(String args[])
	{
		org.junit.runner.JUnitCore.main("A3Tests");
	}
}

