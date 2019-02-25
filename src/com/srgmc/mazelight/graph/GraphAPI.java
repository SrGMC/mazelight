package com.srgmc.mazelight.graph;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

import com.srgmc.mazelight.ImageTools;
import com.srgmc.mazelight.Mazelight;
import com.srgmc.mazelight.Position;
import com.srgmc.mazelight.Relative;
import com.srgmc.mazelight.Mazelight.ALGO;

public class GraphAPI {
    private static LinkedList<Position> nodes = new LinkedList<Position>();
    
    /**
     * Solves a maze given from an input image
     * @param input Path of the image to solve
     * @param output Path of the solved image
     * @param algo Algorithm to use
     * @return true or false, depending on the result of the execution
     * @throws IOException
     * @throws Exception
     */
    public static Relative solve(String input, String output, ALGO algo) throws IOException, Exception  {
    	Graph maze = GraphAPI.bufferToGraph(ImageTools.imageToBuffer(input));
    	switch (algo) {
    		case ASTAR:
    			return GraphAPI.aStar(maze);
    		default:
    			return null;
    	}
    }

    /**
     * Check if the given position is a node
     * @param pos Position to check
     * @return true or false
     */
    private static boolean isNode(LinkedList<Position> list, Position pos){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(pos)){
                return true;
            }
        }
        return false;
    }
    /**
     * Converts a BufferedImage object into a Graph object
     * @param img {@link BufferedImage} to convert
     * @return Graph object
     * @throws IOException
     * @throws Exception
     */
    public static Graph bufferToGraph(BufferedImage img) throws IOException, Exception{
        Graph graph = new Graph();

        //Node finder
        for(int i = 1; i < img.getWidth()-1; i++){
            for(int j = 1; j < img.getHeight()-1; j++){
                if(img.getRGB(i, j) != ImageTools.black){
                    if(img.getRGB(i+1, j) == ImageTools.black && 
                       img.getRGB(i-1, j) == ImageTools.black && 
                       img.getRGB(i, j-1) != ImageTools.black &&
                       img.getRGB(i, j+1) != ImageTools.black){
                        continue;
                    } else if(img.getRGB(i+1, j) != ImageTools.black &&
                    		  img.getRGB(i-1, j) != ImageTools.black && 
                    		  img.getRGB(i, j-1) == ImageTools.black &&
                    		  img.getRGB(i, j+1) == ImageTools.black){
                        continue;
                    } else {
                        nodes.addLast(new Position(i, j));
                   }
                }
            }
        }

        //Start and end points finder
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                if((i == 0 || i == img.getWidth()-1 || j == 0 || j == img.getHeight()-1) && img.getRGB(i, j) == ImageTools.white){
                    if(Mazelight.start == null){
                    	Mazelight.start = new Position(i,j);
                        nodes.addFirst(new Position(i, j));
                    } else if (Mazelight.end == null){
                    	Mazelight.end = new Position(i,j);
                        nodes.addLast(new Position(i, j));
                    }
                }
            }
        }

        //Path finding algorithm
        for(int i = 0; i < nodes.size(); i++){
            Position current = nodes.get(i);
            int x, y, cost;

            //Search nodes on the top and bottom
            x = current.getX(); y = current.getY() - 1; cost = 1;
            while(y >= 0 && img.getRGB(x, y) != ImageTools.black && !isNode(nodes, new Position(x,y))) { y--; cost++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { graph.addEdge(current, new Position(x,y), cost); }

            //Search nodes on the left and right
            x = current.getX() - 1; y = current.getY(); cost = 1;
            while(x >= 0 && img.getRGB(x, y) != ImageTools.black && !isNode(nodes, new Position(x,y))) { x--; cost++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { graph.addEdge(current, new Position(x,y), cost); }
        }

        return graph;
    }
    
    /**
     * Solves a graph using A* algorithm. See https://github.com/SrGMC/mazelight/wiki/Pseudocode#a
     * @param graph Graph to solve
     * @return Path as LinkedList
     */
    public static Relative aStar(Graph graph){
    	LinkedList<Relative> open = new LinkedList<Relative>();
    	LinkedList<Relative> closed = new LinkedList<Relative>();
    	Relative current;
    	
    	int heuristic = (int)Math.sqrt(Math.pow(Math.abs(Mazelight.start.getX() - Mazelight.end.getX()), 2) + 
    			Math.pow(Math.abs(Mazelight.start.getY() - Mazelight.end.getY()), 2));
    	Relative s = new Relative(Mazelight.start, heuristic, 0);
    	open.add(s);
    	
    	while(open.size() != 0) {
    		current = open.poll();
    		LinkedList<Position> adjacent = graph.getAdjacents(current.getPosition());
    		for (int i = 0; i < adjacent.size(); i++) {
    			Position currentAdjacentP = adjacent.get(i);
    			Relative currentAdjacentR = new Relative(currentAdjacentP);
    			currentAdjacentR.setParent(current);
    			if(currentAdjacentP.equals(Mazelight.end)) {
    				return currentAdjacentR;
    			}
    			int h = (int)Math.sqrt(Math.pow(Math.abs(currentAdjacentP.getX() - Mazelight.end.getX()), 2) + 
    					Math.pow(Math.abs(currentAdjacentP.getY() - Mazelight.end.getY()), 2));
    			int g;
    			if (currentAdjacentR.getParent() == null) {
    				g = 0;
    			} else {
    				g = currentAdjacentR.getParent().getG() + graph.getWeight(currentAdjacentP, current.getPosition());
    			}
    			int index = open.indexOf(currentAdjacentR);
    			if(index != -1 && open.get(index).getCost() < (h+g)) {
    				continue;
    			}
    			index = closed.indexOf(currentAdjacentR);
    			if(index != -1 && closed.get(index).getCost() < (h+g)) {
    				continue;
    			} else {
    				open.add(currentAdjacentR);
    			}
			}
    		closed.add(current);
    		Comparator<Relative> sort = new Relative();
    		open.sort(sort);
    	}
    	return null;
    }
}
