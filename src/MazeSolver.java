import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazeSolver {
	
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
