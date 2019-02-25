package com.srgmc.mazelight.graph;

import com.srgmc.mazelight.Position;

/**
 * Object to store information about and Edge. Used by Graph
 * @author Álvaro Galisteo (SrGMC)
 *
 */

public class Edge{
    private Position start;
    private Position end;
    private int weight;

    /**
     * Constructor.
     * @param start 
     * @param end
     * @param weight
     * @throws Exception
     */
    public Edge(Position start, Position end, int weight) throws Exception{
        if (start == null || end == null) {
            throw new Exception("Error 001x001: Edge start and end position is null");
        } else {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * Get the start of the edge
     * @return Position start object
     */
    public Position getStart() {
        return start;
    }

    /**
     * Get the end of the edge
     * @return Position end object
     */
    public Position getEnd() {
        return end;
    }

    /**
     * Get the weight of the edge
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of the edge
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
