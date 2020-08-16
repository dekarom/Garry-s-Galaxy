import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * StraightLine.java
 *  Class that implements the straight line enemy and the
 * functions for its' movement.
 * 
 * @author Dobromir
 *
 */
public class StraightLine extends Enemy {
	protected String direction; // Starting direction of the enemy.
	private Image pic;

	/**
	 * Constructor for the straight line enemy.
	 * 
	 * @param posX
	 * @param posY
	 * @param direction
	 */
	public StraightLine(int posX, int posY, String direction) throws FileNotFoundException {
		super(posX, posY);
		super.setType("Straight Line");
		this.direction = direction;
		// this.pic=new Image (new FileInputStream("Straight_Line.png"));
	}

	// Getter and setter for the direction
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	/**
	 * Method the overwrites the movement method of enemy It calls and auxiliary
	 * method to check if the direction is right
	 * 
	 */
	public void moveStraightLine() {

		if (getDirection() == "up") {
			changeDirection("up");
			super.move(this.getDirection());
		}
		if (getDirection() == "down") {
			changeDirection("down");
			super.move(this.getDirection());
		}
		if (getDirection() == "right") {
			changeDirection("right");
			super.move(this.getDirection());
		}
		if (getDirection() == "left") {
			changeDirection("left");
			super.move(this.getDirection());
		}

	}

	/**
	 * Method that changes a direction if the next cell is non-walkable. aiding the
	 * movement method.
	 * 
	 * @param currentMovement
	 */
	public void changeDirection(String currentMovement) {

		Cell upperCell = getCell(getXPos(), getYPos() + 1); // Adjacent cells
		Cell lowerCell = getCell(getXPos(), getYPos() - 1); //
		Cell leftCell = getCell(getXPos() - 1, getYPos()); //
		Cell rightCell = getCell(getXPos() + 1, getYPos()); //

		if (currentMovement == "up" && upperCell.getWalkEnemy() == false) {
			setDirection("down");
		} else if (currentMovement == "down" && lowerCell.getWalkEnemy() == false) {
			setDirection("up");
		} else if (currentMovement == "right" && rightCell.getWalkEnemy() == false) {
			setDirection("left");
		} else if (currentMovement == "left" && leftCell.getWalkEnemy() == false) {
			setDirection("right");
		} else {

		}
	}
}
