import java.util.ArrayList;

/**
 * Node.java
 * This Class used to convert each cell into a node.
 * 
 * @author Dobromir
 *
 */
public class Node {
	private Cell cell; // The contained cell
	private ArrayList<Node> neighbours = new ArrayList<Node>(); // Neighbors list
	private boolean isVisited; // Shows if a cell has been visited before.
	public int count = 1000; // Used similarly to weight as each Node

	/**
	 * 
	 * @param cell
	 */
	public Node(Cell cell) {
		this.cell = cell;
		if (this.cell.getWalkEnemy() == false) {
			this.isVisited = true;
			if (this.cell.getWalkEnemy() == true) {
				this.isVisited = false;
			}
		}
	}

	public Node() {

	}

	// Setters and getters.
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public void setNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void addNeighbours(Node neighbour) {
		neighbours.add(neighbour);

	}

	public ArrayList<Node> getNeighbourNodes() {
		return neighbours;
	}

	public void visit() {
		isVisited = true;
	}

	@Override
	public String toString() {
		return (getCell().toString() + " " + isVisited);
	}

}
