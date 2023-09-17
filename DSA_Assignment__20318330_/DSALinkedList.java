import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.util.*;
/****************************************
*Adivishva Mohan
*DSALinkedList
*Reference:
*DSALinkedList practical
*Retrieved from: p4.zip
*
*
*****************************************/
public class DSALinkedList implements Iterable
{
	private class DSALinkedListIterator implements Iterator
	{
		private DSAListNode iterNext;
		
		public DSALinkedListIterator(DSALinkedList theList){
			iterNext = theList.head;
		}
		
		public boolean hasNext() { return (iterNext != null); }
		public Object next() {
			Object value;
			if (iterNext == null)
				value = null;
			else {
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			
			}
			return value;
		}
		public void remove() { throw new UnsupportedOperationException("Not supported");}
		
	}
	
	private DSAListNode head;
	private DSAListNode newNd;
	private DSAListNode currNode;
	private Object nodeValue;
	private DSAListNode prevNd;
	private DSAListNode tail;
	protected int count;
	
	public Iterator iterator()
	{
		return new DSALinkedListIterator(this);
	}
	
	public DSALinkedList()
	{	
		head = null;
		tail = null;
		count = 0;
	}
	
	public int getCount()
	{
		return count;
	}
	public void insertFirst(Object newValue)
	{
		newNd = new DSAListNode(newValue); 
		if(isEmpty())
		{
			head = tail = newNd;
		}
		else
		{
			
			newNd.setNext(head);
			head.setPrev(newNd);
			head = newNd;
			head.setPrev(null);
		}
		count++;
	}
	public void insertLast(Object newValue)
	{
		newNd = new DSAListNode(newValue); //new node 
		if (isEmpty())
		{
			head = tail = newNd;
		}
		else
		{
			newNd.setPrev(tail);
			tail.setNext(newNd);
			tail = newNd;
			tail.setNext(null);
	
		}
		count++;
	}
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public Object peekFirst()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("The list is empty");
		}
		else
		{
			nodeValue = head.getValue();

		}
		return nodeValue;
	}
	
	public Object peekLast()
	{
		if (isEmpty())
		{
			throw new IllegalArgumentException("The list is empty");
		}
		else
		{
			currNode = tail;
			nodeValue = currNode.getValue();	
		}
		return nodeValue;
	}
	public Object removeFirst()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("The list is empty");
		}
		else if (head.getNext() == null)
		{
			nodeValue = head.getValue();
			head = null;
			tail = null;	
		}
		else
		{
			nodeValue = head.getValue();
			head = head.getNext();
			head.setPrev(null);
		}
		count--;
		return nodeValue;
	}
	
	public Object removeLast()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("The list is empty");
		}
		else if (head.getNext() == null)
		{
			nodeValue = tail.getValue();
			head = null;
			tail = null;	
		}
		else
		{
			nodeValue = tail.getValue();
			currNode = tail.getPrev();
			currNode.setNext(null);
			tail = currNode;
		}
		count--;		
		return nodeValue;
	}
	
}


