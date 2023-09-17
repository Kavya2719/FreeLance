import java.util.*;
import java.io.*;
/**********************************************************************
*Name: Adivishva Mohan
*Class: LocationGraph
*
*
***********************************************************************/
public class locationGraph implements Serializable
{

	/**
	*variable: vertices, edges
	* vertices: 
	******/
	protected DSALinkedList vertices, edges;
	protected DSALinkedList details;  //Location details

	protected DSAHashTable hashedDetails; // hashed details of a location with its label
	
	/************************************************************8
	*Class: locationGraphVertex
	*Fields: label (String), visited (boolean), edges (DSALinkedList)
	*Purpose: To a have methods that handle information about the vertices
	*************************************************************/
	private class locationGraphVertex implements Serializable, Iterable
	{
		protected String label;
		protected boolean visited;
		protected DSALinkedList edges;
		
		/*******************************************************************
		* Method: locationGraphVertex
		* Import: nLabel (String)
		* Export: none
		* Purpose: This is a parameterised constructor
		********************************************************************/
		public locationGraphVertex(String nLabel) {
			if (nLabel == null)
			{
				throw new IllegalArgumentException("Error, label should NOT be null");
			}
			else
			{
				label = nLabel;
				edges = new DSALinkedList();
				visited = false;
			}
		}
		/************************************************8
		* Method: iterator
		* Import: none
		* Export: edges.iterator();
		*
		*
		*************************************************/
		public Iterator iterator()
		{
			return edges.iterator();
		}
		/************************************************8
		* Method: getLabel
		* Import: none
		* Export: label 
		* Purpose: getting a label of the vertex.
		*************************************************/
		public String getLabel() {
			return label;
		}
		/************************************************8
		* Method: getVisited
		* Import: none
		* Export: visited
		*
		*************************************************/
		public boolean getVisited()
		{
			return visited;
		}
		/************************************************8
		* Method: setVisited
		* Import: visited
		* Export: none
		*
		*
		*************************************************/
		public void setVisited(boolean visited)
		{
			this.visited = visited;
		}
		/************************************************8
		* Method: clearVisited
		* Import: none
		* Export: none
		*
		*
		*************************************************/
		public void clearVisited()  //clear the visits
		{
			this.visited = false;
		}
		/************************************************8
		* Method: isVisited
		* Import: none
		* Export: visited
		*
		*
		*************************************************/
		public boolean isVisited()
		{
			return visited == true;
		}
		/************************************************8
		* Method: setLabel
		* Import: nLabel (String)
		* Export: none
		*
		*
		*************************************************/
		public void setLabel(String nLabel)
		{
			label = nLabel;
		}
		/************************************************8
		* Method: getAdjacentEdge
		* Import: none
		* Export: egdes
		*
		*
		*************************************************/
		public DSALinkedList getAdjacentEdge()
		{
			return edges;
		}
		/************************************************8
		* Method: getAdjacent
		* Import: nLabel (String)
		* Export: vertexList
		*
		*
		*************************************************/
		public DSALinkedList getAdjacent(String nLabel)
		{
			DSALinkedList vertexList = new DSALinkedList();
			Iterator iterate = edges.iterator(); //Iterate through edges of graph
			while(iterate.hasNext())
			{
				locationGraphEdge edge = (locationGraphEdge) iterate.next();
				
				if (nLabel == edge.getFrom().getLabel())
				{
					vertexList.insertLast(edge.getTo());
				}
				else
				{
					vertexList.insertLast(edge.getFrom());
				}
			}
			return vertexList;
			
		}
		/************************************************8
		* Method: insertEdge
		* Import: edge (locationGraphEdge)
		* Export: none
		*
		*
		*************************************************/
		public void insertEdge(locationGraphEdge edge)
		{
			edges.insertLast(edge);
		}
		/************************************************8
		* Method: toString
		* Import: none
		* Export: label
		*
		*
		*************************************************/
		public String toString()
		{
			return label;
		}
		
	}
	/************************************************8
	* Class: locationGraphEdge 
	* Fields: from (locationGraphVertex), to (locationGraphVertex), value (Object), 
	* 	  label (String), directed (boolean)
	* Purpose: To store information about edges of the graph
	*
	*************************************************/
	private class locationGraphEdge implements Serializable
	{
		protected locationGraphVertex from;
		protected locationGraphVertex to;
		protected Object value;
		protected String label;
		protected boolean directed;
		/************************************************8
		* Method: locationGraphEdge
		* Import: fromV (locationGraphVertex), toV (locationGraphVertex), nLabel, (String), nValue (Object), nDirected (boolean)
		* Export: none
		*
		*
		*************************************************/
		public locationGraphEdge(locationGraphVertex fromV, locationGraphVertex toV, String nLabel, Object nValue, boolean nDirected)
		{
			from = fromV;
			to = toV;
			label = nLabel;
			value = nValue;
			directed = nDirected;
		}
		/************************************************8
		* Method: isDirected
		* Import: none
		* Export: directed
		*
		*
		*************************************************/
		public boolean isDirected()
		{
			return directed;
		}
		/************************************************8
		* Method: getLabel
		* Import: none
		* Export: label
		*
		*
		*************************************************/
		public String getLabel() {
			return label;
		}
		/************************************************8
		* Method: getValue
		* Import: none
		* Export: value
		*
		*
		*************************************************/
		public Object getValue() {
			return value;
		}
		/************************************************8
		* Method: getFrom
		* Import: none
		* Export: from
		*
		*
		*************************************************/
		public locationGraphVertex getFrom() {
			return from;
		}
		/************************************************8
		* Method: getTo
		* Import: none
		* Export: to
		*
		*
		*************************************************/
		public locationGraphVertex getTo() {
			return to;
		}
		/************************************************8
		* Method: setLabel
		* Import: nLabel (String)
		* Export: none
		*
		*
		*************************************************/
		public void setLabel(String nLabel)
		{
			label = nLabel;
		}
		/************************************************8
		* Method: setValue
		* Import: nValue (Object)
		* Export: none
		*
		*
		*************************************************/
		public void setValue(Object nValue)
		{
			value = nValue;
		}
		/************************************************8
		* Method: toString
		* Import: none
		* Export: toString
		*
		*
		*************************************************/
		public String toString()
		{
			String fromLabel = from.getLabel();
			String toLabel = to.getLabel();
			String toString = fromLabel + ".." + label + "[" + value + "].. " + toLabel;
			return toString;
		}
	}
	/************************************************8
	* Class: locationDetails
	* Fields: temperature (double), windSpeed (double), humidity(double), vertex(DSAGraphVertex), label (String) 
	* Purpose: To store details of each vertex such as the temperature, weather etc.
	*
	*
	*************************************************/
	private class locationDetails implements Serializable, Iterable
	{
		protected double temperature;
		protected double windSpeed;
		protected double humidity;
		protected String label;
		
