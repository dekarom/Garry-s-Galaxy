import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/**
 * ColourKeyDoor.java
 *  Class that models coloured keys
 * 
 * @author Sam
 *
 */
public class ColourKeyDoor extends Cell {
	/**
	 * Constructor for a colour key.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @param colour
	 * @throws FileNotFoundException
	 */
	public ColourKeyDoor(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile, String colour)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		setColour(colour);
		this.cellId = "colour door";

	}

}