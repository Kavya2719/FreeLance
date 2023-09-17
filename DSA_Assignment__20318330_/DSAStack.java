import java.util.Scanner;
import java.util.*;


/*
* Name: Adivishva Mohan
* date: 04/04/2023
* subject: COMP1002
* All references at the bottom
*/

public class DSAStack implements Iterable
{
	//private Object[] stack;
	//private Object topVal;
	//private int count;
	private static final int DEFAULT_CAPACITY = 100;
	private int capacity;

	private DSALinkedList list;
	
	
	public DSAStack()
	{
		this.capacity = DEFAULT_CAPACITY;
		//this.stack = new Object[capacity];
		//this.count = 0;
		this.list = new DSALinkedList();
	}
	
	public Iterator iterator()
	{
		return list.iterator();
	}
	public DSAStack(int maxCapacity)
	{
		this.capacity = maxCapacity;
		//this.stack = new Object[maxCapacity];
		//this.count = 0;
		this.list = new DSALinkedList();
	}

	public int getCount()
	{
		return list.getCount();
	}
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	
	}
	public boolean isFull()
	{
		return list.getCount() == capacity;
			
	}
	
	public void push(Object value)
	{
		if (isFull())
		{
			throw new IllegalStateException("Cannot Push, stack full");
		}
		else
		{
			list.insertFirst(value);
			//stack[count] = value;
			//count = count + 1;
		}
	}
	
	public Object pop()
	{
		//topVal = top();
		if (isEmpty())
		{
			throw new IllegalStateException("Cannot Pop, stack empty");
		}
		
		return list.removeFirst();
	}
	
	public Object top()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Cannot peek, stack empty");	
		}
		
		return list.peekFirst();
	}
	
	public void printStack()
	{
		Iterator iterate = list.iterator();
		while(iterate.hasNext())
		{
			System.out.println(iterate.next());
		}
	}
	
/***
References
(1)Java Program to Implement stack data structure.(n.d.).Programiz
https://www.programiz.com/java-programming/examples/stack-implementation
(2) DSAStack.java.(Adivishva, 2023).practical 3
Retrieved from: p3.zip
***/	
	
}