		/************************************************8
		* Method: locationDetails
		* Import: newLabel (String), newTemperature (double), newWindSpeed (double), newHumidity (double)
		* Export: none
		*
		*
		*************************************************/
		public locationDetails(String newLabel , double newTemperature, double newWindSpeed, double newHumidity)
		{
			label = newLabel;
			
			temperature = newTemperature;
			windSpeed = newWindSpeed;
			humidity = newHumidity;
			
		}
		
		/************************************************8
		* Method: iterator
		* Import: none
		* Export: edges.iterator();
		*
		*
		*************************************************/
		public Iterator iterator()
		{
			return edges.iterator();
		}
		/************************************************8
		* Method: getTemperature
		* Import: none
		* Export: temperature
		*
		*
		*************************************************/
		public double getTemperature()
		{
			return temperature;
		}
		/************************************************8
		* Method: setTemperature
		* Import: nTemperature (double)
		* Export: none
		*
		*
		*************************************************/
		public void setTemperature(double nTemperature)
		{
			temperature = nTemperature;
		}
		/************************************************8
		* Method: getWindSpeed
		* Import: none
		* Export: windSpeed
		*
		*
		*************************************************/
		public double getWindSpeed()
		{
			return windSpeed;
		}
		/************************************************8
		* Method: setWindSpeed
		* Import: nWindSpeed (double)
		* Export: none
		*
		*
		*************************************************/
		public void setWindSpeed(double nWindSpeed)
		{
			windSpeed = nWindSpeed;
		}
		/************************************************8
		* Method: getHumidity
		* Import: none
		* Export: humidity
		*
		*
		*************************************************/
		public double getHumidity()
		{
			return humidity;
		}
		/************************************************8
		* Method: setHumidity
		* Import: nHumidity (double)
		* Export: none
		*
		*
		*************************************************/
		public void setHumidity(double nHumidity)
		{
			humidity = nHumidity;
		}
		/************************************************8
		* Method: getLabel
		* Import: none
		* Export: nLabel
		*
		*
		*************************************************/
		public String getLabel()
		{
			return label;
		}
		/************************************************8
		* Method: setLabel
		* Import: nLabel String
		* Export: none
		*
		*
		*************************************************/
		public void setLabel(String nLabel)
		{
			label = nLabel;
		}
	}
	/*******************************************************************
	* Method Name: locationGraph
	* Import: none
	* Export: none
	* Purpose: A constructor of locationGraph class that initialize the fields
	********************************************************************/
	public locationGraph()
	{
		vertices = new DSALinkedList();
		edges = new DSALinkedList();
		details = new DSALinkedList();
		hashedDetails = new DSAHashTable();
		hashedDetails.DSAHashTable(26);
	}
	/*******************************************************************
	* Method Name: insertVertex
	* Import: nLabel (String)
	* Export: none
	* Purpose: To insert vertex
	********************************************************************/
	public void insertVertex(String nLabel)
	{
		if(!existVertex(nLabel))
		{
			locationGraphVertex newVertex = new locationGraphVertex(nLabel); //Creating a new vertex with nLabel
			vertices.insertLast(newVertex); //Adding the above vertex into vertices(vertex list)
		}
		else
		{
			throw new IllegalArgumentException("Error, vertex already exists cannot insert again or choose another label");
		}
	}
	/*******************************************************************
	* Method Name: insertDetails
	* Import: label(String), vert(DSAGraphVertex), nTemp(double), nWindSpeed(double), nHumidity(double)
	* Export: none
	* Purpose: To add vertex details into the details bar
	********************************************************************/
	public void insertDetails(String label, double nTemp, double nWindSpeed, double nHumidity)
	{
		locationDetails newDetails = new locationDetails(label, nTemp, nWindSpeed, nHumidity);
		details.insertLast(newDetails);
		hashedDetails.put(label, newDetails);
	}
	
