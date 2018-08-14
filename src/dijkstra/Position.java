package dijkstra;

public class Position implements Comparable<Position>{
    private int x;
    private int y;
    private int minDistance = Integer.MAX_VALUE;
    private Position previous;

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

    public int getMinDistance(){
        return minDistance;
    }

    public void setMinDistance(int minDistance){
        this.minDistance = minDistance;
    }

    public boolean equals(Position pos){
        return this.x == pos.getX() && this.y == pos.getY();
    }

    public Position getPrevious() {
        return previous;
    }

    public void setPrevious(Position previous) {
        this.previous = previous;
    }

    public int compareTo(Position other) {
        return Integer.compare(this.minDistance, other.getMinDistance());
    }
}
