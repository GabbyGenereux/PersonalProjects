/*
 * Gabrielle Genereux- 100963245
 * Assignment 5 part 2 - Two-Three tree
 */
import java.util.Collections;
import java.util.LinkedList;
import java.math.*;

public class TwoThreeTree {
	
    Node root;
    
    public TwoThreeTree()
    {
    	root = null;
    }
    
    /*
     * void setRoot - sets the root of the node
     * param - the node to set as the root
     */
    public void setRoot(Node n)
    {
    	root = n;
    }
    
    /*
     * Node getRoot - returns the root of the tree
     * returns - the root of the tree
     */
    public Node getRoot()
    {
    	return root;
    }
    
    /*
     * int getHeight - calculates the height of the tree
     * param - the root of the tree
     * returns - an integer representation of the height
     */
    
    public int getHeight(Node n)
    {
    	int max;
    	if(n.getChildren().size() == 0)
    	{
    		return 1;
    	}
    	else
    	{
    		max = getHeight(n.getChildren().getFirst());
    		for(Node node: n.getChildren())
    		{
    			if(getHeight(node) > max)
    			{
    				max = getHeight(node);
    			}
    		}
    		return max + 1;
    	}
    }
    
    /*
     * int valueCount - counts the number of values (either 1 or 2) in the node
     * param - the node
     * returns - an integer representing the number of values
     */
    public int valueCount(Node n)
    {
    	int sum = n.getValues().size();
    	for(Node node: n.getChildren())
    	{
    		sum += valueCount(node);
    	}
    	return sum;
    }
    
    /*
     * string printTree - prints the tree
     * param - the root of the tree/subtree
     * returns - the string representation of the tree
     */
    public String printTree(Node n)
    {
    	String s = "";
    	if(n.getChildren().size() == 2)
    	{
    		s += printTree(n.getChildren().getFirst());
    		s += n.getValues().getFirst() + " ";
    		s += printTree(n.getChildren().getLast());
    	}
    	else if(n.getChildren().size() == 3)
    	{
    		s += printTree(n.getChildren().getFirst());
    		s += n.getValues().getFirst() + " ";
    		s += printTree(n.getChildren().get(1));
    		s += n.getValues().getLast() + " ";
    		s += printTree(n.getChildren().getLast());
    	}
    	else
    	{
    	   for(Integer i: n.getValues())
    	   {
    		   s += i + " ";
    	   }
    	}
    	return s;
    }
    
    /*
     * Node buidlThreeNode - makes a three node out of the given linked list
     * param - linked list of items
     * return - the 3 node built
     */
    public Node buildThreeNode(LinkedList<Integer> list)
    {
    	int childSize = (list.size()-2)/3;
    	LinkedList<Integer> left = new LinkedList<Integer>();
    	LinkedList<Integer> middle = new LinkedList<Integer>();
    	LinkedList<Integer> right = new LinkedList<Integer>();
    	LinkedList<Integer> top = new LinkedList<Integer>();
    	
    	for(int i = 0; i < childSize; i++)
    	{
    		left.add(list.removeFirst());	
    	} 	
    	top.add(list.removeFirst());
    	for(int i = 0; i < childSize; i++)
    	{
    		middle.add(list.removeFirst());	
    		
    	} 	
    	top.add(list.removeFirst());
    	for(int i = 0; i < childSize; i++)
    	{
    		right.add(list.removeFirst());	
    	}
    	
    	Node n = new Node(top.removeFirst());
    	n.addValue(top.removeFirst());
    	if(left.size() != 0)
    	{
        	n.addChild(buildFullBinary(left));
    	}
    	if(middle.size() != 0)
    	{
        	n.addChild(buildFullBinary(middle));	
    	}
    	if(right.size() != 0)
    	{
        	n.addChild(buildFullBinary(right));
    	}
    	return n;
    }
    
    /*
     * Node buildFullBinary - builds a binary tree from the provided list
     * param - linked list of integers to build the binary tree from
     * returns - the root of the binary tree
     */
    public Node buildFullBinary(LinkedList<Integer> list)
    {
    	int childSize = (list.size()-1)/2;
    	LinkedList<Integer> left = new LinkedList<Integer>();
    	LinkedList<Integer> right = new LinkedList<Integer>();
    	LinkedList<Integer> top = new LinkedList<Integer>();
    	if(list.size() > 1)
    	{
    		for(int i = 0; i < childSize; i++)
    		{
    			left.add(list.removeFirst());	
    		}
    	}
    	top.add(list.removeFirst());
    	if(list.size() > 0)
    	{
    		for(int i = 0; i < childSize; i++)
    		{
    			right.add(list.removeFirst());	
    		}
    	}
    	Node node = new Node(top.removeFirst());
        if(left.size() != 0)
        {
       		node.addChild(buildFullBinary(left));
       	}
        if(right.size() != 0)
        {
       		node.addChild(buildFullBinary(right));
       	}
    	return node;
    }
    