	/*******************************************************************
	* Method Name: displayDetails
	* Import: none
	* Export: none
	* Purpose: to display details of each vertex
	********************************************************************/
	public void displayDetails()
	{
		locationDetails vertex = null;
		Iterator iterate = details.iterator();
		
		System.out.println("Vertex temperature windSpeed humidity");
		while (iterate.hasNext())
		{
			vertex = (locationDetails) iterate.next();
			
			String vert = vertex.getLabel();
			double temp = vertex.getTemperature();
			double windSpeed = vertex.getWindSpeed();
			double humidity = vertex.getHumidity();
			System.out.println(vert + " :     " + temp + "   ,   " + windSpeed + "   ,   " + humidity);
		
		}
	
	}
	/*******************************************************************
	* Method Name: exist
	* Import: label(String)
	* Export: getVertex(label) != null (boolean)
	* Purpose: check if the vertex exist
	********************************************************************/
	public boolean existVertex(String label)
	{
		return getVertex(label) != null;
	}
	
	/*******************************************************************
	* Method Name: getVertexCount
	* Import: none
	* Export: vertices.getCount()
	* Purpose: To get number of vertices
	********************************************************************/
	public int getVertexCount()
	{
	
		return vertices.getCount();
	}
	
	/*******************************************************************
	* Method Name: getEdgeCount
	* Import: none
	* Export: edges.getCount
	* Purpose: To get number of edges
	********************************************************************/
	public int getEdgeCount()
	{
		
		return edges.getCount();
	}

