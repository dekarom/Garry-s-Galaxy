import java.util.ArrayList;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * WallFollowing.java 
 * Class that models the wall following enemy behaviors.
 * 
 * @author Dobromir
 *
 */

public class WallFollowing extends Enemy {
	private String startingDirection;
	private Cell upperCell; // The following 4 attributes are used
	private Cell lowerCell; // to record the position of the neighboring
	private Cell leftCell; // for the movement
	private Cell rightCell; //
	private Image pic; // Image file to be displayed
	ArrayList<String> visitedCells = new ArrayList<String>(); // Stores the visited cells.e

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @throws FileNotFoundException
	 */
	public WallFollowing(int posX, int posY) throws FileNotFoundException {
		super(posX, posY);
		super.setType("Wall Following");
		// this.pic=new Image(new FileInputStream("Wall Following.png"));
		// this.startingDirection = direction;
	}

// Getters and setters for direction
	public String getDirection() {
		return startingDirection;
	}

	public void setDirection(String direction) {
		this.startingDirection = direction;
	}

	/**
	 * Method that overrides the inherited move().
	 */
	public void followWall() {
		updateNeighbours();
		if (upperCell.getWalkEnemy() == true
				&& visitedCells.contains(upperCell.getXPos() + " " + upperCell.getYPos()) == false) { // up
			super.move("up");
			visitedCells.add(lowerCell.getXPos() + " " + lowerCell.getYPos());
			updateNeighbours();
		} else if (rightCell.getWalkEnemy() == true
				&& visitedCells.contains(rightCell.getXPos() + " " + rightCell.getYPos()) == false) {
			super.move("right");
			updateNeighbours();
			visitedCells.add(leftCell.getXPos() + " " + leftCell.getYPos());
		} else if (leftCell.getWalkEnemy() == true
				&& visitedCells.contains(leftCell.getXPos() + " " + leftCell.getYPos()) == false) {
			super.move("left");
			updateNeighbours();
			visitedCells.add(rightCell.getXPos() + " " + rightCell.getYPos());
		} else {
			if (lowerCell.getWalkEnemy() == true
					&& visitedCells.contains(lowerCell.getXPos() + " " + lowerCell.getYPos()) == false) { // down
				super.move("down");
				updateNeighbours();
				visitedCells.add(upperCell.getXPos() + " " + upperCell.getYPos());
			} else if (rightCell.getWalkEnemy() == true
					&& visitedCells.contains(rightCell.getXPos() + " " + rightCell.getYPos()) == false) {
				super.move("right");
				updateNeighbours();
				visitedCells.add(leftCell.getXPos() + " " + leftCell.getYPos());
			} else if (leftCell.getWalkEnemy() == true
					&& visitedCells.contains(leftCell.getXPos() + " " + leftCell.getYPos()) == false) {
				super.move("left");
				updateNeighbours();
				visitedCells.add(rightCell.getXPos() + " " + rightCell.getYPos());
			} else {
				if (rightCell.getWalkEnemy() == true
						&& visitedCells.contains(rightCell.getXPos() + " " + rightCell.getYPos()) == false) { // right
					super.move("right");
					updateNeighbours();
					visitedCells.add(leftCell.getXPos() + " " + leftCell.getYPos());
				} else if (upperCell.getWalkEnemy() == true
						&& visitedCells.contains(upperCell.getXPos() + " " + upperCell.getYPos()) == false) {
					super.move("up");
					updateNeighbours();
					visitedCells.add(lowerCell.getXPos() + " " + lowerCell.getYPos());
				} else if (lowerCell.getWalkEnemy() == true
						&& visitedCells.contains(lowerCell.getXPos() + " " + lowerCell.getYPos()) == false) {
					super.move("down");
					updateNeighbours();
					visitedCells.add(upperCell.getXPos() + " " + upperCell.getYPos());
				} else {
					if (leftCell.getWalkEnemy() == true
							&& visitedCells.contains(leftCell.getXPos() + " " + leftCell.getYPos()) == false) { // left
						super.move("left");
						updateNeighbours();
						visitedCells.add(rightCell.getXPos() + " " + rightCell.getYPos());
					} else if (upperCell.getWalkEnemy() == true
							&& visitedCells.contains(upperCell.getXPos() + " " + upperCell.getYPos()) == false) {
						super.move("up");
						updateNeighbours();
						visitedCells.add(lowerCell.getXPos() + " " + lowerCell.getYPos());
					} else if (lowerCell.getWalkEnemy() == true
							&& visitedCells.contains(lowerCell.getXPos() + " " + lowerCell.getYPos()) == false) {
						super.move("down");
						updateNeighbours();
						visitedCells.add(upperCell.getXPos() + " " + upperCell.getYPos());
					} else {
						visitedCells.clear();
						System.out.println("cleared");
						followWall();
					}
				}
			}
		}

	}

	/**
	 * Method that sets and updates the adjacent cells.
	 */
	private void updateNeighbours() {
		upperCell = getCell(getXPos(), getYPos() + 1);
		lowerCell = getCell(getXPos(), getYPos() - 1);
		leftCell = getCell(getXPos() - 1, getYPos());
		rightCell = getCell(getXPos() + 1, getYPos());
	}
}