import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Ground.java
 *  Models ground cell.
 * 
 * @author Savan
 *
 */
public class Ground extends Cell {
	/**
	 * Constructor for a ground
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */

	public Ground(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "dirt";

	}

	/**
	 * When called this method sets the given enemy/direction on this cell.
	 * 
	 * @param enemy
	 * @param direction
	 * @throws FileNotFoundException
	 */
	public void setEnemy(char enemy, String direction) throws FileNotFoundException {
		if (enemy == 'e') {
			this.wallEnemy = new WallFollowing(xPos, yPos);
			this.cellId = "wall following";

		} else if (enemy == 's') {
			this.straightEnemy = new StraightLine(xPos, yPos, direction);
			this.cellId = "straight line";
		} else if (enemy == 'p') {
			this.dumbEnemy = new Dumb(xPos, yPos);
			this.cellId = "dumb enemy";
		} else if (enemy == 'q') {
			this.smartEnemy = new Smart(xPos, yPos);
			this.cellId = "smart enemy";

		}

	}
}
