package version0_2;

import java.util.LinkedList;

public class Graph{
    public class Edge{
        private Position start;
        private Position end;
        private int weight;

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

    private LinkedList<Edge> edges = new LinkedList<Edge>();

    public Graph(){}

    public int size(){
        return edges.size() /2;
    }

    public void addEdge(Position start, Position end) throws Exception{
        this.edges.addLast(new Edge(start, end, 0));
        this.edges.addLast(new Edge(end, start, 0));
    }

    public void removeEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i = i+2){
            if(this.edges.get(i).getStart().equals(start)
               && this.edges.get(i).getEnd().equals(end)) {
                this.edges.remove(i);
                this.edges.remove(i+1);
            }
        }
    }

    public boolean isEdge(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i = i+2){
            if(this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end)
                && this.edges.get(i+1).getStart().equals(end) && this.edges.get(i+1).getEnd().equals(start)) {
                return true;
            }
        }

        return false;
    }

    public int getWeight(Position start, Position end){
        for(int i = 0; i < this.edges.size(); i = i+2){
            if(this.edges.get(i).getStart().equals(start) && this.edges.get(i).getEnd().equals(end)) {
                return this.edges.get(i).getWeight();
            }
        }

        return -1;
    }

    public LinkedList<Position> getAdjacents(Position pos){
        LinkedList<Position> result = new LinkedList<Position>();
        for(int i = 0; i < this.edges.size(); i = i+2){
            if(this.edges.get(i).getStart().equals(pos)){
                result.addLast(this.edges.get(i).getEnd());
            }
        }
        return result;
    }

}
