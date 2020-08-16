import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Cell.java
 * a single tile within the game
 * 
 * @author Sam
 *
 */
public class Cell {
	protected int yLink;
	protected int xLink;
	protected int xPos;
	protected int yPos;
	protected boolean walkPlayer;
	protected boolean walkEnemy;
	protected String imageFile;
	protected Image picture;
	protected String cellId;
	protected Smart smartEnemy;
	protected Dumb dumbEnemy;
	protected WallFollowing wallEnemy;
	protected StraightLine straightEnemy;
	protected String colour;

	/**
	 * Constructor for the cell class.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param walkPlayer
	 * @param walkEnemy
	 * @param imageFile
	 * @throws FileNotFoundException
	 */
	public Cell(int xPos, int yPos, boolean walkPlayer, boolean walkEnemy, String imageFile)
			throws FileNotFoundException {
		this.xPos = xPos;
		this.yPos = yPos;
		this.walkPlayer = walkPlayer;
		this.walkEnemy = walkEnemy;
		this.imageFile = imageFile;
		this.picture = new Image(new FileInputStream("Resources/Images/" + imageFile));
	}

	// Getters and setters
	public String getColour() {
		return this.colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Smart getSmart() {
		return this.smartEnemy;
	}

	public Dumb getDumb() {
		return this.dumbEnemy;
	}

	public WallFollowing getWallFollowing() {
		return this.wallEnemy;
	}

	public StraightLine getStraightLine() {
		return this.straightEnemy;
	}

	public void setSmart(Smart enemy) {
		this.smartEnemy = enemy;
		this.cellId = "smart enemy";

	}

	public void setDumb(Dumb enemy) {
		this.dumbEnemy = enemy;
		this.cellId = "dumb enemy";
	}

	public void setWallFollowing(WallFollowing enemy) {
		this.wallEnemy = enemy;
		this.cellId = "wall following";
	}

	public void setStraightLine(StraightLine enemy) {
		this.straightEnemy = enemy;
		this.cellId = "straight line";
	}

	public String getId() {
		return this.cellId;
	}

	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}

	public boolean getWalkPlayer() {
		return this.walkPlayer;
	}

	public boolean getWalkEnemy() {
		return this.walkEnemy;
	}

	public void setWalkPlayer(boolean walk) {
		this.walkPlayer = walk;
	}

	public void setWalkEnemy(boolean walk) {
		this.walkEnemy = walk;
	}

	public Image getImage() {
		return this.picture;
	}

	public int getYLink() {
		return this.yLink;
	}

	public int getXLink() {
		return this.xLink;
	}
}
