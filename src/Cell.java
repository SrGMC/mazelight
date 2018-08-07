
public class Cell {
	private int type; //0: Empty space
					  //1: Wall
	  				  //2: Node
					  //3: Exit/Start node
	private int vertex;
	
	public Cell(int type, int vertex) {
		this.type = type;
		this.vertex = vertex;
	}
	
	public void setVertex(int i) {
		this.vertex = i;
	}
	public int getVertex() {
		return vertex;
	}
	
	public void setSpecial() {
		type = 3;
	}
	public boolean isSpecial() {
		return type == 3;
	}
	public boolean isEmpty() {
		return type == 0;
	}
	public boolean isWall() {
		return type == 1;
	}
	public boolean isNode() {
		return (type == 2 || type == 3);
	}
}
