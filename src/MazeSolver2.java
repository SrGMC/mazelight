import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

public class MazeSolver2 {
	
	public class Vertex implements Comparable<Vertex> {
		 
		private String name;
		private List<Edge> adjacenciesList;
		private boolean visited;
		private Vertex predecessor;
		private double distance = Double.MAX_VALUE;
	 
		public Vertex(String name) {
			this.name = name;
			this.adjacenciesList = new ArrayList<>();
		}
	 
		public void addNeighbour(Edge edge) {
			this.adjacenciesList.add(edge);
		}
	 
		public String getName() {
			return name;
		}
	 
		public void setName(String name) {
			this.name = name;
		}
	 
		public List<Edge> getAdjacenciesList() {
			return adjacenciesList;
		}
	 
		public void setAdjacenciesList(List<Edge> adjacenciesList) {
			this.adjacenciesList = adjacenciesList;
		}
	 
		public boolean isVisited() {
			return visited;
		}
	 
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	 
		public Vertex getPredecessor() {
			return predecessor;
		}
	 
		public void setPredecessor(Vertex predecessor) {
			this.predecessor = predecessor;
		}
	 
		public double getDistance() {
			return distance;
		}
	 
		public void setDistance(double distance) {
			this.distance = distance;
		}
	 
		@Override
		public String toString() {
			return this.name;
		}
	 
		@Override
		public int compareTo(Vertex otherVertex) {
			return Double.compare(this.distance, otherVertex.getDistance());
		}
	}
	
	public class Edge {
		 
		private double weight;
		private Vertex startVertex;
		private Vertex targetVertex;
		
		public Edge(double weight, Vertex startVertex, Vertex targetVertex) {
			this.weight = weight;
			this.startVertex = startVertex;
			this.targetVertex = targetVertex;
		}
	 
		public double getWeight() {
			return weight;
		}
	 
		public void setWeight(double weight) {
			this.weight = weight;
		}
	 
		public Vertex getStartVertex() {
			return startVertex;
		}
	 
		public void setStartVertex(Vertex startVertex) {
			this.startVertex = startVertex;
		}
	 
		public Vertex getTargetVertex() {
			return targetVertex;
		}
	 
		public void setTargetVertex(Vertex targetVertex) {
			this.targetVertex = targetVertex;
		}
	}
	 
	public class DijkstraShortestPath {
	 
		public void computeShortestPaths(Vertex sourceVertex){
	 
			sourceVertex.setDistance(0);
			PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
			priorityQueue.add(sourceVertex);
			sourceVertex.setVisited(true);
	 
			while( !priorityQueue.isEmpty() ){
	            // Getting the minimum distance vertex from priority queue
				Vertex actualVertex = priorityQueue.poll();
	 
				for(Edge edge : actualVertex.getAdjacenciesList()){
	 
					Vertex v = edge.getTargetVertex();
					if(!v.isVisited())
					{
						double newDistance = actualVertex.getDistance() + edge.getWeight();
	 
						if( newDistance < v.getDistance() ){
							priorityQueue.remove(v);
							v.setDistance(newDistance);
							v.setPredecessor(actualVertex);
							priorityQueue.add(v);
						}
					}
				}
				actualVertex.setVisited(true);
			}
		}
	 
		public List<Vertex> getShortestPathTo(Vertex targetVertex){
			List<Vertex> path = new ArrayList<>();
	 
			for(Vertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
				path.add(vertex);
			}
	 
			Collections.reverse(path);
			return path;
		}
	 
	}
	
