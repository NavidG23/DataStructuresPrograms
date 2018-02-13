package assignment7;

import java.util.*;
import java.io.*;
/**
 * Class Huffman houses an inner node class and uses a hashmap to keep track of instances of a letter and a priority queue to
 * order elements sequentially. This class also encodes and decodes files of texts and binary using these data structures.
 * 
 * @author Navid Galt
 * INFS 519
 * Spring 2017
 */
class Huffman implements Serializable
{
	/**root of the structure*/
	private Node root = null;
	/**instance of the HashMap*/
	private HashMap<Character, String> encodings = new HashMap<>();
	/**contents of file*/
	private transient String fileContents = null;
	
	/**
	 * Inner Node class used by Huffman as a linked structure/tree
	 */
	private static class Node implements Serializable, Comparable<Node>
	{
		/**Variable that tracks number of occurrences*/
		public int count;
		/**Variable used to represent letter within node*/
		public Character character = null;
		/**Variable used to keep track of left and right child*/
		public Node[] children = new Node[2];
		
		public Node(int count)
		{
			this.count = count;
		}
		
		public Node(int count, Character character)
		{
			this.count = count;
			this.character = character;
		}
		
		public int compareTo(Node otherNode)
		{
			return this.count - otherNode.count;
		}
	}
	/**
	 * Constructor of Huffman, houses HashMap to count characters and creates a priority queue that stores these numbers sequentially.
	 * @param fileContents contents of the file
	 */
	public Huffman(String fileContents)
	{
		this.fileContents = fileContents;
		
		/**counts all characters*/
		HashMap<Character, Node> counts = new HashMap<>();
		
		/**Variable used to track size of file*/
        int size = this.fileContents.length();
        
        for(int i = 0; i < size; i++)
        {
        	if(counts.containsKey(this.fileContents.charAt(i)))
        	{
        		Node temp = counts.get(this.fileContents.charAt(i));
        		temp.count++;
        	}
        	else
        	{
        		counts.put(this.fileContents.charAt(i), new Node(1, this.fileContents.charAt(i)));
        	}
        }
        
		/**creating a queue with all values in HashMap(Nodes) housed in each node of queue*/
		PriorityQueue<Node> queue = new PriorityQueue<Node>(counts.values());

		while(queue.size() > 1)
		{
			/**Variable for first node off priority queue*/
			Node temp1 = queue.poll();
			/**Variable for second node off priority queue*/
			Node temp2 = queue.poll();
			/**Variable for parent node*/
			Node parent = new Node(temp1.count + temp2.count);
			parent.children[0] = temp1;
			parent.children[1] = temp2;
			queue.add(parent);
		}
		this.root = queue.remove();
		computeEncodings(this.root, ""); 
	}
   /**
    * Method used to compute encodings of HashMap
    * @param currentLoc
    * @param encoding
    */
	private void computeEncodings(Node currentLoc, String encoding)
	{
		if(currentLoc.character != null)
		{
			this.encodings.put(currentLoc.character, encoding);
		}
		else
		{
			computeEncodings(currentLoc.children[0], encoding+"0");
			computeEncodings(currentLoc.children[1], encoding+"1");
		}
	}
	/**
	 * Method used to encode file contents
	 * @return output.toString() encoded text in String representation
	 */
	public String encode()
	{
		/**Variable used to house encoded text*/
		StringBuffer output = new StringBuffer();
		
		for (char ch : this.fileContents.toCharArray())
		{
			output.append(this.encodings.get(ch));
		}
		return output.toString();
	}
	/**
	 * Method used to decode file
	 * @param input encoded text
	 * @return output.toString() decoded text
	 */
	public String decode(String input)
	{
		/**Append output of decode to a string*/
		StringBuffer output = new StringBuffer();
		/**Variable for the root node*/
		Node base = this.root;
		/**Variable for the character array*/
		char[] arrayCharack = input.toCharArray();
		
		for(int i = 0; i < arrayCharack.length; i++)
		{
			/**Temporary node to hold value of node*/
			int temp = Character.getNumericValue(arrayCharack[i]);
			if(temp == 0)
			{
				if(base.children[0] != null)
				{
					base = base.children[0];
				}
				else
				{
					output.append(base.character);
					base = this.root.children[0];
				}
			}
			else if(temp == 1)
			{
				if(base.children[1] != null)
				{
					base = base.children[1];
				}
				else
				{
					output.append(base.character);
					base = this.root.children[1];
				}
			}	
		}
		return output.toString();
	}
}
