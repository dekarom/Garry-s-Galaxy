import java.io.FileNotFoundException;

/**
 * Goal.java 
 * Class that modells a goal cell
 * 
 * @author Sam
 *
 */
public class Goal extends Cell {

	public Goal(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "goal";
	}
}
