package com.srgmc.mazelight;

/**
 * Object to store the position of a node
 * @author Álvaro Galisteo (SrGMC)
 *
 */

public class Position{
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean equals(Position pos){
        return this.x == pos.getX() && this.y == pos.getY();
    }
}
