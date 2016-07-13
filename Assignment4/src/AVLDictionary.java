
public class AVLDictionary<E, K extends Sortable> implements Dictionary<E, K> 
{
	private AVLNode<E, K> root;
	
	public AVLDictionary(AVLNode<E, K> n)
	{
		root = n;
	}
	
	public AVLDictionary()
	{
		root = null;
	}
	
	
	/*
	 * searchHelper: checks a node against the provided key to see if the two match, and resursively does this until the node is found
	 * param - key, node
	 * returns - element at given key if found, else null
	 */
	public E searchHelper(K key, AVLNode<E, K> node)
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
		// TODO Auto-generated method stub
		return searchHelper(key, root);
	}

	/*
	 * insertHelper - inserts a node by recursively comparing the key to another node to see if it's larger or smaller, to determine where
	 * to insert the node based on the AVL node guidelines
	 * param - key, element, and node
	 * return - nothing
	 */
	public void insertHelper(K key, E element, AVLNode node)
	{
		if(key.compareTo(node.getKey()) <= -1)
		{
			if(node.getLeft() != null)
			{
				insertHelper(key, element, node.getLeft());
			}
			else
			{
				node.setLeft(new AVLNode<E, K>(key, element, null, null, 1));
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
				node.setRight(new AVLNode<E, K>(key, element, null, null, 1));
			}
		}
	}

	public void insert(K key, E element) {
		if(root == null)
		{
			root = new AVLNode<E, K>(key, element, null, null, 1);
		}
		
		else
		{
			insertHelper(key, element, root);
			//getBalance(root.getLeft(), root.getRight());
		}
		
	}

	/*
	 * delete - deletes the node at the specified key, and adjusts the surronding nodes in order to keep the proper order
	 * param - key
	 * return - nothing
	 */
	public void delete(K key)
	{
		if(search(key) == null) return;
		if(root == null) return;
		AVLNode<E,K> n = root;
		AVLNode<E,K> child = null;
		AVLNode<E,K> parent = null;
		AVLNode<E,K> parent2 = null;;
		boolean Left = true;
		boolean Left2 = false;
		boolean found = false;
		
		while(!found)
		{
			if(n.getKey().compareTo(key) == 0)
			{
				found = true;
			}
			else if(key.compareTo(n.getKey())<=-1)
			{
				if(n.getLeft() != null)
				{
					parent = n;
					n = n.getLeft();
					Left = true;
				}
			}
			else if(key.compareTo(n.getKey()) >= 1)
			{
				if(n.getRight() != null)
				{
					parent = n;
					n = n.getRight();
					Left = false;	
				}
			}
			
		}
		
		if(n.getLeft() != null && n.getRight() != null)
		{
			child = n.getRight();
			parent2 = n;
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
			else parent2.setRight(child.getLeft());
			child.setLeft(n.getLeft());
			child.setRight(n.getRight());
		}
		else if(n.getLeft() == null && n.getRight() != null)
		{
		    child = n.getRight();
		}
		else if(n.getLeft() != null && n.getRight() == null)
		{
			child = n.getLeft();
		}
		else if(n.getLeft() == null && n.getRight() == null)
		{
			if(Left)
			{
				parent.setLeft(null);
			} 
			else
			{
				parent.setRight(null);
			}
			return;
		}
		if(root == n)
		{
			root = child;
			return;
		}
		
		if(Left) 
		{
			parent.setLeft(child);
		}
		else
		{
			parent.setRight(child);
		}
		
		//getBalance(root.getLeft(), root.getRight());
	}
	
	//printHelp - recursive function to aid in the printTree function
	//param - AVLNode
	//output - string representation of the tree
	public String printHelp(AVLNode<E, K> root2)
	{
		String s = "";
		if(root2.getLeft() != null)
		{
			s += printHelp(root2.getLeft());
		}
		s += "Key: "+ root2.getKey() + ": " + "Element: " + root2.getElement().toString() + ", ";
		if(root2.getRight() !=null)
		{
			s += printHelp(root2.getRight());
		}
		return s;
	}

	public void printTree() {
		// prints the tree in order
		System.out.println(printHelp(root));
	}

	
	//depthHelp(AVLNode) - recursive function to aid in calculating the depth of the tree
	//param - AVLNode
	//output - depth of tree
	
	public int depthHelp(AVLNode<E,K> node)
	{
		int sum1 = 0;
		int sum2 = 0;
		if(node == null)
		{
			return 0;
		}
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
	
	/*
	 * leftRotate - rotates the node left by 1
	 * param - AVLNode
	 * output - AVLNode
	 */
	
	private AVLNode<E,K> leftRotate(AVLNode<E,K> node)
	{
			AVLNode<E,K> r = node.left; //?
			node.left = node.right;
			r.right = node;
            return r;
			
	}
	
	/*
	 * rightRotate - rotates the node right by 1
	 * param - AVLNode
	 * output - AVLNode
	 */
	
	private AVLNode<E,K> rightRotate(AVLNode<E,K> node)
	{
			AVLNode<E,K> r = node.right;
			node.right = node.left;
			r.left = node;
			return r;
	}
	
	/*
	 * getBalance - balances the tree based on AVL guidelines
	 * param - two AVLNodes
	 * return - nothing
	 */
	
	public void getBalance(AVLNode<E,K> node1, AVLNode<E,K> node2)
	{
		if(node1 == null || node2 == null)
		{
			return;
		}
		
		int depth1 = depthHelp(node1); //convention: depth1 = left node
		int depth2 = depthHelp(node2); //convention: depth2 = right now
		int tempBalance = 1;
		
		//while(tempBalance != 0)
		//{
			tempBalance = depth2-depth1;
			if(tempBalance > 1) //depth2 is larger than depth1
			{
				System.out.println("The right branch is larger");
				if(depthHelp(node2.left) < 0)
				{
					node2.left = leftRotate(node2.left);
					leftRotate(node2);
				}
				else
				{
					leftRotate(node2);
				}
			}
			else if(tempBalance < -1) //depth1 is larger than depth2
			{
				System.out.println("The left branch is larger");
				if(depthHelp(node1.right) > 0)
				{
					node1.right = leftRotate(node1.right);
					leftRotate(node1);
				}
				else
				{
					leftRotate(node1);
				}
			}
		//}
		System.out.println("The tree is balanced");
		printTree();
		
	}
	
	public static void main(String args[])
	{
		AVLNode<Integer, SortableString> node6 = new AVLNode<Integer, SortableString>(new SortableString("6"), new Integer(6), null, null, 1);
		AVLNode<Integer, SortableString> node5 = new AVLNode<Integer, SortableString>(new SortableString("5"), new Integer(5), null, null, 1);
		AVLNode<Integer, SortableString> node4 = new AVLNode<Integer, SortableString>(new SortableString("4"), new Integer(4), null, null, 1);
		AVLNode<Integer, SortableString> node3 = new AVLNode<Integer, SortableString>(new SortableString("3"), new Integer(3), null, null, 1);
		AVLNode<Integer, SortableString> node2 = new AVLNode<Integer, SortableString>(new SortableString("2"), new Integer(2), null, null, 1);
		AVLNode<Integer, SortableString> node1 = new AVLNode<Integer, SortableString>(new SortableString("1"), new Integer(1), null, null, 1);
		
		node2.setLeft(node1);
		node2.setRight(node3);
		node4.setLeft(node2);
		node4.setRight(node6);
		node6.setLeft(node5);
		
		AVLDictionary<Integer, SortableString> test2 = new AVLDictionary<Integer, SortableString>(node4);
		test2.printTree();
		System.out.println(test2.depth());
		System.out.println(test2.search(node6.getKey()));
		System.out.println("adding 9: 9");
		test2.insert(new SortableString("7"), new Integer(7));
		test2.insert(new SortableString("8"), new Integer(8));
		test2.insert(new SortableString("9"), new Integer(9));
		test2.printTree();
		test2.getBalance(node4.getLeft(), node4.getRight());
	}
	

}
