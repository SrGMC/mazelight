package master;
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