	/*******************************************************************
	* Method Name: getEdge
	* Import: label(String)
	* Export: edge (locationGraphEdge)
	* Purpose: finding the edge of particular vertex
	********************************************************************/
	public locationGraphEdge getEdge(String label)
	{
		Iterator iterate = edges.iterator();
		locationGraphEdge edge = null;
		do{
			locationGraphEdge currentEdge = (locationGraphEdge) iterate.next();
			if (currentEdge.getLabel().equals(label))
			{
				edge = currentEdge;
			}
		}while ((edge == null)&&(iterate.hasNext()));
		
		return edge;
	}
	/*******************************************************************
	* Method Name: insertEdge
	* Import: l1 (String), l2 (String)
	* Export: none
	* Purpose: inserting edge with 2 parameters
	********************************************************************/
	public void insertEdge(String l1, String l2)
	{
		insertEdge(l1, l2, null, null, false);
	}
	/*******************************************************************
	* Method Name: insertEdge
	* Import: l1 (String), l2 (String), value (Object)
	* Export: none
	* Purpose: inserting edge with 3 parameters
	********************************************************************/
	public void insertEdge(String l1, String l2, Object value)
	{
		insertEdge(l1, l2, null, value, false);
	}
	/*******************************************************************
	* Method Name: insertEdge
	* Import: l1 (String), l2 (String), edgeLabel (String), value (Object)
	* Export: none
	* Purpose: inserting edge with 4 parameters
	********************************************************************/
	public void insertEdge(String l1, String l2, String edgeLabel, Object value)
	{
		insertEdge(l1, l2, edgeLabel, value, false);
	}
	/*******************************************************************
	* Method Name: insertEdge
	* Import: l1 (String), l2 (String), edgeLabel (String), value (Object), directed (boolean)
	* Export: none
	* Purpose: inserting edge with 5 parameters
	********************************************************************/
	public void insertEdge(String l1, String l2, String edgeLabel, Object value, boolean directed)
	{
		
		locationGraphVertex fromV = getVertex(l1);
		locationGraphVertex toV = getVertex(l2);
		
		if (fromV == null)
		{
			throw new IllegalArgumentException("Error vertex fromV is null");
		}
		
		if (toV == null)
		{
			throw new IllegalArgumentException("Error vertex ToV is null");
		}
		
		locationGraphEdge edge = new locationGraphEdge(fromV, toV, edgeLabel, value, directed);
		
		edges.insertLast(edge);
		fromV.insertEdge(edge);
		toV.insertEdge(edge);
	}
	/*******************************************************************
	* Method Name: getAdjacent
	* Import: label (String)
	* Export: adjacentList (DSALinkedList)
	* Purpose: getting adjacent vertices of a particular vertex
	********************************************************************/
	public DSALinkedList getAdjacent(String label)
	{
		locationGraphVertex vertex = getVertex(label);
		DSALinkedList adjacentList = null;
		
		if (vertex == null)
		{
			System.out.println("Error vertex is not there");
		}
		else
		{
			adjacentList = new DSALinkedList();
			Iterator iterate = vertex.getAdjacentEdge().iterator();
			
			while (iterate.hasNext())
			{
				locationGraphEdge edge = (locationGraphEdge) iterate.next();
				locationGraphVertex toVertex = edge.getTo();
				adjacentList.insertLast(toVertex.getLabel());
			}
		}
		
		return adjacentList;
	}
	/*****************************************************
	* Method Name: getVertex
	* Import: nLabel (String)
	* Export: vertex (locationGraphVertex)
	******************************************************/
	
