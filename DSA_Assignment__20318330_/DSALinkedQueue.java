import java.util.Scanner;
import java.util.*;
import java.lang.*;

/*
* Name: Adivishva Mohan
* date: 04/04/2023
* subject: COMP1002
* All references at the bottom
*/

/*********
Class: DSAQueue
ClassField: queue (double array), count(int)
Class Constant: DEFAULT_CAPACITY <- 100

**********/
public class DSALinkedQueue implements Iterable
{
	private DSALinkedList lists;
	//protected Object[] queue;
	//protected int count = 0;
	protected static final int DEFAULT_CAPACITY = 100;
	protected int capacity;
	
	
	public DSALinkedQueue()
	{	
		this.capacity = DEFAULT_CAPACITY;
		//this.queue = new Object[capacity];
		//this.count = 0;
		this.lists = new DSALinkedList();	
	}
	
	public DSALinkedQueue(int maxCapacity)
	{
		this.capacity = maxCapacity;
		this.lists = new DSALinkedList();
	}
	
	public Iterator iterator()
	{
		return lists.iterator();
	}
	
	public int getCount()
	{
		return lists.getCount();
	}
	//(1)
	public boolean isEmpty()
	{	
		return lists.isEmpty();
	}
	//(1)
	
	public boolean isFull()
	{
		return lists.getCount() == capacity;
	}
	public void enqueue(Object value)
	{
		if (isFull())
		{
			throw new IllegalStateException("Cannot enqueue, queue is full");
		}
		
		lists.insertLast(value);
	
	}
	//(1)
	public Object dequeue()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Cannot dequeue, queue is empty");
		}
		
		return lists.removeFirst();
	}

	//(1)
	public Object peek()
	{
		return lists.peekFirst();
	}
	
	public void displayQueue()
	{
		Iterator iterate = lists.iterator();
		while(iterate.hasNext())
		{
			System.out.println(iterate.next());
		}
	}
	
}

/*******************
Reference:
(1) - Queue Data Structure - Definition and Java Example Code(March 4, 2022)freeCodeCamp
 https://www.freecodecamp.org/news/queue-data-structure-definition-and-java-example-code
#:~:text=Enqueue%3A%20Adds%20an%20item%20from,if%20the%20queue%20is%20empty.
(2) DSALinkedQueue.(Adivishva ,2023).Practical 6
Retrieved from: p6.zip
********************/
