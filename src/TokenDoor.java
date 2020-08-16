import java.io.FileNotFoundException;

/**
 * TokenDoor.java

 * The cell token door
 *
 * @author Raghib
 *
 */
/**
 * Instantiates a new tokenDoor.
 *
 * @param xPos       the x pos
 * @param yPos       the y pos
 * @param walkPlayer player can walk or not
 * @param walkEnemy  enemy can walk or not
 * @param imageFile  the image file
 * @throws FileNotFoundException the file not found exception
 */
public class TokenDoor extends Cell {
	private int tokensNeeded;

	public TokenDoor(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.tokensNeeded = tokensNeeded;
		this.cellId = "token door";
	}

}
