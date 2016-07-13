//import StackReferenceBased.Node;

public class StackReferenceBased
{
	private Node top;
	private int items;
	
	public StackReferenceBased()
	{
		createStack();
	}
	
	public void createStack()
	{
		top = null;
		items = 0;
	}
	
	public void popAll()
	{
		top = null;
		items = 0;
	}
	
	public boolean isEmpty()
	{
		if(top==null||items==0) 
		{
			return true;
		}
		return false;
	}
	
	public int size()
	{
		return items;
	}
	
	public void push(Object value)
	{
			top = new Node(value, top);
			items++;
	}
	
	public Object pop()
	{
		Node temp = top;
		top = top.getNext();
		items--;
		return temp.getValue();
	}
	
	public int getitems()
	{
		return items;
	}
	
	public void getList()
	{
		System.out.print(items + " items in the stack: ");
		for(Node p=top; p!=null; p=p.getNext())
		{
			System.out.print(p.getValue());
			if(p.getNext()!=null) System.out.print(", ");
		}
		System.out.println();
	}
	
	public Object peek(int index)
	{
		int i=0;
		for(Node p=top; p !=null; p=p.getNext())
		{
			if(i == index)
			{
				return p.getValue();
			}
		}
		return null;
	}
	
	public static void main(String args[])
	{
		StackReferenceBased list = new StackReferenceBased();
		list.push(12);
		list.push(3);
		list.push(25);
		list.push(18);
		list.peek(2);
		
		list.getList();
		
		list.push(13);
		list.peek(3);
		list.push(17);
		list.peek(1);
		list.getList();
		list.pop();
		list.peek(2);
		list.getList();
	}
	
	private class Node
	{
		private Object value;
		private Node next;
		
		public Node(Object value, Node next)
		{
			this.value = value;
			this.next = next;
		}
		public Object getValue()
		{
			return value;
		}
		public Node getNext()
		{
			return next;
		}
		public void setNext(Node ptr)
		{
			next = ptr;
		}
	}
}