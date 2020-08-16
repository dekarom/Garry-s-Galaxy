import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * container for all game data and operations to trigger interactions
 * @author Christophe and Sam
 */

public class GameState {
	
	//data about the state of the game
	private int levelNumber;
	private int length;
	private int height;
	private int score;
	private int time;
	private ArrayList<String> colourList;
	private ArrayList<String> directionList;
	private ArrayList<Integer> tokenList;
	private static ArrayList<ArrayList<Cell>> grid = new ArrayList<>();
	private GamePanel game;
	private Character garry;
	private LoadFile map;
	
	/**
	 * makes a gamestate
	 * @param gridWidth
	 * @param gridHeight
	 * @param tokenCount
	 * @param redKeyCount
	 * @param blueKeyCount
	 * @param greenKeyCount
	 * @param flipper
	 * @param fireBoots
	 * @param colourList
	 * @param directionList
	 * @param tokenList
	 * @param game
	 * @param garry
	 * @param map
	 */
	public GameState(int gridWidth,int gridHeight,
			int tokenCount, int redKeyCount, int blueKeyCount, int greenKeyCount, boolean flipper, boolean fireBoots,
			ArrayList<String> colourList, ArrayList<String> directionList, ArrayList<Integer> tokenList,GamePanel game,Character garry,LoadFile map) {

		this.garry = garry;
		this.levelNumber = levelNumber;
		this.length = length;
		this.height = height;
		this.score = score;
		this.time = time;
		this.colourList = colourList;
		this.directionList = directionList;
		this.tokenList = tokenList;

	}
	/**
	 *  various interactions when player steps on a cell
	 * @param x
	 * @param y
	 * @param gameMap
	 * @param garry
	 * @return the file to be played if there is one
	 * @throws FileNotFoundException
	 */
	public String checkItems(int x , int y , ArrayList<ArrayList<Cell>> gameMap, Character garry ) throws FileNotFoundException {
		if ( garry.getPosX() == gameMap.get(y).get(x).getXPos()
				&&  garry.getPosY() == gameMap.get(y).get(x).getYPos()) {

			switch (gameMap.get(y).get(x).getId()) {
			
			//walks on fire
			case ("fire"):
				if(  garry.getFlipper() == false) { 
					// player dies
					garry.setOutput("Game Over. burny burn");
					return "die";
				} 
			////walks on fire
			case ("water"):
			if(  garry.getFlipper() == false) { 
				// player dies
				garry.setOutput("Game Over. You Died! You Drowned");
				return "die";
			} 
			break;
			
			//walks on water
			case  ("flipper"):
				gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
				 garry.setFlipper(true);
				 garry.setOutput("picked up flipper");
				return "Itempickup2.wav";
				//walks on flipper	
			case ("fire boots"):
				gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
				garry.setOutput("picked up fire boots");
				 garry.setFireBoots(true);
				 return "Itempickup2.wav";
				//walks on fire boots
			case ("token"):
				gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
				
				 garry.setTokenCount( garry.getTokenCount() + 1);
				
				 garry.setOutput("picked up token");
				
				return "Itempickup2.wav";
				//walks on token
			case ("token door"):
				if ( garry.getTokenCount() >= 2) {
					System.out.println("00000000000??????????/00");
					gameMap.get(y).set(x, new TokenDoor(x, y, true, false, "tokenDoorOpen.png"));
					 garry.setTokenCount(0);
					 garry.setOutput("Token Door opened");
					 return "doors.wav";
				}
				
				break;
				//walks on goal
			case ("goal"):
				gameMap.get(y).set(x, new Goal(x, y, true, false, "goalReached.png"));
				garry.setOutput("Goal reached");
				return "Itempickup2.wav";
				//walks on key, gives depending on colour
			case ("key"):

				if (gameMap.get(y).get(x).getColour().equals("red")) {
					 garry.setRedKeyCount( garry.getRedKeyCount() + 1);
					gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
					garry.setOutput("red key picked up");
				} else if (gameMap.get(y).get(x).getColour().equals("blue")) {
					 garry.setBlueKeyCount( garry.getBlueKeyCount() + 1);
					gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
					garry.setOutput("blue key picked up");
				} else if (gameMap.get(y).get(x).getColour().equals("green")) {
					 garry.setGreenKeyCount( garry.getGreenKeyCount() + 1);
					gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
					garry.setOutput("green key picked up");
				}
				return "Itempickup2.wav";
				//walks on colour door gives depending on colour
			case ("colour door"):
				if (gameMap.get(y).get(x).getColour().equals("red") &&  garry.getRedKeyCount() > 0) {
					gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "redDoorOpen.png", "red"));
					gameMap.get(y).get(x).setColour("red");
					garry.setOutput("red door opened");
					return "doors.wav";
				} else if (gameMap.get(y).get(x).getColour().equals("blue")
						&&  garry.getBlueKeyCount() > 0) {
					gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "blueDoorOpen.png", "blue"));
					gameMap.get(y).get(x).setColour("blue");
					garry.setOutput("blue key picked up");
					return "doors.wav";
				} else if (gameMap.get(y).get(x).getColour().equals("green")
						&&  garry.getGreenKeyCount() > 0) {
					gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "greenDoorOpen.png", "green"));
					gameMap.get(y).get(x).setColour("green");
					garry.setOutput("green key picked up");
					return "doors.wav";
				}
			}
		}
		return "Main_Theme_v2.wav";
	}
}