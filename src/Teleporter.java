import java.io.FileNotFoundException;

/**
 * Teleporter.java
 * teleporter cells
 * @author Sam
 *
 */
public class Teleporter extends Cell{
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @param xlink a link to next teleporter(x value)
	 * @param ylink a link to next teleporter(y value)
	 * @throws FileNotFoundException
	 */
	Teleporter teleporter;
	public Teleporter(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile,int xlink,int ylink) throws FileNotFoundException {
		super(xPos, yPos, walkPlayer, walkEnemy, imageFile);
		this.cellId = "teleporter";
		this.yLink = ylink;
		this.xLink = xlink;
	}

}
