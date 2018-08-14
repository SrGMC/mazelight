package dijkstra;

import java.util.LinkedList;

public class Graph{

    private LinkedList<Edge> edges = new LinkedList<Edge>();

    public Graph(){}

    public int size(){
        return edges.size();
    }

    public void addEdge(Position start, Position end, int weight) throws Exception{
        this.edges.addLast(new Edge(start, end, weight));
    }

    public void removeEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if(this.edges.get(i).getStart().equals(start)
               && this.edges.get(i).getEnd().equals(end)) {
                this.edges.remove(i);
            }
        }
    }

    public boolean isEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return true;
            }
        }

        return false;
    }

    public int getWeight(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return this.edges.get(i).getWeight();
            }
        }

        return -1;
    }

    public Edge getEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i++){
            if((this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end))
                || (this.edges.get(i).getStart().equals(end) && this.edges.get(i).getEnd().equals(start))) {
                return this.edges.get(i);
            }
        }

        return null;
    }

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
