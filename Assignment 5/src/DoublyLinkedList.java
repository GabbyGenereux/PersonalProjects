/*
 * Gabrielle Genereux
 * 100963245
 * Assignment 5 Part 1 - Doubly linked list
 */

public class DoublyLinkedList 
{
	private Node listHead;
	
	public DoublyLinkedList()
	{
		listHead = new Node(0, null, null);
		listHead.setNext(listHead);
		listHead.setPrevious(listHead);
	}
	
	/*
	 * void sortedAdd - adds a value into the list, keeping it sorted
	 * param - value to be added
	 */
	public void sortedAdd(int value)
	{
		if(listHead.getNext() == listHead)
		{
			listHead.setNext(new Node(value, listHead, listHead));
			listHead.setPrevious(listHead.getNext());
		}
		else
		{
			for(Node curr = listHead.getNext(); curr != listHead; curr=curr.getNext())
			{
				if(curr.getValue() > value)
				{
					Node newNode = new Node(value, curr, curr.getPrevious());
					curr.setPrevious(newNode);
					newNode.getPrevious().setNext(newNode);
					return;
				}
				else if(curr.getValue() < value && curr.getNext() == listHead)
				{
					Node newNode = new Node(value, curr.getNext(), curr);
					curr.setNext(newNode);
					listHead.setPrevious(newNode);
					
				}
			}
		}
	}
	
	/*
	 * void sortedRemove - removes a value from the list, keeping it sorted
	 * param - value to be added
	 */
	public void sortedRemove(int value)
	{
		for(Node curr = listHead.getNext(); curr!=listHead;curr=curr.getNext())
		{
			if(curr.getValue() == value)
			{
				curr.getPrevious().setNext(curr.getNext());
				curr.getNext().setPrevious(curr.getPrevious());
				return;
			}
		}
		System.out.println("Value not found in list");
	}
	
	/*
	 * void printList - prints the list
	 */
	public void printList()
	{
		for(Node p = listHead.getNext(); p != listHead; p = p.getNext())
		{
			System.out.print(p.getValue()+ " ");
		}
		System.out.println();
	}
	
	public static void main(String args[])
	{
		DoublyLinkedList list1 = new DoublyLinkedList();
		System.out.println("Adding 1: ");
		list1.sortedAdd(1);
		list1.printList();
		System.out.println("Adding 5: ");
		list1.sortedAdd(5);
		list1.printList();
		System.out.println("Adding 7: ");
		list1.sortedAdd(7);
		list1.printList();
		System.out.println("Adding 9: ");
		list1.sortedAdd(9);
		list1.printList();
		System.out.println("Adding 3: ");
		list1.sortedAdd(3);
		list1.printList();
		System.out.println("Adding 2: ");
		list1.sortedAdd(2);
		list1.printList();
		System.out.println("Removing 3 and 7");
		list1.sortedRemove(3);
		list1.sortedRemove(7);
		list1.printList();
		System.out.println("Removing 4");
		list1.sortedRemove(4);
		list1.printList();
		
	}
	
	private class Node
	{
		private int value;
		private Node next;
		private Node previous;
		
		public Node(int value, Node next, Node previous)
		{
			this.value = value;
			this.next = next;
			this.previous = previous;
		}
		public Node(Node ptr)
		{
			this.next = ptr;
		}
		public int getValue()
		{
			return value;
		}
		public Node getNext()
		{
			return next;
		}
		public Node getPrevious()
		{
			return previous;
		}
		public void setNext(Node ptr)
		{
			next = ptr;
		}
		public void setPrevious(Node ptr)
		{
			previous = ptr;
		}
	}

}
