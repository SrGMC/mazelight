package com.srgmc.mazelight;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
            System.out.println("Sides: ");
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
    public static LinkedList<Position> aStar(Graph graph){
    	LinkedList<Position> path = new LinkedList<Position>();
    	
    	return path;
    }

    public static void main(String[] args) throws IOException, Exception {
        long startT = System.currentTimeMillis();

        Graph maze = bufferToGraph(imageToBuffer("mazes/maze1.png"));

        System.out.println("\n\nTotal computation time: " + (System.currentTimeMillis() - startT) + "ms");
    }
}
