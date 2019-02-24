package com.srgmc.mazelight;

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

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
