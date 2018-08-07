import java.util.Queue;
//class implementing queue interface
import java.util.LinkedList;

//This implementation should used an adjacency matrix.
public class Graph implements IGraph {

	//array for storing the set of vertices (we suppose a fix number of vertices)
	//suppose the vertices are String objects.
	public String[] vertices;
	//number of vertices in the graph
	int numVertices;
	
	//matrix cannot be float, because float does not allow to store null
	Integer[][] matrix;
	//directed or not
	boolean directed;
	
	
	public Graph(String[] vertices) {
		this.vertices = vertices;
		numVertices = vertices.length;
		matrix = new Integer[numVertices][numVertices];
		directed = false;
	}
	
	
	//check if i is a right vertex and returns its name
	private String checkVertex(int i) {
		//i has to be positive and lower than numVertices
		if (i>=0 && i<numVertices) return vertices[i];
		else return null;
	}
	
	
	
	
	public int sizeVertices() {
		//returns the number of vertices
		return numVertices;
	}

	//return the number of edges
	public int sizeEdges() {
		int numEdges=0;
		if (directed) {
			for (int i=0;i<numVertices;i++) {
				for (int j=0;j<numVertices;j++) {
					if (matrix[i][j]!=null) numEdges++;
				}	
			}
		} else {
			//we only have to visit the half of the matrix (because it is symmetric)
			for (int i=0;i<numVertices;i++) {
				for (int j=i;j<numVertices;j++) {
					if (matrix[i][j]!=null) numEdges++;
				}	
			}
		}
		return numEdges;
	}

	@Override
	public int getDegree(int i) {
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return -1;
		}
		
		int degree=0;
		
		if (directed) degree=getInDegree(i)+getOutDegree(i); 
		else {
			//we could count the number of rows or columns that have an edge with i
			//in this implementation, we count the number of rows
			for (int row=0;row<numVertices;row++) {
				if (matrix[row][i]!=null) degree++;
			}
		}
		return degree;
	}

	@Override
	public int getInDegree(int i) {
		//a non directed graph do not have getInDegree
		if (!directed) {
			System.out.println("Graph non directed!!!");
			return 0;
		}
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return -1;
		}
		//count the number of rows that have an edge with i
		int indeg=0;
		for (int row=0;row<numVertices;row++) {
			if (matrix[row][i]!=null) indeg++;
		}
		
		return indeg;
	}

	public int getOutDegree(int i) {
		//a non directed graph do not have getOutDegree
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return -1;
		}
		
		
		int outdeg=0;
		//counts the columns that have an edge with i
		for (int col=0;col<numVertices;col++) {
			if (matrix[i][col]!=null) outdeg++;
		}
		return outdeg;
	}

	

	@Override
	public void addEdge(int i, int j, int w) {
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return ;
		}
		if (checkVertex(j)==null) {
			System.out.println("Nonexistent vertex  " + j);
			return ;
		}
		
		matrix[i][j]=w;
		//if is non directed, we also have to save the symmetric edge
		if (!directed) matrix[j][i]=w;
	}

	@Override
	public void removeEdge(int i, int j) {
		//checks if the indexes are right
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return;
		}
		if (checkVertex(j)==null) {
			System.out.println("Nonexistent vertex  " + j);
			return;
		}
		
		//updates the value to null
		matrix[i][j]=null;
		//if is non directed, we also have to remove the symmetric edge
		if (!directed) matrix[j][i]=null;
		
	}
	
	

	@Override
	public boolean isEdge(int i, int j) {
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return false;
		}
		if (checkVertex(j)==null) {
			System.out.println("Nonexistent vertex  " + j);
			return false;
		}
		
		//checks if there is an edge between i and j
		if (matrix[i][j]==null) return false;
		else return true;
	}

	@Override
	public Integer getWeightEdge(int i, int j) {
		//checks if the indexes are right
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return null;
		}
		if (checkVertex(j)==null) {
			System.out.println("Nonexistent vertex  " + j);
			return null;
		}
		
		//gets the value saved in matrix[i,j]. This value could be null.
		Integer result= matrix[i][j];
		return result;
	}

	public void show() {
		//visits the rows
		for (int i=0; i<numVertices;i++) {
			//for each row, visits all columns
			for (int j=0; j<numVertices;j++) {
				//prints the element in matrix[i,j] and a space
				System.out.print(matrix[i][j]+"\t");
			}
			//new line for the new row
			System.out.println();
		}
		System.out.println();

	}
	
	//returns an array with the adjacent vertices for i
	public int[] getAdjacents(int i) {
		if (checkVertex(i)==null) {
			System.out.println("Nonexistent vertex  " + i);
			return null;
		}
		
		//obtains the number of adjacent vertices,
		//which will be the size of the array
		int numAdjacents=0;
		if (directed) numAdjacents=getOutDegree(i);
		else numAdjacents=getDegree(i);
		
		int[] adjacents=new int[numAdjacents];
		
		if (numAdjacents>0) {
			int j=0;
			//gets the edges (i,col) and saves col into adjacents
			for (int col=0; col<numVertices;col++) {
				if (matrix[i][col]!=null) {
					adjacents[j]=col;
					j++;
				}
			}
		}
		//return an array with the adjacent vertices of i
		return adjacents;
	}
	
	public void breadth() {
		System.out.println("breadth traverse of the graph:");
		
		//to mark when a vertex has already been shown
		boolean visited[]=new boolean[numVertices];

		//we have to traverse all vertices
		for (int i=0;i<numVertices;i++) {
			if (!visited[i]) { //we only process the non-visited vertex
				breadth(i,visited);
			}
		}
		System.out.println();
	}
	
	

	//breadth order for the vertex i 
	protected void breadth(int i, boolean[] visited) {
		//this array helps to mark what vertices have been stored into the queue
		boolean stored[]=new boolean[numVertices];
		//System.out.println("breadth traverse for " + i);
		//we use a queue to save the adjacent vertices that we visit
		Queue<Integer> q=new LinkedList<Integer>();
		//enqueue the first
		q.add(i); //similar to enqueue
		//while the queue is not empty
		while (!q.isEmpty()) {
			//gets the first, poll() is similar dequeue()
			int vertex=q.poll();
			//shows the vertex and marks it as visited
			System.out.print(vertex+"\t");
			visited[vertex]=true;
			//gets its adjacent vertices
			int[] adjacents=getAdjacents(vertex);
			for(int adjVertex:adjacents) {
					//enqueue only those that have not been visited or stored yet 
					if (!visited[adjVertex] && !stored[adjVertex]) {
						q.add(adjVertex);
						stored[adjVertex]=true;
					}
			}
		}
	}
	
	public void depth() {
		System.out.println("depth traverse of the graph:");
		//to mark when a vertex has already been shown
		boolean visited[]=new boolean[numVertices];
		//we have to traverse all vertices
		for (int i=0;i<numVertices;i++) {
			if (!visited[i]) depth(i,visited);
		}
		System.out.println();
	}

	protected void depth(int i,boolean[] visited) {
		//prints the vertex and marks as visited
		System.out.print(i+"\t");
		visited[i] = true;
		//gets its adjacent vertices
		int[] adjacents = getAdjacents(i);
		for (int adjV : adjacents) {
			if (!visited[adjV]) {
				//only depth traverses those adjacent vertices 
				//that have not been visited yet
				depth(adjV,visited);
			}
		}
	}
	
	
	
}