	public locationGraphVertex getVertex(String nLabel)
	{
		Iterator iterate = vertices.iterator();
		locationGraphVertex vertex = null;
		
		while (iterate.hasNext())
		{
			
			
			locationGraphVertex current = (locationGraphVertex) iterate.next();
			
			if ((current.getLabel()).equals(nLabel))
			{
	
				vertex = current;
			}
		}
		
		return vertex;
	}
	
	/*****************************************************
	* Method Name: isAdjacent
	* Import: l1 (String), l2 (String)
	* Export: adjacent (boolean)
	******************************************************/
	public boolean isAdjacent(String l1, String l2) {
		locationGraphVertex fromV = null;
		locationGraphVertex toV = null;
		
		fromV = getVertex(l1);
		toV = getVertex(l2);
		
		DSALinkedList adjacentList = fromV.getAdjacentEdge();
		
		Iterator iterate = adjacentList.iterator();
		boolean adjacent = false;
		if(fromV != null && toV != null)
		{
			while (iterate.hasNext() && (!adjacent))
			{
				locationGraphEdge link = (locationGraphEdge) iterate.next();
				if ((link.getTo().getLabel()).equals(toV.getLabel())) 
				{
					adjacent = true;
				}
				
			}
		}
		
		return adjacent;
	}
	/*****************************************************
	* Method Name: listDisplay
	* Import: none
	* Export: none
	* Purpose: To display adjacency list of the graph
	******************************************************/
	public void listDisplay()
	{
		locationGraphVertex vertex = null;
		locationGraphVertex vertex2 = null;
		Iterator iterate = vertices.iterator();
		Iterator adjLinks = null;
		
		while (iterate.hasNext())
		{
			vertex = (locationGraphVertex) iterate.next();
			vertex.clearVisited();
		}
		
		iterate = vertices.iterator();
		while (iterate.hasNext())
		{
		
			vertex = (locationGraphVertex) iterate.next();
			
			
			DSALinkedList adjacentList = vertex.getAdjacent(vertex.getLabel()); //Already got the adjacent vertices don't need to go over which is adjacent vertex and edges.
			
			System.out.print(vertex.getLabel() + ": ");
			
			adjLinks = adjacentList.iterator(); //vertices that are linked to the vertex
			
			while (adjLinks.hasNext())
			{
				vertex2 = (locationGraphVertex) adjLinks.next(); //Vertex 2 is going to be adjacent to vertex 
				
				if (!vertex2.getVisited())
				{
					System.out.print(vertex2.getLabel() + " ,");
					vertex2.setVisited(true);
				}
				
			}
			System.out.println("");
			
			adjLinks = adjacentList.iterator();
			while(adjLinks.hasNext())
			{
				vertex2 = (locationGraphVertex) adjLinks.next(); 
				vertex2.clearVisited();
			}	
		}
	}
	/*****************************************************
	* Method Name: printAllVertices
	* Import: none
	* Export: none
	* Purpose: To print all vertices
	******************************************************/
	public void printAllVertices()
	{
		locationGraphVertex vertex = null;
		Iterator iterate = vertices.iterator();
		Iterator adjLinks = null;
		
		while (iterate.hasNext())
		{
			vertex = (locationGraphVertex) iterate.next();
			
			System.out.println(vertex.getLabel());
	
		}
	}
		
	/*****************************************************
	* Method Name: matrixDisplay
	* Import: none
	* Export: none
	* Purpose: To display adjacency matrix of vertices and edges of graph
	******************************************************/
	public void matrixDisplay()
	{
		int count = getVertexCount();
		String[][] matrix = new String[count+1][count+1];
		Iterator iterate1 = null;
		Iterator iterate2 = null;
		int n = 0;
		
		locationGraphVertex v1, v2;
		iterate1 = vertices.iterator();
		
		

		for(int i = 0; i < count+1; i++)
		{
			for(int j = 0; j < count+1; j++)
			{
				
				matrix[i][j] = " ";
			}
		}
		while(n < count)
		{
			n++;
			v1 = (locationGraphVertex)iterate1.next();
			matrix[0][n] = v1.getLabel();
			matrix[n][0] = v1.getLabel();		
		}
		
		iterate1 = vertices.iterator();
		
		for(int i = 1; i <= count; i++)
		{
			v1 = (locationGraphVertex)iterate1.next();
			iterate2 = v1.iterator();
			while(iterate2.hasNext())
			{
				locationGraphEdge edge = (locationGraphEdge)iterate2.next();
				if(v1 == edge.getFrom())
				{
					v2 = edge.getTo();
				}
				else
				{
					v2 = edge.getFrom();
				}
				for(int j = 1; j <= count; j++)
				{
					if(v2.getLabel().equals(matrix[0][j]))
					{
						matrix[i][j] = "1";
					}
				
				}
				
			}
		}
		
		for(int i = 0; i <= count; i++)
		{
			for(int j = 0; j <= count; j++)
			{
				System.out.print(matrix[i][j] +" ");
			}
			System.out.println(" ");
		}
		
	}
	
