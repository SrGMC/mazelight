package com.srgmc.mazelight;

/**
 * Object to store the position of a node
 * @author Álvaro Galisteo (SrGMC)
 *
 */

public class Position{
    private int x;
    private int y;
    private Position parent;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructor
     */
    public Position() {}

    /**
     * Get X coordinate
     * @return X coordinate
     */
    public int getX() {
        return x;
    }
    /**
     * Get Y coordinate
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Compare two Position objects
     * @param pos Position object
     * @return true or false
     */
    public boolean equals(Position pos){
        return this.x == pos.getX() && this.y == pos.getY();
    }
    
    /**
     * Get parent
     * @return Position parent object
     */
    public Position getParent() {
		return this.parent;
	}
	
    /**
     * Set parent
     * @param parent Position object to be parent
     */
	public void setParent(Position parent) {
		this.parent = parent;
	}
}
