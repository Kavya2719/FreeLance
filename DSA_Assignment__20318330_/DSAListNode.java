import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.util.*;
/********************************************************************
*Class: DSAListNode
*Date: 22/06/2023
*Author: Adivishva Mohan
*All references at the bottom
*********************************************************************/
public class DSAListNode
{
	private Object value;
	private DSAListNode next;
	private DSAListNode prev;
	
	public DSAListNode(Object inValue)
	{
		value = inValue;
		next = null;
	}

	public Object getValue()
	{
		return value;
	}
	
	public void setValue(Object inValue)
	{
		value = inValue;
	}
	public DSAListNode getNext()
	{
		return next;
	}
	
	public void setNext(DSAListNode newNext)
	{
		next = newNext;
	}
	public DSAListNode getPrev()
	{
		return prev;
	}
	
	public void setPrev(DSAListNode newPrev)
	{
		prev = newPrev;
	}
	

}
/***************************
*References:
*(1) DSAListNode.(Adivishva, 2023).Practical 4
*Retrieved from: p4.zip
*
****************************/
