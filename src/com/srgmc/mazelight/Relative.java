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

    public Relative(Position position){
        this.position = position;
    }
    
    public Relative(Position position, int h, int g) {
		this.position = position;
		this.h = h;
		this.g = g;
	}

	public Relative() {}

    public boolean equals(Relative r){
        return this.position.getX() == r.getPosition().getX() && this.position.getY() == r.getPosition().getY();
    }
    
    public void setH(int h) {
		this.h = h;
	}
    
    public int getH() {
		return this.h;
	}
    
    public void setG(int g) {
		this.g = g;
	}
    
    public int getG() {
		return this.g;
	}
    
    public int getCost() {
		return this.g + this.h;
	}
    
	public void setPosition(Position position) {
		this.position = position;
	}
    
	public Position getPosition() {
		return this.position;
	}
    
	public Relative getParent() {
		return this.parent;
	}
	
	public void setParent(Relative parent) {
		this.parent = parent;
	}

	public int compare(Relative p1, Relative p2) {
		return p1.getCost() - p2.getCost();
	}
}