    /*
     * LinkedList<Integer> generateList - generates a linked list of integers from root of a tree
     * param - root node of the tree
     * returns - generated linked list 
     */
    public LinkedList<Integer> generateList(Node root)
    {
    	LinkedList<Integer> list = new LinkedList<Integer>();
    	LinkedList<Node> children = root.getChildren();
    	if(children.size() == 2)
    	{
    		list.addAll(generateList(children.getFirst()));
    		list.add(root.getValues().getFirst());
    		list.addAll(generateList(children.getLast()));
    	}
    	else if(children.size() == 3)
    	{
    		list.addAll(generateList(children.getFirst()));
    		list.add(root.getValues().getFirst());
    		list.addAll(generateList(children.get(1)));
    		list.add(root.getValues().getLast());
    		list.addAll(generateList(children.getLast()));
    	}
    	else
    	{
    	   for(Integer i: root.getValues())
    	   {
    		   list.add(i);
    	   }
    	}
    	return list;
    }
    
    /*
     * void insert - inserts a node with the given value
     * param - integer value to be added to the list
     */
    public void insert(int value)
    {
    	int height;
    	LinkedList<Integer> list;
    	if(root == null)
    	{
    		root = new Node(value);
    	}
    	else
    	{
    		height = getHeight(root);
        	double binaryTreeRefactor = Math.pow(2,  height+1)-1;
        	if((valueCount(root) + 1 ) == binaryTreeRefactor)
        	{
    			list = generateList(root);
    			list.add(value);
    			Collections.sort(list);
    			root = buildFullBinary(list);
    			return;
        	}
    		height = getHeight(root);
        	double threeNodeRefactor = 2 + 3*(Math.pow(2,  height-1)-1);
    		if((valueCount(root) + 1)  == threeNodeRefactor) 
    		{
    			list = generateList(root);
    			list.add(value);
    			Collections.sort(list);
    			root = buildThreeNode(list);
    		}
    		else
    		{
        		insertHelper(root, value);
    		}
    	}
    }
    
    /*
     * void insertHelper - shifts the tree around to properly add the value
     * param - root node and integer value
     */
    public void insertHelper(Node root, int value)
    {
    	Node node;
    	if(root.getChildren().size() == 0)
    	{
    		root.addValue(value);
    		return;
    	}
    	if(root.getChildren().size() == 2)
    	{    			
    		if(value < root.getValues().getFirst())
    		{
    			node = root.getChildren().getFirst();
    		}
    		else
    		{
    			node = root.getChildren().getLast();
    		}
    		if(node.getChildren().size() == 2)
    		{  			
    			int height = getHeight(node);
    	    	double threeNodeRefactor = 2 + 3*(Math.pow(2,  height-1)-1);
    			if((valueCount(node) +1) == threeNodeRefactor) 
        		{
        			LinkedList<Integer> list = generateList(node);
        			list.add(value);
        			Collections.sort(list);
        			root.getChildren().removeLast();
        			root.getChildren().add(buildThreeNode(list));
        			return;
        		}
    			else
    			{
    				insertHelper(node, value);
    			}
    		}
			else
			{
				insertHelper(node, value);
			}
    	}
    	else if(root.getChildren().size() == 3)
		{
    		if(value < root.getValues().getFirst())
    		{
    			node = root.getChildren().getFirst();
    		}
    		else if(value > root.getValues().getLast())
    		{
    			node = root.getChildren().getLast();
    		}
    		else
    		{
    			node = root.getChildren().get(1);
    		}
    		if(node.getChildren().size() == 2) 
    		{  			
    			int height = getHeight(node);
    	    	double threeNodeRefactor = 2 + 3*(Math.pow(2,  height-1)-1);
    			if((valueCount(node) +1) == threeNodeRefactor) 
        		{
        			LinkedList<Integer> list = generateList(node);
        			list.add(value);
        			Collections.sort(list);
        			node = buildThreeNode(list);
        		}
    			else
    			{
    				insertHelper(node, value);
    			}
    		}
			else
			{
				insertHelper(node, value);
			}
		}	
    }
    
    /*
     * void remove - removes the node with given element
     * param - integer value to be removed from the tree
     */
    public void remove(int value)
    {
    	LinkedList<Integer> newList = generateList(root);
    	if(newList.contains(value))
    	{
    		newList.remove(new Integer(value));
    		root = null;
    		for(Integer i:newList)
    		{
    			insert(i);
    		}
    		System.out.println("The item has been removed: " + printTree(root));
    	}
    	else
    	{
    		System.out.println("That element does not exist in the tree. " + printTree(root));
    	}	
    }
    
    public static void main(String args[])
    {   	
    	
    	TwoThreeTree t = new TwoThreeTree();
    	System.out.println("Building the tree: ");
    	for(int i = 1; i<=10;i++)
    	{
    		t.insert(i);
    		System.out.println("Tree: "+t.printTree(t.getRoot()));
    	}
    	System.out.println("Removing 3");
    	t.remove(3);
    	System.out.println("Removing 7");
    	t.remove(7);
    	System.out.println("Removing 13");
    	t.remove(13);
    	
    }   
}