	/*****************************************************
	* Method Name: breadthFirstSearch
	* Import: initialLabel (String), destLabel (String)
	* Export: T (DSALinkedQueue)
	* Purpose: To perform a breadthFirstSearch which is a searching algorithm that checks all vertices adjacent to each other
	******************************************************/
	public DSALinkedQueue breadthFirstSearch(String initialLabel, String destLabel)
	{
		if(!existVertex(initialLabel))
		{
			throw new IllegalStateException("Vertex does not exist to perform the search breadthFirstSearch");
		}
		DSALinkedQueue T = new DSALinkedQueue();
		DSALinkedQueue Q = new DSALinkedQueue();
		
		DSAHashTable hashMap = new DSAHashTable();
		hashMap.DSAHashTable(25);
		
		Iterator iterate = vertices.iterator();
		
		while(iterate.hasNext())
		{
			((locationGraphVertex) iterate.next()).clearVisited();
		}
		
		locationGraphVertex startVertex = getVertex(initialLabel);
		iterate = startVertex.getAdjacent(startVertex.getLabel()).iterator();
		
		locationGraphVertex v = startVertex; 

		int[] distance = new int[26]; // distance from initial label
		for(int i=0;i<26;i++) distance[i] = (int)1e9 + 10; // = infinity / not reachable from A
		DSAStack path = new DSAStack();

		if (v != null)
		{
			distance[initialLabel.charAt(0) - 'A'] = 0;
			v.setVisited(true);
			Q.enqueue(v);
		}
		while(!Q.isEmpty())
		{
			locationGraphVertex removeVertex = (locationGraphVertex) Q.dequeue(); //removeVertex is the parent
			T.enqueue(removeVertex);
			for (Object j : removeVertex.getAdjacent(removeVertex.getLabel())) // j is the key
			{
				locationGraphVertex k = (locationGraphVertex) j;
				if(k.getVisited() == false)
				{
					distance[k.getLabel().charAt(0) - 'A'] = 1 + distance[removeVertex.getLabel().charAt(0) - 'A'];
					path.push(removeVertex.getLabel() + " -> " + k.getLabel());

					k.setVisited(true);
					Q.enqueue(k);
					hashMap.put(k.getLabel(), removeVertex.getLabel());
					if(k.getLabel().equals(destLabel))
					{
						break;
					}
				}
			}
		}

		//DSALinkedQueue path =  new DSALinkedQueue();
		//String node = destLabel;
		//while(!node.equals(null))
		//{
			
		//}
		
		DSAStack shortestPath = new DSAStack();
		char dest = destLabel.charAt(0);
		while(! path.isEmpty()){
			String edge = (String) path.pop();
			char curSrc = edge.charAt(0); char curDest = edge.charAt(5);
			if(curDest == dest){
				shortestPath.push(dest); dest = curSrc;
			}
		}
		shortestPath.push(initialLabel.charAt(0));

		String reqPath = "";
		while(! shortestPath.isEmpty()){
			char ver = (char) shortestPath.pop();
			reqPath += ver;
			if(! shortestPath.isEmpty()) reqPath += " -> ";
		}
		System.out.print("Shortest Path between " + initialLabel + " and " + destLabel + " : " + reqPath + "\n");

		System.out.print("Shortest Distance between " + initialLabel + " and " + destLabel + " is " + distance[destLabel.charAt(0) - 'A'] + "\n");
		return T;
	}
	
