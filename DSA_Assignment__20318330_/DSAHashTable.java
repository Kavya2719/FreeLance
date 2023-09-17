import java.util.*;
import java.util.Scanner;

/**************************************8
* Class: DSAHashTable
* Author: Adivishva Mohan
* All reference at the bottom
***************************************/
public class DSAHashTable 
{
	private class DSAHashEntry /*For privacy and to protect data Entry will be private*/
	{
		private String key;
		private Object value;
		private int state;
		
		public DSAHashEntry()
		{
			key = "";
			value = null;
			state = 0;
		}
		
		public DSAHashEntry(String inKey, Object inValue)
		{
			key = inKey;
			value = inValue;
			state = 1;
		}
		
		public String getKey()
		{
			return key;
		}
		
		public Object getValue()
		{
			return value;
		}
		
		public int getState()
		{
			return state;
		}
		
		public void setKey(String inKey)
		{
			key = inKey;
		}
		
		public void setValue(Object inValue)
		{
			value = inValue;
		}
		
		public void setState(int inState)
		{
			state = inState;
		}
		
	}
	
	private DSAHashEntry[] hashArray;
	private DSAHashEntry removeEntry;
	private int count;
	private DSAHashEntry hashPrint;
	
	public void DSAHashTable(int tableSize)
	{
		int actualSize = tableSize;
		hashArray = new DSAHashEntry[actualSize];
		
		for(int i = 0; i < actualSize; i++)
		{
			hashArray[i] = new DSAHashEntry();
			
		}
	}
	
	public void put(String inKey, Object inValue)
	{
		int index = hash(inKey);
		
		int stepSize = stepHash(inKey);
		
		while (hashArray[index].getState() == 1)
		{
			index = (index + stepSize) % hashArray.length;
			if((index < 0)||(index > hashArray.length-1))
			{
				index = 0;
			}
			
		}
		hashArray[index].setState(1);
		hashArray[index].setValue(inValue);
		hashArray[index].setKey(inKey);
		count++;
		
	}
	
	public Object get(String inKey)
	{
		int hashIdx = hash(inKey);
		int origIdx = hashIdx;
		boolean found = false;
		boolean giveUp = false;
		int stepSize = stepHash(inKey);
		
		while ((!found)&&(!giveUp))
		{
			if (hashArray[hashIdx].getState() == 0)
				giveUp = true;
			else if (hashArray[hashIdx].getKey().equals(inKey))
				found = true;
			else
			{
				hashIdx = (hashIdx + stepSize) % hashArray.length;
				if(hashIdx == origIdx)
				{
					giveUp = true;
				}
			}
		}
		if (!found)
		{
			throw new IllegalArgumentException("Key is NOT found");
		}
		Object retValue = hashArray[hashIdx].getValue();
		return retValue;
	
	}
	
	private int hash(String inKey)
	{
		int hashIdx = 0;
		
		for(int i = 0; i < inKey.length(); i++) 
		{
			hashIdx = (31 * hashIdx) + inKey.charAt(i);
		}
		return hashIdx % hashArray.length;
	}
		
	
	public boolean hasKey(String inKey)
	{
		int index = hash(inKey);
		
		int stepSize = stepHash(inKey);
		
		boolean found = false;
		
		while((hashArray[index].getState() == 1) && (!found))
		{
			found = hashArray[index].getKey().equals(inKey);
			index = (index + stepSize) % hashArray.length;
			
		}
		return found;
	}
	
	public Object remove(String inKey)
	{
		Object temp = null;
		int index = hash(inKey);
		
		int stepSize = stepHash(inKey);
		
		boolean found = false;
		
		while((hashArray[index].getState() == 1) && (!found))
		{
			found = hashArray[index].getKey().equals(inKey);
			index = (index + stepSize) % hashArray.length;
			
		}
		if(found)
		{
			index = (index - stepSize) % hashArray.length;
			
			temp = hashArray[index].getValue();
				
			hashArray[index].setValue(null);
			hashArray[index].setKey("");
			hashArray[index].setState(-1);
				
			count--;
		}
		
		return temp;
	}
	
	public int stepHash(String inKey)
	{
		int hashIdx = 0;
		
		for(int i = 0; i < inKey.length(); i++)
		{
			hashIdx = (31 * hashIdx) + inKey.charAt(i);
		}
		return (5 - (hashIdx % 5));
	}
	
	public void printHashTable()
	{
		System.out.println("\nHash Table");
		
		for (int i = 0; i < hashArray.length-1; i++)
		{
			if (hashArray[i] != null)
				System.out.println(hashArray[i].getKey() + " " + hashArray[i].getValue());
		
		}
	
	}


}
/***************************************************************8
*References:
*(1) DSAHashTable.(Adivishva, 2023).Practical 7
*Retrieved from: p7.zip
*(2) DSALecture 7
*****************************************************************/
