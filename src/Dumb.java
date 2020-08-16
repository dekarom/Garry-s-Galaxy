import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * Dumb.java 
 * Class that models the dumb enemy's behavior.
 * 
 * @author Dobromir
 *
 */
public class Dumb extends Enemy {

	private int charPosX; // X coordinate of the player
	private int charPosY; // Y coordinate of the player
	private Image pic; // Image to be loaded on the screen

	/**
	 * Constructor for dumb enemy
	 * 
	 * @param posX
	 * @param posY
	 * @param pic
	 * @throws FileNotFoundException
	 */
	protected Dumb(int posX, int posY) throws FileNotFoundException {
		super(posX, posY);
		super.setType("Dumb Targeting");
		// this.pic = new Image(new FileInputStream("Dumb.png"));
	}

	// Getters and setters
	public int getCharPosX() {
		return charPosX;
	}

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
	 * Method that takes the player's position and overrides the inherited move
	 * method.
	 * 
	 * @param posX
	 * @param posY
	 */
	public void moveDumb(int posX, int posY) {
		setCharPosX(posX);
		setCharPosY(posY);

		if (getYPos() < getCharPosY()) {
			super.move("up");
		} else if (getYPos() > getCharPosY()) {
			super.move("down");
		} else if (getXPos() < getCharPosX()) {
			super.move("right");
		} else if (getXPos() > getCharPosX()) {
			super.move("left");
		}

	}
}
