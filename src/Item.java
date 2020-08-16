import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Item.java 
 * a pickable item within a cell
 * 
 * @author Raghib
 *
 */
public class Item extends Cell {

	private boolean playerVisited; // keeps track if an item has been visited.

	/**
	 * Constructor for Items
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */
	public Item(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.playerVisited = playerVisited;

	}
}
