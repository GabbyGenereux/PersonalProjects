/*
 * Gabrielle Genereux - 100963245
 * Assignment 4, BSTDictionary class
 */
public class BSTDictionary<E, K extends Sortable> implements Dictionary<E, K> 
{
	private BSTNode<E, K> root;
	
	public BSTDictionary(BSTNode<E, K> n)
	{
		root = n;
	}
	public BSTDictionary()
	{
		root = null;
	}
	
	/*
	 * searchHelper: checks a node against the provided key to see if the two match, and resursively does this until the node is found
	 * param - key, node
	 * returns - element at given key if found, else null
	 */
	
	public E searchHelper(K key, BSTNode<E, K> node)
	{
		E left = null;
		E right = null;
		
		if(node.getKey().compareTo(key) <= -1) //root is less than key
		{
			if(node.getRight()!=null)
			{
				return searchHelper(key, node.getRight());

			}
			else
			{
				return null;
			}
		}
		else if(node.getKey().compareTo(key) >= 1)
		{
			if(node.getLeft() != null)
			{
				return searchHelper(key, node.getLeft());
			}
			else
			{
				return null;
			}
		}
		else if(node.getKey().compareTo(key) == 0)
		{
			return node.getElement();
		}
		else
		{
			return null;
		}
		
	}

	public E search(K key) {
		return searchHelper(key, root);
	}
	
	/*
	 * insertHelper - inserts a node by recursively comparing the key to another node to see if it's larger or smaller, to determine where
	 * to insert the node based on the BST node guidelines
	 * param - key, element, and node
	 * return - nothing
	 */
	
	public void insertHelper(K key, E element, BSTNode node)
	{
		if(key.compareTo(node.getKey()) <= -1)
		{
			if(node.getLeft() != null)
			{
				insertHelper(key, element, node.getLeft());
			}
			else
			{
				node.setLeft(new BSTNode<E, K>(key, element, null, null));
			}
		}
		else if(key.compareTo(node.getKey()) >= 1)
		{
			if(node.getRight() != null)
			{
				insertHelper(key, element, node.getRight());
			}
			else
			{
				node.setRight(new BSTNode<E, K>(key, element, null, null));
			}
		}
	}

	public void insert(K key, E element) {
		if(root == null)
		{
			root = new BSTNode<E, K>(key, element, null, null);
		}
		
		else
		{
			insertHelper(key, element, root);
		}
		
	}
	
	/*
	 * delete - deletes the node at the specified key, and adjusts the surronding nodes in order to keep the proper order
	 * param - key
	 * return - nothing
	 */
	public void delete(K key)
	{
		if(search(key) == null) 
		{
			return;
		}
		if(root == null) 
		{
			return;
		}
		BSTNode<E,K> node = root;
		BSTNode<E,K> child = null;
		BSTNode<E,K> parent1 = null;
		BSTNode<E,K> parent2 = null;;
		boolean Left = true;
		boolean Left2 = false;
		boolean found = false;
		
		while(!found)
		{
			if(node.getKey().compareTo(key) == 0)
			{
				found = true;
			}
			else if(key.compareTo(node.getKey())<=-1)
			{
				if(node.getLeft() != null)
				{
					parent1 = node;
					node = node.getLeft();
					Left = true;
				}
			}
			else if(key.compareTo(node.getKey()) >= 1)
			{
				if(node.getRight() != null)
				{
					parent1 = node;
					node = node.getRight();
					Left = false;	
				}
			}
		}
		
		if(node.getLeft() != null && node.getRight() != null)
		{
			child = node.getRight();
			parent2 = node;
			while(child.getLeft() != null)
			{
				parent2 = child;
				child = child.getLeft();
				Left2 = true;
			}
			if(Left2)
			{
				parent2.setLeft(child.getLeft());	
			}
			else 
			{
				parent2.setRight(child.getLeft());
			}
			child.setLeft(node.getLeft());
			child.setRight(node.getRight());
		}
		else if(node.getLeft() == null && node.getRight() != null)
		{
		    child = node.getRight();
		}
		else if(node.getLeft() != null && node.getRight() == null)
		{
			child = node.getLeft();
		}
		else if(node.getLeft() == null && node.getRight() == null)
		{
			if(Left)
			{
				parent1.setLeft(null);
			} 
			else
			{
				parent1.setRight(null);
			}
			return;
		}
		if(root == node)
		{
			root = child;
			return;
		}
		
		if(Left) 
		{
			parent1.setLeft(child);
		}
		else
		{
			parent1.setRight(child);
		}
	}
	
	//printHelp - recursive function to aid in the printTree function
	//param - BSTNode
	//output - string representation of the tree
	public String printHelp(BSTNode<E,K> node)
	{
		String s = "";
		if(node.getLeft() != null)
		{
			s += printHelp(node.getLeft());
		}
		s += "Key: "+ node.getKey() + ": " + "Element: " + node.getElement().toString() + ", ";
		if(node.getRight() !=null)
		{
			s += printHelp(node.getRight());
		}
		return s;
	}

	public void printTree() {
		// prints the tree in order
		System.out.println(printHelp(root));
		
	}
	
	//depthHelp(BTSNode) - recursive function to aid in calculating the depth of the tree
	//param - BTSNode
	//output - depth of tree
	
	public int depthHelp(BSTNode<E,K> node)
	{
		int sum1 = 0;
		int sum2 = 0;
		if(node.getLeft() != null)
		{
			sum1 += depthHelp(node.getLeft());
		}
		if(node.getRight() != null)
		{
			sum2 += depthHelp(node.getRight());
		}
		if(node.getLeft() == null && node.getRight() == null)
		{
			return 1;
		}
		if(sum1>sum2)
		{
			return sum1 +1;
		}
		else
		{
			return sum2 +1;
		}
	}

	public int depth() {
		// Returns the depth of the tree
		return depthHelp(root);
	}
	
	public static void main(String args[])
	{
		BSTNode<Integer, SortableString> node6 = new BSTNode<Integer, SortableString>(new SortableString("6"), new Integer(6), null, null);
		BSTNode<Integer, SortableString> node5 = new BSTNode<Integer, SortableString>(new SortableString("5"), new Integer(5), null, null);
		BSTNode<Integer, SortableString> node4 = new BSTNode<Integer, SortableString>(new SortableString("4"), new Integer(4), null, null);
		BSTNode<Integer, SortableString> node3 = new BSTNode<Integer, SortableString>(new SortableString("3"), new Integer(3), null, null);
		BSTNode<Integer, SortableString> node2 = new BSTNode<Integer, SortableString>(new SortableString("2"), new Integer(2), null, null);
		BSTNode<Integer, SortableString> node1 = new BSTNode<Integer, SortableString>(new SortableString("1"), new Integer(1), null, null);
		BSTNode<Integer, SortableString> node7 = new BSTNode<Integer, SortableString>(new SortableString("7"), new Integer(7), null, null);

		node2.setLeft(node1);
		node2.setRight(node3);
		node4.setLeft(node2);
		node4.setRight(node6);
		node6.setLeft(node5);
		
		BSTDictionary<Integer, SortableString> test = new BSTDictionary<Integer, SortableString>(node4);
		test.printTree();
		System.out.println("Depth of tree: "+test.depth());
		System.out.println("Trying to find key 5: "+test.search(node5.getKey()));
		System.out.println("Trying to find key 7: "+test.search(node7.getKey()));
		test.insert(new SortableString("9"), new Integer(9));
		test.printTree();
		test.delete(node2.getKey());
		test.printTree();
	}

}
