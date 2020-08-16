import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Token.java
 *  a pickable token within cells that can be used to unlock token
 * doors.
 *
 * @author Raghib
 *
 */
public class Token extends Item {

	/**
	 * Instantiates a new token.
	 *
	 * @param xPos       the x pos
	 * @param yPos       the y pos
	 * @param walkPlayer player can walk or not
	 * @param walkEnemy  enemy can walk or not
	 * @param imageFile  the image file
	 * @throws FileNotFoundException the file not found exception
	 */
	public Token(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "token";
	}

}
