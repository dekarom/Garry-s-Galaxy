import java.io.FileNotFoundException;

/**
 * Water.java 
 * Single water cell
 * 
 * @author Sam
 *
 */
public class Water extends Cell {
	/**
	 * Constructor
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */
	public Water(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "water";
	}
}
