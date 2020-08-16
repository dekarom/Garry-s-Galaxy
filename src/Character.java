import java.util.ArrayList;

/**
 * Character.java
 * Class for a single character
 * 
 * @author Ben
 * 
 */
public class Character {

	private int posX; // X coordinate of player
	private int posY; // Y coordinate of player
	private boolean fireBoots; // If true player can walk over fire tiles
	private boolean flipper; // If true player can walk over water tiles
	private int tokenCount; // Amount of tokens the player currently has
	private int redKeyCount; // Amount of red keys player currently has
	private int blueKeyCount; // Amount of blue keys player currently has
	private int greenKeyCount; // Amount of green keys player currently has
	private String output;

	public Character(int posX, int posY, boolean fireBoots, boolean flipper, int tokenCount, int redKeyCount,
			int blueKeyCount, int greenKeyCount) {
		this.posX = posX;
		this.posY = posY;
		this.fireBoots = fireBoots;
		this.flipper = flipper;
		this.tokenCount = tokenCount;
		this.redKeyCount = redKeyCount;
		this.blueKeyCount = blueKeyCount;
		this.greenKeyCount = greenKeyCount;
	}

	/**
	 * Method to reset inventory at the start of a level
	 */
	public void restart() {
		// setPos(0,0);
		fireBoots = false;
		flipper = false;
		tokenCount = 0;
		redKeyCount = 0;
		blueKeyCount = 0;
		greenKeyCount = 0;

	}

	/**
	 * Sets the character position.
	 *
	 * @param posx the x coordinate
	 * @param posy the y coordinate
	 */
	public void setCharacterPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Getter for x position
	 *
	 * @return posx
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * Getter for y position.
	 *
	 * @return posy
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Returns true if the player has picked up fire boots item
	 *
	 * @return fireBoots
	 */
	public boolean getFireBoots() {
		return fireBoots;
	}

	/**
	 * Sets the fire boots.
	 *
	 * @param fireBoots the fire boots item
	 */
	public void setFireBoots(boolean fireBoots) {
		this.fireBoots = fireBoots;
	}

	/**
	 * Returns true if player has picked up flipper item
	 *
	 * @return flipper
	 */
	public boolean getFlipper() {
		return this.flipper;
	}

	/**
	 * Setter for flipper.
	 *
	 * @param flipper the flipper item
	 */
	public void setFlipper(boolean flipper) {
		this.flipper = flipper;
	}

	/**
	 * Gets the token count.
	 *
	 * @return the token count
	 */
	public int getTokenCount() {
		return this.tokenCount;
	}

	/**
	 * Sets the token count.
	 *
	 * @param tokenCount the new token count
	 */
	public void setTokenCount(int tokenCount) {
		this.tokenCount = tokenCount;
	}

	/**
	 * Gets the red key count.
	 *
	 * @return redKeyCount
	 */
	public int getRedKeyCount() {
		return this.redKeyCount;
	}

	/**
	 * Sets the red key count.
	 *
	 * @param redKeyCount the new red key count
	 */
	public void setRedKeyCount(int redKeyCount) {
		this.redKeyCount = redKeyCount;
	}

	/**
	 * Gets the blue key count.
	 *
	 * @return blueKeyCount
	 */
	public int getBlueKeyCount() {
		return this.blueKeyCount;
	}

	/**
	 * Sets the blue key count.
	 *
	 * @param blueKeyCount the new blue key count
	 */
	public void setBlueKeyCount(int blueKeyCount) {
		this.blueKeyCount = blueKeyCount;
	}

	//
	/**
	 * Gets the green key count.
	 *
	 * @return greenKeyCount
	 */
	public int getGreenKeyCount() {
		return this.greenKeyCount;
	}

	/**
	 * Sets the green key count.
	 *
	 * @param greenKeyCount the new green key count
	 */
	public void setGreenKeyCount(int greenKeyCount) {
		this.greenKeyCount = greenKeyCount;
	}

	public ArrayList<ArrayList<String>> getCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return this.output;
	}

	// Move method?

}
