import java.util.ArrayList;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * Enemy.java 
 * Super Class Enemy providing functions and attributes for all types
 * of enemies.
 * 
 * @author Dobromir
 */

public class Enemy {
	/**
	 * @param posX       current X coordinate
	 * @param posY       current Y coordinate
	 * @param type       The enemy's type
	 * @param            mapMinX,mapMaxX, mapMinY,mapMaxY Grid size
	 * @param currentMap Current grid and map used in the enemy class
	 */
	private int posX;
	private int posY;
	private String type;
	private ArrayList<ArrayList<Cell>> currentMap;
	private int mapMinX;
	private int mapMaxX;
	private int mapMinY;
	private int mapMaxY;
	private Image pic;

	/**
	 * Constructor of Enemy
	 * 
	 * @param posX Stores the position of an enemy on the x axis
	 * @param posY Stores the position of an enemy on the y axis
	 * @param type Defines the type of the enemy
	 */
	protected Enemy(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;

	}

	/**
	 * Setter for the posX.
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Setter for posY.
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Setter for the type of an enemy.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Method that provides the movement for an enemy. It takes as string for the
	 * movement and computes the new position of the enemy. It will return an error
	 * message if the users tries to move the enemy out of the grid.
	 * 
	 * @param movement
	 */
	public void move(String movement) {
		if (posX >= mapMinX && posX <= mapMaxX && posY >= mapMinY && posY <= mapMaxY) {
			if (movement == "up" && getCell(posX, posY + 1).getWalkEnemy() == true) {
				setPosY(posY + 1);
			} else if (movement == "down" && getCell(posX, posY - 1).getWalkEnemy() == true) {
				setPosY(posY - 1);
			} else if (movement == "left" && getCell(posX - 1, posY).getWalkEnemy() == true) {
				setPosX(posX - 1);
			} else if (movement == "right" && getCell(posX + 1, posY).getWalkEnemy() == true) {
				setPosX(posX + 1);
			} else {
				System.out.println("Error!");
			}
		} else {
			System.out.println("Position out of Grid!");
		}
	}

	/**
	 * Method that takes a Grid, computes the its size and stores it into the enemy
	 * class.
	 * 
	 * @param test1 Grid
	 */
	//
	public void setEnemyGrid(ArrayList<ArrayList<Cell>> test1) {

		this.currentMap = test1;
		Cell compare = getCell(0, 0);
		for (int row = 0; row < currentMap.size(); row++) {
			for (int col = 0; col < currentMap.get(row).size(); col++) {
				Cell tempCell = currentMap.get(row).get(col);
				if (tempCell.getXPos() > compare.getXPos() || tempCell.getYPos() > compare.getYPos()) {
					compare = tempCell;
					mapMaxX = compare.getXPos();
					mapMaxY = compare.getYPos();
				}
			}
		}
		for (int row = 0; row < currentMap.size(); row++) {
			for (int col = 0; col < currentMap.get(row).size(); col++) {
				Cell tempCell = currentMap.get(row).get(col);
				if (tempCell.getXPos() < compare.getXPos() || tempCell.getYPos() < compare.getYPos()) {
					compare = tempCell;
					mapMinX = compare.getXPos();
					mapMinY = compare.getYPos();

				}
			}
		}

	}

	public ArrayList<ArrayList<Cell>> getGrid() {
		return currentMap;
	}

	public Cell getCell(int x, int y) {
		Cell myCell = null;
		// String test="test";
		for (int row = 0; row < currentMap.size(); row++) {
			for (int col = 0; col < currentMap.get(row).size(); col++) {
				if (y == row && x == col) {
					myCell = currentMap.get(row).get(col);
				}
			}
		}

		return myCell;
	}

	/**
	 * Getter for the posX.
	 * 
	 * @return posX
	 */
	public int getXPos() {
		return posX;
	}

	/**
	 * Getter for the posY.
	 * 
	 * @return posY
	 */
	public int getYPos() {
		return posY;
	}

	/**
	 * Getter for the enemy type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Converts an enemy to String.
	 * 
	 * @return Object as a String
	 */
	public String toString() {
		return ("This is an enemy of type " + this.type + " with positon " + this.posX + ", " + this.posY);
	}
}
