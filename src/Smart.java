import java.util.ArrayList;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * SmartTargetingEnemy.java 
 * This class is used to describe the smart enemy class
 * It implements an algorithm to calculate the shortest path to the player.
 * 
 * @author Dobromir
 *
 */
public class Smart extends Enemy {

	private int charPosX = 0; // Players X and Y coordinates
	private int charPosY = 7; //
	private Image pic; // Image to be loaded

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @throws FileNotFoundException
	 */
	public Smart(int posX, int posY) throws FileNotFoundException {
		super(posX, posY);
		super.setType("Smart Targeting");

	}

	// Getters and setters for the players position
	public int getCharPosX() {
		return charPosX;
	}

//
	public void setCharPosX(int charPosX) {
		this.charPosX = charPosX;
	}

	public int getCharPosY() {
		return charPosY;
	}

	public void setCharPosY(int charPosY) {
		this.charPosY = charPosY;
	}

	/**
	 * Method that calculates the shortest path step by step.
	 */
	public void executeAlgorithm(int xPos, int yPos) {
		charPosX = xPos;
		charPosY = yPos;
		Cell source = getCell(getXPos(), getYPos());
		Cell destination = getCell(charPosX, charPosY);
		Graph graph = new Graph(getGrid());
		graph.addNodes();
		graph.constructAdjacenyMatrix();
		Node bestMove = graph.executeBFS(source, destination);
		if (bestMove != null) {
			setPosX(bestMove.getCell().getXPos());
			setPosY(bestMove.getCell().getYPos());
		} else {
			super.move("up");
		}

	}
}
