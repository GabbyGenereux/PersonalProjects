import java.util.Collections;
import java.util.LinkedList;


public class Node 
{
	private LinkedList<Integer> values;
	   private LinkedList<Node> children; 
	   
	   public Node(int value)
	   {
		   this.values = new LinkedList<Integer>();
		   this.children= new LinkedList<Node>();
		   values.add(value);
	   }
	   
	   public LinkedList<Integer> getValues()
	   {
		   return values;
	   }
	   
	   public LinkedList<Node> getChildren()
	   {
		   return children;
	   }
	   
	   public void addValue(int value)
	   {
		   values.add(value);
		   Collections.sort(values);
	   }
	   
	   public void addChild(Node child)
	   {
		   children.add(child);
	   }
}