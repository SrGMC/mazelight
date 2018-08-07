
public class Link {
	private int weight;
	private Cell node;
	
	public Link(Cell node, int weight) {
		this.weight = weight;
		this.node = node;
	}
	
	public int getWeight() {
		return weight;
	}
	public Cell getNode() {
		return node;
	}
}