	public static void printMaze(int[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if(maze[i][j] == 1) {
					System.out.print("■" + " ");
				} else if(maze[i][j] == 2) {
					System.out.print("N" + " ");
				} else if(maze[i][j] == 3) {
					System.out.print("S" + " ");
				} else {
					System.out.print(" " + " ");
				}
			}
			System.out.println("");
		}
	}
	
	public static void printMaze(Cell[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if(maze[i][j].isWall()) {
					System.out.print("■" + " ");
				} else if(maze[i][j].isSpecial()) {
					System.out.print("S" + " ");
				} else if(maze[i][j].isNode()) {
					System.out.print("N" + " ");
				} else {
					System.out.print(" " + " ");
				}
			}
			System.out.println("");
		}
	}
	
	public static Graph processMaze(String filePath, boolean print) throws IOException {
		System.out.println("Processing nodes...");
		
		int vertex = 0;
		
		//Gets image file
		File file = new File(filePath);
	    BufferedImage img = ImageIO.read(file);
	    
	    //Converts to B&W image
	    BufferedImage bw = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	    Graphics2D graphics = bw.createGraphics();
	    graphics.drawImage(img, 0, 0, null);
	    
	    //Converts into integer matrix
		int[][] pending = new int[bw.getHeight()][bw.getWidth()];
		//i is y, j is x
	    for (int i = 0; i < pending.length; i++) {
			for (int j = 0; j < pending[i].length; j++) {
				if(bw.getRGB(j, i) == -16777216) {
					pending[i][j] = 1;
				} else {
					pending[i][j] = 0;
				}
			}
		}
	    
	    Cell[][] table = new Cell[pending.length][pending[0].length];
		int[][] maze = new int[pending.length][pending[0].length];
		
		//Set Walls
		for (int i = 0; i < pending.length; i++) {
			for (int j = 0; j < pending[i].length; j++) {
				maze[i][j] = pending[i][j];
				if(pending[i][j] == 1) {
					table[i][j] = new Cell(1, vertex);
				}
			}
		}
			
		//Generate nodes
		for (int i = 1; i < pending.length-1; i++) {
			for (int j = 1; j < pending[i].length-1; j++) {
				if(pending[i][j] == 1){																						//Ignores walls
					//Nothing
				} else if(pending[i+1][j] == 1 && pending[i-1][j] == 0 && pending[i][j+1] == 0 && pending[i][j-1] == 0) {	//Recognizes intersection nodes
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				} else if(pending[i+1][j] == 0 && pending[i-1][j] == 1 && pending[i][j+1] == 0 && pending[i][j-1] == 0) {
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				} else if(pending[i+1][j] == 0 && pending[i-1][j] == 0 && pending[i][j+1] == 1 && pending[i][j-1] == 0) {
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				} else if(pending[i+1][j] == 0 && pending[i-1][j] == 0 && pending[i][j+1] == 0 && pending[i][j-1] == 1) {
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				} else if(pending[i+1][j] == 0 && pending[i-1][j] == 0 && pending[i][j+1] == 0 && pending[i][j-1] == 0) {	//Recognices crossing paths
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				} else if(pending[i+1][j] == 0 && pending[i-1][j] == 0 || pending[i][j+1] == 0 && pending[i][j-1] == 0) {	//Recognizes empty spaces
					maze[i][j] = 0;
					table[i][j] = new Cell(0, vertex);
				} else {																										//Fallback
					maze[i][j] = 2;
					table[i][j] = new Cell(2, vertex);
					vertex++;
				}
			}
		}
		
		//Find exit and start points
		for (int i = 0; i < pending.length; i++) {
			for (int j = 0; j < pending[i].length; j++) {
				if(i == 0 || i == (pending.length-1) || j == 0 || j == (pending[i].length-1)) {
					if(pending[i][j] == 0) {
						//Set to start/exit point
						 maze[i][j] = 3;
						table[i][j] = new Cell(3, vertex);
						vertex++;
						/*Add node above, below or to the sides*/
						if(i == 0) {
							 maze[i+1][j] = 2;
							table[i+1][j] = new Cell(2, vertex);
							vertex++;
						} else if(i == (pending.length-1)) {
							 maze[i-1][j] = 2;
							table[i-1][j] = new Cell(2, vertex);
							vertex++;
						}
								
						if(j == 0) {
							 maze[i][j+1] = 2;
							table[i][j+1] = new Cell(2, vertex);
							vertex++;
						} else if(j == (pending[i].length-1)) {
							 maze[i][j-1] = 2;
							table[i][j-1] = new Cell(2, vertex);
							vertex++;
						}
					}
				}
			}
		}
		
		String[] vertices = new String[vertex];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = i + "";
		}
		
		Graph graph = new Graph(vertices);
		
		System.out.println("Processing edges...");
		
		//Get Links
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if(table[i][j].isNode()) {
					boolean stop = false;
					int i2 = i, j2 = j-1;
					//Link Left
					while(!stop && j2 >= 0 ) {
						if(table[i][j2].isWall()) stop = true;
						else if(table[i][j2].isEmpty()) j2--;
						else if(table[i][j2].isNode()){
							graph.addEdge(table[i][j].getVertex(), table[i][j2].getVertex(), j-j2);
							stop = true;
						}
					}
					stop = false;
					i2 = i; j2 = j+1;
					//Link Right
					while(!stop && j2 <= table[i].length-1) {
						if(table[i][j2].isWall()) stop = true;
						else if(table[i][j2].isEmpty()) j2++;
						else if(table[i][j2].isNode()){
							graph.addEdge(table[i][j].getVertex(), table[i][j2].getVertex(), j2-j);
							stop = true;
						}
					}
					stop = false;
					i2 = i+1; j2 = j;
					//Link bottom
					while(!stop && i2 <= table.length-1) {
						if(table[i2][j].isWall()) stop = true;
						else if(table[i2][j].isEmpty()) i2++;
						else if(table[i2][j].isNode()){
							graph.addEdge(table[i][j].getVertex(), table[i2][j].getVertex(), i2-i);
							stop = true;
						}
					}
					stop = false;
					i2 = i-1; j2 = j;
					//Link top
					while(!stop && i2 >= 0) {
						if(table[i2][j].isWall()) stop = true;
						else if(table[i2][j].isEmpty()) i2--;
						else if(table[i2][j].isNode()){
							graph.addEdge(table[i][j].getVertex(), table[i2][j].getVertex(), i-i2);
							stop = true;
						}
					}
				}
				
			}
		}
		
		if (print) {
			printMaze(table);
		}
		
		return graph;

	}
	
	public static void main(String[] args) throws IOException {
		//if(args.length == 0) {
		//	System.out.println("No image file specified. Use: MazeSolver.jar filename.png\nExiting");
		//	System.exit(2);
		//}
	    Graph maze = processMaze("mazes/maze1.png", true);
	    System.out.println("");
	    System.exit(0);
	}
}