	/*****************************************************
	* Method Name: depthFirstSearch
	* Import: initialLabel (String)
	* Export: T (DSALinkedQueue)
	* Purpose: To perform a depthFirstSearch which is a searching algorithm that traverses the entire graph
	******************************************************/
	public DSALinkedQueue depthFirstSearch(String initialLabel)
	{
		if(!existVertex(initialLabel))
		{
			throw new IllegalArgumentException("Vertex does not exist to perform the search DepthFirstSearch");
		}
		
		DSAStack S = new DSAStack();
		DSALinkedQueue T = new DSALinkedQueue();
		
		DSALinkedList dupliVert = new DSALinkedList();
		Iterator iter = vertices.iterator();
		
		while(iter.hasNext())
		{
			locationGraphVertex vert = (locationGraphVertex) iter.next();
			locationGraphVertex newVert = new locationGraphVertex(vert.getLabel());
			dupliVert.insertLast(newVert); 
		}
		
		Iterator iterate = dupliVert.iterator();
		
		while(iterate.hasNext())
		{
			((locationGraphVertex) iterate.next()).clearVisited();
		}
		
		locationGraphVertex startVertex = getVertex(initialLabel);
		iterate = startVertex.getAdjacent(initialLabel).iterator();
		
		locationGraphVertex v = startVertex; //(DSAGraphVertex) iterate.next();
		if (v != null)
		{
			v.setVisited(true);
			S.push(v);
			
			while(!S.isEmpty())
			{	
				v = (locationGraphVertex)S.top();
				locationGraphVertex w = getNextUnvisited(v);
				
				while(w != null)
				{
					
					T.enqueue(v.getLabel() + w.getLabel());
					
					w.setVisited(true);
					S.push(w);
					v = w;
					
					w = getNextUnvisited(v);
					
					
				}
				
				
				v = (locationGraphVertex) S.pop();
			}
			
		}
		return T;
	}
	
	/*****************************************************
	* Method Name: getNextUnvisited
	* Import: (locationGraphVertex) input
	* Export: output (locationGraphVertex)
	* Purpose: To get the next unvisited vertex after the visited vertex.
	******************************************************/
	public locationGraphVertex getNextUnvisited(locationGraphVertex input)
	{
		Iterator iterate = input.getAdjacent(input.getLabel()).iterator();
		locationGraphVertex output = null;
		
		while ((iterate.hasNext())&&(output == null))
		{
			locationGraphVertex adjacent = (locationGraphVertex) iterate.next();
			
			if (!adjacent.getVisited())
			{
				output = adjacent;
			}
		}
		return output;
	}

	/***************************************
	*	Method Name: getDetails
	*	Import: label (String)
	*	Output: getDetails (locationDetails)
	*	Purpose: To get the locationDetails of a particular location efficiently.
	****************************************/
	public locationDetails getDetails(String label)
	{
		return (locationDetails) hashedDetails.get(label);
	}

	/***************************************
	*	Method Name: displayDetailsByLocation
	*	Import: label (String)
	*	Purpose: To display the details of a particular location efficiently.
	****************************************/
	public void displayDetailsByLocation(String label)
	{
		locationDetails det = getDetails(label);
		System.out.println("Location Details of " + label + " are: ");
		System.out.println("Temperature: " + det.getTemperature());
		System.out.println("WindSpeed: " + det.getWindSpeed());
		System.out.println("Humidity: " + det.getHumidity());
	}
}
/*************************************************************
* References
* (1) Lecture 6: Graphs.(no author ,n.d).COMP1002 DATA STRUCTURES AND ALGORITHMS
* Blackboard. https://lms.curtin.edu.au/
* DSAGraph.java.(Adivishva, 2023).Practical 6
* Retrieved from : p6.zip
*
*
**************************************************************/

