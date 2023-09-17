import java.util.*;
import java.io.*;
/***********************************
* Class: unitTestLocation
* Author: Adivishva Mohan
* Purpose: To test my program.
*
************************************/

public class unitTestLocation
{
	public static void main(String args[])
	{
		int passed = 0;
		int numTests = 0;
		
		System.out.println("Testing readLocations");
		locationGraph mapFile = locationFileIO.readLocations("location.al");
		System.out.println("readLocations test Pass");
		
		System.out.println("Testing reading UAV data of each vertices and adding them with the existing location map");
		locationGraph mapWithDetails = locationFileIO.readDetails("UAVdata.al", mapFile);
		
		try
		{
			
			try
			{
				System.out.println("Testing Vertex Insertion A");
				mapWithDetails.insertVertex("A");
			
			}
			catch(IllegalArgumentException k)
			{
				System.out.println(k.getMessage());
			}
			
			try
			{
				System.out.println("Testing Vertex Insertion L");
				mapWithDetails.insertVertex("L");
				System.out.println("PASSED!");
				
				System.out.println("Testing existence of vertex L");
				mapWithDetails.existVertex("L");
				if(!mapWithDetails.existVertex("L"))
				{
					throw new IllegalArgumentException("Oh no, vertex L does NOT exist");
				}
				System.out.println("PASS");
				
				System.out.println("Testing existence of vertex B");
				mapWithDetails.existVertex("B");
				if(!mapWithDetails.existVertex("B"))
				{
					throw new IllegalArgumentException("Oh no, vertex L does NOT exist");
				}
				System.out.println("PASS");
				
				System.out.println("Testing existence of vertex D");
				mapWithDetails.existVertex("D");
				if(!mapWithDetails.existVertex("D"))
				{
					throw new IllegalArgumentException("Oh no, vertex L does NOT exist");
				}
				System.out.println("PASS");
				
				System.out.println("Testing existence of vertex F");
				mapWithDetails.existVertex("F");
				if(!mapWithDetails.existVertex("F"))
				{
					throw new IllegalArgumentException("Oh no, vertex L does NOT exist");
				}
				System.out.println("PASS");
				
				System.out.println("Testing existence of vertex J");
				mapWithDetails.existVertex("J");
				if(!mapWithDetails.existVertex("J"))
				{
					throw new IllegalArgumentException("Oh no, vertex L does NOT exist");
				}
				System.out.println("PASS");
			}
			catch(IllegalArgumentException g)
			{
				System.out.println(g.getMessage());
			}
			
			try
			{
				System.out.println("Testing Edge Insertion J and L");
				mapWithDetails.insertEdge("J", "L", 2.8);
				System.out.println("PASS");
				
				System.out.println("Testing whether J has a adjacent vertex L");
				mapWithDetails.isAdjacent("J", "L");
				if(!mapWithDetails.isAdjacent("J", "L"))
				{
					throw new IllegalArgumentException("Oh no, no edge between J and L exists");
				}
				System.out.println("PASS");
				
				System.out.println("Testing whether A is adjacent to F");
				mapWithDetails.isAdjacent("A", "F");
				if(!mapWithDetails.isAdjacent("A", "F"))
				{
					System.out.println("Oh no, A to F edge not existential");
				}
				System.out.println("PASS");
				
				System.out.println("Testing whether A is adjacent to F");
				mapWithDetails.isAdjacent("A", "B");
				if(!mapWithDetails.isAdjacent("A", "B"))
				{
					throw new IllegalArgumentException("Oh no, no edge between A and F exists");
				}
				System.out.println("PASS");
				
				System.out.println("Testing whether A is adjacent to F");
				mapWithDetails.isAdjacent("C", "G");
				if(!mapWithDetails.isAdjacent("C", "G"))
				{
					throw new IllegalArgumentException("Oh no, no edge between A and F exists");
				}
				System.out.println("PASS");
				
				System.out.println("Testing whether A is adjacent to no vertex");
				mapWithDetails.isAdjacent("C", null);
				if(!mapWithDetails.isAdjacent("C", null))
				{
					System.out.println("There must be two label and must exist");
				}
				System.out.println("PASS");
			}
			catch(IllegalArgumentException l)
			{
				System.out.println(l.getMessage());
			}
			
			try 
			{
				System.out.println("Testing getVertexCount");
				numTests++;
				int count = 11; // A, B, C, D, E, F, G, H, I, J, L
				int match = mapWithDetails.getVertexCount(); //Getting number of edges
				if(count != match)
				{
					throw new IllegalArgumentException("Edge count incorrect, Actual: " + match + " Expected: " + count);
				}
				passed = passed + 1;
				System.out.println("PASSED");
			
			}
			catch(IllegalArgumentException l)
			{
				System.out.println("FAILED: " + l.toString());
			}
				
		}
		
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			System.out.println("Testing firstDepthSearch");
			numTests++;
			
			String[] DFS = {"BA", "AC", "CD", "DH", "HF", "FE", "EG", "GJ", "JI", "JL"};
			
			DSALinkedQueue dfsQueue = mapWithDetails.depthFirstSearch("B");
			dfsQueue.displayQueue();
			
			for (int i = 0; i < DFS.length; i++)
			{
				Object to = dfsQueue.dequeue();
				
				System.out.println("to is " + to);
				
				System.out.println("DFS i is " + DFS[i]);
				
				if(!to.equals(DFS[i]))
				{
					throw new IllegalArgumentException("FAILED, element not match");
				}
				
				System.out.println("pass");
			}
			
			
			dfsQueue.displayQueue();
			passed++;
			System.out.println("PASSED");
		
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("FAILED: " + e.getMessage());
		}
		
		try
		{
			System.out.println("Testing BreadthFirstSearch");
			numTests++;
			String[] BFS = {"A","B", "C", "E", "F", "D", "G", "I", "H", "J", "L"};
			int i = 0;
			DSALinkedQueue bfsQueue = mapWithDetails.breadthFirstSearch("A","J");
			while(!bfsQueue.isEmpty())
			{
				Object too = bfsQueue.dequeue();
				
				System.out.println("too is " + too);
				System.out.println("BFS is " + BFS[i]);
				String bfs = too.toString();
				if(!bfs.equals(BFS[i]))
				{
					throw new IllegalArgumentException("FAILED, element not match");
				}
				
				i++;
				System.out.println("pass");
			}
			bfsQueue.displayQueue();
			passed++;
			System.out.println("PASSED");
			
			
		} catch(IllegalArgumentException e)
		{	
			System.out.println("FAILED: " + e.getMessage());
		}
		System.out.println("Printing listDisplay");
		mapWithDetails.listDisplay();
		
		System.out.println("Printing matrixDisplay");
		mapWithDetails.matrixDisplay();
		
		System.out.println("Printing UAV details of each vertex");
		mapWithDetails.displayDetails();
		
		mapWithDetails.displayDetailsByLocation("A");
	}

}
