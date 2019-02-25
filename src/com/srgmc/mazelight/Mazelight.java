package com.srgmc.mazelight;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.srgmc.mazelight.array.ArrayAPI;
import com.srgmc.mazelight.graph.GraphAPI;

import java.util.LinkedList;

/**
 * Mazelight is a Java-based Open Source CLI tool designed to solve mazes.
 * @author Álvaro Galisteo (SrGMC)
 * @version 0.8
 */

public class Mazelight {
    public static Position start = null;
    public static Position end = null;
    
    /**
     * Solves a maze given from an input image
     * @param input Path of the image to solve
     * @param output Path of the solved image
     * @param algo Algorithm to use
     * @param api API to use
     * @return true or false, depending on the result of the execution
     * @throws IOException
     * @throws Exception
     */
    public static boolean solve(String input, String output, ALGO algorithm, API api) throws IOException, Exception {
    	Relative last;
    	switch (api) {
		case GRAPH:
			last = GraphAPI.solve(input, output, algorithm);
			break;
		case ARRAY:
			last = ArrayAPI.solve(input, output, algorithm);
			break;
		default:
			return false;
		}
    	
        LinkedList<Position> path = getPath(last);
        BufferedImage image = ImageTools.imageToBuffer(input);
        for (int i = 0; i < path.size()-1; i++) {
        	Position c1 = path.get(i);
        	Position c2 = path.get(i+1);
        	ImageTools.draw(image, c1, c2, ImageTools.green);
		}
        ImageIO.write(image, "png", new File(output));
        return true;
    }
   
    /**
     * Generate a path ending with r
     * @param r Last Relative to end the path with
     * @return Path list
     */
    private static LinkedList<Position> getPath(Relative r){
    	LinkedList<Position> path = new LinkedList<Position>();
    	do {
    		path.addFirst(r.getPosition());
    		r = r.getParent();
    	} while (r.getParent() != null);
    	path.addFirst(start);
    	
    	return path;
    }
    
    public enum ALGO {
        ASTAR
    }
    
    public enum API {
        GRAPH, ARRAY
    }
    
    public static void main(String[] args) throws IOException, Exception {
        long startT = System.currentTimeMillis();
        if(args.length != 2) {
        	System.out.println("Usage: java mazelight.jar <input image path> <output image path>");
        	System.exit(1);
        }
        if(solve(args[0], args[1], ALGO.ASTAR, API.GRAPH)) {
        	System.out.println("Done...");
        } else {
        	throw new Exception("Unknown algorithm");
        }
        System.out.println("Total computation time: " + (System.currentTimeMillis() - startT) + "ms");
    }
}
