import java.util.*;
import java.io.*;

/************************************************8
* Class: locationFileIO
* Fields: none
* Purpose: To read files of locations and details
*
*
*************************************************/
public class locationFileIO
{
	/************************************************8
	* Method: readLocations
	* Import: file (String)
	* Export: map (locationGraph)
	*
	*
	*************************************************/
	public static locationGraph readLocations(String file) 
	{
		locationGraph map = new locationGraph();
		BufferedReader bufReader = null;
		String line = null;
		String[] splitLine = null;
		String vert1 = null;
		String vert2 = null;
		
		double edgeWeight = 0;
		int getVertexCount = 0;
		int getEdgeCount = 0;
		int i = 0;
		
		try
		{
			bufReader = new BufferedReader(new FileReader(file));
			line = bufReader.readLine();
			
			while(line != null)
			{
				
				splitLine = line.split(" ");
				if(i == 0)
				{
					getVertexCount = Integer.parseInt(splitLine[0]);
					getEdgeCount = Integer.parseInt(splitLine[1]);
				}
				else
				{
					vert1 = splitLine[0];
					vert2 = splitLine[1];
					edgeWeight = Double.parseDouble(splitLine[2]);
					
					if(!map.existVertex(vert1))
						map.insertVertex(vert1);
					
					if(!map.existVertex(vert2))
						map.insertVertex(vert2);
					
							
					map.insertEdge(vert1, vert2, edgeWeight);
				}		
				i++;
				line = bufReader.readLine();
				
			}
			
		}
		catch(IOException e)
		{
			System.out.println("File does not exist");
			try
			{
				bufReader.close();
			}
			catch(IOException e2)
			{
			  System.out.println("File not exist.");
			}
		}
	
		return map;
	}
	/************************************************8
	* Method: readDetails
	* Import: detailFile (String), map (locationGraph)
	* Export: map (locationGraph)
	*
	*
	*************************************************/
	public static locationGraph readDetails(String detailFile, locationGraph map)
	{
		BufferedReader bufReader = null;
		String line = null;
		String[] splitLine = null;
		
		String vert = null;
		double temp = 0;
		double humidity = 0;
		double windSpeed = 0;
		
		int i = 0;
		try
		{	
			bufReader = new BufferedReader(new FileReader(detailFile));
			line = bufReader.readLine();
			
			while(line != null)
			{
				splitLine = line.split(" ");
				vert = splitLine[0];
				temp = Double.parseDouble(splitLine[1]);
				humidity = Double.parseDouble(splitLine[2]);
				windSpeed = Double.parseDouble(splitLine[3]);
				
				map.insertDetails( vert, temp, windSpeed, humidity);
				i++;
				line = bufReader.readLine();
			
			}
		
		}
		catch(IOException e)
		{
			System.out.println("File does not exist");
			try
			{
				bufReader.close();
			}
			catch(IOException e2)
			{}
		}
		return map;
		
		
	} 

}
