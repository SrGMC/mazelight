package com.srgmc.mazelight;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Mazelight is a Java-based Open Source CLI tool designed to solve mazes.
 * @author Álvaro Galisteo (SrGMC)
 * @version 0.8
 */

public class Mazelight {

    private static Color colorBlack = new Color(0, 0, 0);
    private static int black = colorBlack.getRGB();
    private static Color colorWhite = new Color(255, 255, 255);
    private static int white = colorWhite.getRGB();
    private static Color colorRed = new Color(255, 0, 0);
    private static int red = colorRed.getRGB();
    private static Color colorGreen = new Color(0, 255, 0);
    private static int green = colorGreen.getRGB();

    private static Position start = null;
    private static Position end = null;
    private static LinkedList<Position> nodes = new LinkedList<Position>();

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
     * Sorts a LinkedList with Positions by weight
     * @param graph Graph object to get the weights from
     * @param start Start position
     * @param list LinkedList with nodes to sort
     * @return void
     */
    private static void sortByWeight(Graph graph, Position start, LinkedList<Position> list){
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size()-i-1; j++){
                if(graph.getWeight(start, list.get(j)) > graph.getWeight(start, list.get(j+1))){
                    Position temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }

    }
    
    /**
     * Converts an image into a BufferedImage object
     * @param path Absolute or relative path of the image
     * @return {@link BufferedImage}
     * @throws IOException
     */

    private static BufferedImage imageToBuffer(String path) throws IOException{
        //Gets image file
        File file = new File(path);
        BufferedImage img = ImageIO.read(file);

        return img;
    }
    
    private static BufferedImage draw(BufferedImage image, Position start, Position end, int rgb) {
    	if(start.getX() == end.getX()) {
    		int min = Math.min(start.getY(), end.getY());
    		int max = Math.max(start.getY(), end.getY());
    		for (int i = min; i <= max; i++) {
				image.setRGB(start.getX(), i, rgb);
			}
    	} else {
    		int min = Math.min(start.getX(), end.getX());
    		int max = Math.max(start.getX(), end.getX());
    		for (int i = min; i <= max; i++) {
				image.setRGB(i, start.getY(), rgb);
			}
    	}
    	
    	return image;
    }

    /**
     * Converts a BufferedImage object into a Graph object
     * @param img {@link BufferedImage} to convert
     * @return Graph object
     * @throws IOException
     * @throws Exception
     */
    private static Graph bufferToGraph(BufferedImage img) throws IOException, Exception{
        Graph graph = new Graph();

        //Node finder
        for(int i = 1; i < img.getWidth()-1; i++){
            for(int j = 1; j < img.getHeight()-1; j++){
                if(img.getRGB(i, j) != black){
                    if(img.getRGB(i+1, j) == black && img.getRGB(i-1, j) == black && img.getRGB(i, j-1) != black && img.getRGB(i, j+1) != black){
                        continue;
                    } else if(img.getRGB(i+1, j) != black && img.getRGB(i-1, j) != black && img.getRGB(i, j-1) == black && img.getRGB(i, j+1) == black){
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
                if((i == 0 || i == img.getWidth()-1 || j == 0 || j == img.getHeight()-1) && img.getRGB(i, j) == white){
                    img.setRGB(i,j, red);
                    if(start == null){
                        start = new Position(i,j);
                        nodes.addFirst(new Position(i, j));
                    } else if (end == null){
                        end = new Position(i,j);
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
            while(y >= 0 && img.getRGB(x, y) != black && !isNode(nodes, new Position(x,y))) { y--; cost++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { graph.addEdge(current, new Position(x,y), cost); }

            //Search nodes on the left and right
            x = current.getX() - 1; y = current.getY(); cost = 1;
            while(x >= 0 && img.getRGB(x, y) != black && !isNode(nodes, new Position(x,y))) { x--; cost++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { graph.addEdge(current, new Position(x,y), cost); }
        }

        return graph;
    }
    
    /**
     * Solves a graph using A* algorithm
     * @param graph Graph to solve
     * @return Path as LinkedList
     */
    private static Relative aStar(Graph graph){
    	LinkedList<Relative> open = new LinkedList<Relative>();
    	LinkedList<Relative> closed = new LinkedList<Relative>();
    	Relative current;
    	
    	int heuristic = (int)Math.sqrt(Math.pow(Math.abs(start.getX() - end.getX()), 2) + Math.pow(Math.abs(start.getY() - end.getY()), 2));
    	Relative s = new Relative(start, heuristic, 0);
    	open.add(s);
    	
    	while(open.size() != 0) {
    		current = open.poll();
    		LinkedList<Position> adjacent = graph.getAdjacents(current.getPosition());
    		for (int i = 0; i < adjacent.size(); i++) {
    			Position currentAdjacentP = adjacent.get(i);
    			Relative currentAdjacentR = new Relative(currentAdjacentP);
    			currentAdjacentR.setParent(current);
    			if(currentAdjacentP.equals(end)) {
    				return currentAdjacentR;
    			}
    			int h = (int)Math.sqrt(Math.pow(Math.abs(currentAdjacentP.getX() - end.getX()), 2) + Math.pow(Math.abs(currentAdjacentP.getY() - end.getY()), 2));
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
    
    private static LinkedList<Position> getPath(Relative r){
    	LinkedList<Position> path = new LinkedList<Position>();
    	do {
    		path.addFirst(r.getPosition());
    		r = r.getParent();
    	} while (r.getParent() != null);
    	path.addFirst(start);
    	
    	return path;
    }

    public static void main(String[] args) throws IOException, Exception {
        long startT = System.currentTimeMillis();
        String img = "mazes/maze10.png";

        Graph maze = bufferToGraph(imageToBuffer(img));
        
        System.out.println(start.getX() + ", " + start.getY() + " to " + end.getX() + ", " + end.getY());
        System.out.println(nodes.size());
        
        Relative last = aStar(maze);
        LinkedList<Position> path = getPath(last);
        
        BufferedImage image = imageToBuffer(img);
        for (int i = 0; i < path.size()-1; i++) {
        	Position c1 = path.get(i);
        	Position c2 = path.get(i+1);
        	draw(image, c1, c2, green);
        	System.out.println(c1.getX() + ", " + c1.getY() + " to " + c2.getX() + ", " + c2.getY());
		}
        ImageIO.write(image, "png", new File(img + ".sol.png"));

        System.out.println("\nTotal computation time: " + (System.currentTimeMillis() - startT) + "ms");
    }
}
