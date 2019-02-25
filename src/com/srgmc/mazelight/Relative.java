package com.srgmc.mazelight;

import java.util.Comparator;

/**
 * Object to store the position of a node
 * @author Álvaro Galisteo (SrGMC)
 *
 */

public class Relative implements Comparator<Relative>{
    private Position position;
    private Relative parent;
    private int h;
    private int g;

    /**
     * Constructor
     * @param position Position object
     */
    public Relative(Position position){
        this.position = position;
    }
    
    /**
     * Constructor
     * @param position Position object
     * @param h Heuristic cost
     * @param g Weight cost
     */
    public Relative(Position position, int h, int g) {
		this.position = position;
		this.h = h;
		this.g = g;
	}

    /**
     * Constructor
     */
	public Relative() {}

	/**
	 * Compare Position of two Relative objects
	 * @param r
	 * @return
	 */
    public boolean equals(Relative r){
        return this.position.equals(r.getPosition());
    }
    
    /**
     * Set the heuristic cost
     * @param h Heuristic cost
     */
    public void setH(int h) {
		this.h = h;
	}
    
    /**
     * Get the heuristic cost
     * @return Heuristic cost
     */
    public int getH() {
		return this.h;
	}
    
    /**
     * Set the weight cost
     * @param g Weight cost
     */
    public void setG(int g) {
		this.g = g;
	}
    
    /**
     * Get the weight cost
     * @return Weight cost
     */
    public int getG() {
		return this.g;
	}
    
    /**
     * Get the total cost: f(n) = h(n) + g(n)
     * @return
     */
    public int getCost() {
		return this.g + this.h;
	}
    
    /**
     * Set Position
     * @param position Position object
     */
	public void setPosition(Position position) {
		this.position = position;
	}
    
	/**
	 * Get position
	 * @return Position object
	 */
	public Position getPosition() {
		return this.position;
	}
    
	/**
	 * Get parent
	 * @return Position parent object
	 */
	public Relative getParent() {
		return this.parent;
	}
	
	/**
	 * Set parent
	 * @param parent Position parent object
	 */
	public void setParent(Relative parent) {
		this.parent = parent;
	}

	/**
	 * Compare the cost of two Relative objects
	 * @param p1 the first object to be compared.
     * @param p2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
	 */
	public int compare(Relative p1, Relative p2) {
		return p1.getCost() - p2.getCost();
	}
}
