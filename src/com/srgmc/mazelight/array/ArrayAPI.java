package com.srgmc.mazelight.array;

import com.srgmc.mazelight.Mazelight.ALGO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import com.srgmc.mazelight.ImageTools;
import com.srgmc.mazelight.Mazelight;
import com.srgmc.mazelight.Position;
import com.srgmc.mazelight.Relative;

public class ArrayAPI {
	private static Relative[][] grid;
    private static Position start = null;
    private static Position end = null;
	
	private static int getSize(Relative[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if(grid[i][j] != null) count++;
			}
		}
		return count;
	}
	
    /**
     * Converts a BufferedImage object into a Relative matrix
     * @param img {@link BufferedImage} to convert
     * @return Graph object
     * @throws IOException
     * @throws Exception
     */
    private static Relative[][] bufferToGraph(BufferedImage img) throws IOException, Exception{
    	Relative[][] grid = new Relative[img.getWidth()][img.getHeight()];

        //Node finder
        for(int i = 1; i < img.getWidth()-1; i++){
            for(int j = 1; j < img.getHeight()-1; j++){
                if(img.getRGB(i, j) == ImageTools.black){
                  grid[i][j] = null;
                } else {
                  grid[i][j] = new Relative(new Position(i, j));
                }
            }
        }

        //Start and end points finder
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                if((i == 0 || i == img.getWidth()-1 || j == 0 || j == img.getHeight()-1) && img.getRGB(i, j) == ImageTools.white){
                    if(start == null){
                    	start = new Position(i,j);
                    	grid[i][j] = new Relative(new Position(i, j));
                    } else if (end == null){
                    	end = new Position(i,j);
                    	grid[i][j] = new Relative(new Position(i, j));
                    }
                }
            }
        }

        return grid;
    }
    
    private static Relative aStar() {
    	Comparator<Relative> comparator = new Relative();
        PriorityQueue<Relative> open = new PriorityQueue<Relative>(comparator);
        boolean closed[][] = new boolean[grid.length][grid[0].length];
        
      //add the start location to open list.
        int h = (int)Math.sqrt(Math.pow(Math.abs(start.getX() - end.getX()), 2) + 
    			Math.pow(Math.abs(start.getY() - end.getY()), 2));
        Relative s = new Relative(start, h, 0);
        Relative e = new Relative(end, 0, 0);
        open.add(s);
        
        Relative current;
        
        while(true){ 
            current = open.poll();
            if(current==null)break;
            closed[current.getPosition().getX()][current.getPosition().getY()]=true; 

            if(current.equals(e)){
                return e; 
            } 

            Relative t;
        }
		return null;
    }
	
	public static Relative solve(String input, String output, ALGO algo) {
		return null;
	}
}
