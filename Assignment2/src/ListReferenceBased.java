/*
 * Gabrielle Genereux
 * 100963245
 * Assignment 2 - List Reference Based
 */

public class ListReferenceBased {
	private Node head;
	private int numItems;
	
	public ListReferenceBased()
	{
		head = null;
		numItems = 0;
	}
	
	public boolean isEmpty()
	{
		if(head==null||numItems==0) 
		{
			return true;
		}
		return false;
	}
	
	public int size()
	{
		return numItems;
	}
	
	public void add(Integer value, int index)
	{
		if(index==0)
		{
			head = new Node(value, head);
			numItems++;
			return;
		}
		int i=0;
		for(Node p=head; p!=null; p=p.getNext())
		{
			if(i+1==index)
			{
				p.setNext(new Node(value, p.getNext()));
				numItems++;
			}
			i++;
		}
	}
	
	public void remove(int index)
	{
		if(index==0)
		{
			head = head.getNext();
			numItems--;
			return;
		}
		int i=0;
		for(Node p=head; p.getNext()!=null; p=p.getNext())
		{
			if(i+1==index)
			{
				p.setNext(p.getNext().getNext());
				numItems--;
			}
			i++;
		}
	}
	
	public int getNumItems()
	{
		return numItems;
	}
	
	public void display()
	{
		System.out.print(numItems + " items in the linked list: ");
		for(Node p=head; p!=null; p=p.getNext())
		{
			System.out.print(p.getValue());
			if(p.getNext()!=null) System.out.print(", ");
		}
		System.out.println();
	}
	
	public static void main(String args[])
	{
		ListReferenceBased list = new ListReferenceBased();
		list.add(12, list.getNumItems());
		list.add(3, list.getNumItems());
		list.add(25, list.getNumItems());
		list.add(18, list.getNumItems());
		list.display();
		
		list.add(13,0);
		list.display();
		list.add(17, 2);
		list.display();
		list.remove(4);
		list.display();
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
