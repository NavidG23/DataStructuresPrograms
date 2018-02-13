package assignment5;

/**
 * INFS 519
 * Spring 2017
 * <p> Class SimpleKTree houses a linked structure that is used to create a k-ary tree. K-ary meaning the amount of children nodes
 * is and will be defined by the user. Two functions are then ran on this tree, Pre-order and Post-order both of which will output
 * the values that are inputted in these trees into a certain format <\p>
 * @author Navid Galt
 */

public class SimpleKTree<E>
{
	/**Variable to determine amount of values*/
	int nodesInTree = 0;
	/**Variable for root of tree*/
	Node<E> root = null;
	/**Global variable for branching*/
	int k = 0;
	/**
	 * <p>Node class to create Node, node child in tree and branching factor k<\p>
	 */
	class Node<E>
	{
		/**Variable to hold node values*/
		public E value;
		/**Variable to hold parent node*/
		public E parent;
		/**Variable for array of children*/
		public Node<E> [] children;
		/**Variable for branching*/
		public int k = 0;
		
		Node()
		{
			this.value=null;
			this.children = null;
			this.parent = null;
		}
		Node(E val, int k)
		{
			this.value = val;
			this.k = k;
			this.children = new Node[k];
		} 
		void setChild(Node<E> child, int index)
		{
			this.children[index] = child;
		}
	}
	
	/**
	 * Constructor for SimpleKTree, creates Trees and children nodes
	 * @throws InvalidKException if children are less than two
	 * @param arrayTree variable used to represent the array
	 * @param k branching factor, how many children
	 */
	public SimpleKTree(E[] arrayTree, int k)
	{
		if(k<2)
		{
			throw new InvalidKException();
		}
		
		this.k = k;
		Node<E>[] array = new Node[arrayTree.length];
		
		for(int i = 0; i < arrayTree.length; i++)
		{
			Node<E> node = new Node<E>(arrayTree[i], k);
			array[i] = node;
			
		}
		/**Setting root to index 0*/
		Node<E> root = array[0];
		/**Setting nodes in tree to the length of array*/
		nodesInTree = array.length;
		
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 1; j <= k; j++)
			{
				if(i*k+j < array.length)
				{
					System.out.println("Parent Value:"+array[i].value+"Child Value:"+array[i*k+j].value);
					array[i].setChild(array[i*k+j], j-1);
				}
			}
		}	
		
		this.root = root;
	}
	
	/**
	 * Method height is used to calculate height/levels of tree
	 * @return k height of k-ary tree
	 * @return -1 if no values in tree 
	 */
	public int height()
	{
		if(root == null)
		{
			return -1;
		}
		else
		{
			return (int) Math.floor(Math.log(nodesInTree)/Math.log(k));		
		}
		
	}
	
	/**
	 * Method to sort numbers in list in Preorder
	 * @return null if no values in tree
	 * @return toString string representation of integers 
	 */
	public String toStringPreOrder()
	{
		/**Variable to hold string of node values*/
		String toString = "";
		if(root == null)
		{
			return null;
		}
		toString = toString + root.value + " ";
		
		for(int i = 0; i < k; i++)
		{
			String result = toStringPreOrderSubRoot(root.children[i]);
			if(result != null)
			{
				toString = toString + result + " ";
			}
			
		}
		return toString; 
	}
	/**
	 * Method to sort numbers in list in Preorder
	 * @param root the root of the tree
	 * @return null if no values in tree
	 * @return toString string representation of integers
	 */
	public String toStringPreOrderSubRoot(Node<E> root)
	{
		/**Variable to hold string of node values*/
		String toString = "";
		if(root == null)
		{
			return null;
		}
		toString = toString + root.value + " ";
	
		for(int i = 0; i < k; i++)
		{
			String result = toStringPreOrderSubRoot(root.children[i]);
			if(result != null)
			{
				toString = toString + result + " ";
			}
		}
		return toString; 
	}
	
	/**
	 * Method to sort numbers in list in Post-order
	 * @return null if no values in tree
	 * @return toString string representation of integers
	 */
	public String toStringPostOrder()
	{
		/**Variable to hold string of node values*/
		String toString = "";
		if(root == null)
		{
			return null;
		}
		
		for(int i = 0; i < k; i++)
		{
			String result = toStringPostOrderSubRoot(root.children[i]);
			if(result != null)
			{
				toString = toString + result + " ";
			}
		}
		toString = toString + " " + root.value;
		
		return toString; 
	}
	/**
	 * Method to sort numbers in list in Post-order
	 * @return null if no values in tree
	 * @return toString string representation of integers
	 */
	public String toStringPostOrderSubRoot(Node<E> root)
	{
		/**Variable to hold string of node values*/
		String toString = "";
		if(root == null)
		{
			return null;
		}
		
		for(int i = 0; i < k; i++)
		{
			String result = toStringPostOrderSubRoot(root.children[i]);
			if(result != null)
			{
				toString = toString + result + " ";
			}
		}
		toString = toString + " " + root.value;
		
		return toString; 
	}
	/**
	 * <p> Class for InvalidKException, used to throw an Exception if child nodes are less than 2 <\p>
	 *  @return toString string representation of integers
	 */
	public static class InvalidKException extends RuntimeException
	{
	
	}

}
