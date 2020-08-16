import java.io.FileNotFoundException;

/**
 * Wall.java
 * Models a single wall cell
 * 
 * @author Sam
 *
 */
public class Wall extends Cell {
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
	public Wall(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "wall";
	}

}
