import java.io.FileNotFoundException;

/**
 * Fire.java 
 * Fire cells
 * 
 * @author Sam
 *
 */
public class Fire extends Cell {
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */
	public Fire(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "fire";
	}
}
