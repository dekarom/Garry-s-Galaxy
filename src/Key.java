import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Key.java 
 * A pickable token within cells that can be used to unlock token
 * doors.
 * 
 * @author Raghib
 *
 */
public class Key extends Item {
	/**
	 * Constructor for a key
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer Show if cell is walkable for player
	 * @param walkEnemy  Show if cell is walkable for enemy
	 * @param imageFile
	 * @param colour
	 * @throws FileNotFoundException
	 */
	Key(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile, String colour)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "key";
		setColour(colour);
	}
}
