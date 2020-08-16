import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Flipper.java 
 * a pickable flipper item within cells that can be used to walk on
 * water cells.
 * 
 * @author Raghib
 *
 */
public class Flipper extends Item {

	/**
	 * Constructor for a flipper
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */
	public Flipper(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "flipper";

	}
}
