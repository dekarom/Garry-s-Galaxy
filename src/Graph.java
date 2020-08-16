import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Queue;

/**
 * Graph.java
 * This class takes all the cells, converts them into nodes and execute the
 * shortest path algorithm.
 * 
 * @author Dobromir
 *
 */
public class Graph {
	public Node tempNode = new Node();
	private ArrayList<ArrayList<Cell>> currentMap; // The 2d array representation of the map
	private ArrayList<Node> nodeContainer = new ArrayList<Node>(); // Array List of the transformed 2d array of cells
	private HashMap<Node, ArrayList<Node>> adjacencyMap = new HashMap<Node, ArrayList<Node>>(); // Adjacency list with
	private int count = 0; // hashmap

	/**
	 * 
	 * @param currentMap
	 */
	public Graph(ArrayList<ArrayList<Cell>> currentMap) {
		this.tempNode.count = 1000;
		this.currentMap = currentMap;
		this.adjacencyMap = adjacencyMap;
	}

	// Setters and getters
	public ArrayList<ArrayList<Cell>> getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(ArrayList<ArrayList<Cell>> currentMap) {
		this.currentMap = currentMap;
	}

	/**
	 * Method that that transform the list of cell into a list of nodes.
	 */
	public void addNodes() {
		for (int row = 0; row < currentMap.size(); row++) {
			for (int col = 0; col < currentMap.get(row).size(); col++) {
				Node tempNode = new Node(currentMap.get(row).get(col));
				nodeContainer.add(tempNode);
			}
		}
	}

//
	public ArrayList<Node> getNodeContainer() {
		return nodeContainer;
	}

	public void setNodeContainer(ArrayList<Node> nodeContainer) {
		this.nodeContainer = nodeContainer;
	}

	/**
	 * Function that constructs an adjacecancy matrix in the form of Hash Map
	 */
	public void constructAdjacenyMatrix() {
		Node tempNode = null;
		Node compareNode = null;
		ArrayList<Node> tempList = new ArrayList<Node>();
		for (int i = 0; i < nodeContainer.size(); i++) {
			tempNode = nodeContainer.get(i);
			adjacencyMap.put(nodeContainer.get(i), new ArrayList<Node>());
			for (int j = 0; j < nodeContainer.size(); j++) {
				compareNode = nodeContainer.get(j);
				if (compareNode.getCell().getXPos() == tempNode.getCell().getXPos()
						&& compareNode.getCell().getYPos() == tempNode.getCell().getYPos() + 1) {
					adjacencyMap.get(tempNode).add(compareNode);
				} else if (compareNode.getCell().getXPos() == tempNode.getCell().getXPos()
						&& compareNode.getCell().getYPos() == tempNode.getCell().getYPos() - 1) {
					adjacencyMap.get(tempNode).add(compareNode);
				} else if (compareNode.getCell().getXPos() == tempNode.getCell().getXPos() + 1
						&& compareNode.getCell().getYPos() == tempNode.getCell().getYPos()) {
					adjacencyMap.get(tempNode).add(compareNode);
				} else if (compareNode.getCell().getXPos() == tempNode.getCell().getXPos() - 1
						&& compareNode.getCell().getYPos() == tempNode.getCell().getYPos()) {
					adjacencyMap.get(tempNode).add(compareNode);
				}

			}
		}
	}

	public void setAdjacencyMap(HashMap<Node, ArrayList<Node>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
	}

	public String getAdjacencyMap() {
		String something = "wow";
		for (Node keys : adjacencyMap.keySet()) {
			System.out.println(keys + ":" + adjacencyMap.get(keys));
		}
		return something;
	}

	/**
	 * Method that executes the shortest path algorithm
	 * 
	 * @param start - Enemy's current position
	 * @param end   - Player's current position
	 * @return Node next move
	 */
	public Node executeBFS(Cell start, Cell end) {
		Node source = null;

		for (int i = 0; i < nodeContainer.size(); i++) {
			Node tempNode = nodeContainer.get(i);
			if (tempNode.getCell().getXPos() == start.getXPos() && tempNode.getCell().getYPos() == start.getYPos()) {
				source = tempNode;
			}
		}
		Node destination = null;
		for (int i = 0; i < nodeContainer.size(); i++) {
			Node tempNode = nodeContainer.get(i);
			if (tempNode.getCell().getXPos() == end.getXPos() && tempNode.getCell().getYPos() == end.getYPos()) {
				destination = tempNode;
			}
		}
		ArrayList<Node> path = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();

		q.add(destination);
		while (!q.isEmpty()) {
			Node currentNode = q.remove();
			if (currentNode == source) {
				for (Node neighbour1 : adjacencyMap.get(currentNode)) {
					if (neighbour1.isVisited() == true && neighbour1.getCell().getWalkEnemy() == true) {
						if (this.tempNode.count > neighbour1.count && this.tempNode.count != 0) {
							this.tempNode = neighbour1;

						}
						path.add(tempNode);
					}
				}
				q.clear();
				return tempNode;

			} else {
				currentNode.setVisited(true);
				currentNode.count = this.count;
				this.count++;
				for (Node neighbour : adjacencyMap.get(currentNode)) {
					if (neighbour.isVisited() == false) {
						neighbour.setVisited(true);
						q.add(neighbour);
					}
				}
			}
		}
		return null;
	}
}
