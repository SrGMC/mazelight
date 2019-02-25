package com.srgmc.mazelight.graph;

import java.util.LinkedList;

import com.srgmc.mazelight.Position;

/**
 * Data type to store information about a Graph
 * @author Álvaro Galisteo (SrGMC)
 */

public class Graph{
    private LinkedList<Edge> edges = new LinkedList<Edge>();

    public Graph(){}

    /**
     * Get the total number of edges
     * @return Total number of edges
     */
    public int size(){
        return edges.size();
    }

    /**
     * Creates a new edge in the Graph.
     * @param start The start Position object of the edge.
     * @param end The end Position object of the edge.
     * @param weight The weight of the edge
     * @throws Exception
     */
    public void addEdge(Position start, Position end, int weight) throws Exception{
        if (start == null || end == null)
            throw new Exception("Error 001x001: Edge start and end position is null");
        else
        	this.edges.addLast(new Edge(start, end, weight));
    }

    /**
     * Removes an existing edge in the Graph.
     * @param start The start Position object of the edge.
     * @param end The end Position object of the edge.
     * @throws Exception
     */
    public void removeEdge(Position start, Position end) throws Exception{
        if (start == null || end == null) {
            throw new Exception("Error 001x001: Edge start and end position is null");
    	}
        for(int i = 0; i < this.edges.size(); i++){
            if(this.edges.get(i).getStart().equals(start)
               && this.edges.get(i).getEnd().equals(end)) {
                this.edges.remove(i);
            }
        }
    }

    /**
     * Checks if the edge exists.
     * @param start The start Position object of the edge.
     * @param end The end Position object of the edge.
     * @return true or false
     */
    public boolean isEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get the weight of the edge
     * @param start The start Position object of the edge.
     * @param end The end Position object of the edge.
     * @return Weight of the edge
     */
    public int getWeight(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return this.edges.get(i).getWeight();
            }
        }

        return -1;
    }

    /**
     * Get edge (if exists) from start to end
     * @param start The start Position object of the edge.
     * @param end The end Position object of the edge.
     * @return Edge or null if it does not exist
     */
    public Edge getEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return this.edges.get(i);
            }
        }

        return null;
    }

    /**
     * Get the adjacent nodes
     * @param pos Node to get the adjacent from
     * @return List with adjacent nodes
     */
    public LinkedList<Position> getAdjacents(Position pos){
        LinkedList<Position> result = new LinkedList<Position>();
        for(int i = 0; i < this.edges.size(); i++){
            if(this.edges.get(i).getStart().equals(pos)){
                result.addLast(this.edges.get(i).getEnd());
            } else if(this.edges.get(i).getEnd().equals(pos)){
                result.addLast(this.edges.get(i).getStart());
            }
        }
        return result;
    }

}
