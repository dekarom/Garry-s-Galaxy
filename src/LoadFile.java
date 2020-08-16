import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * LoadFile.java 
 * This class loads a map from a files
 * 
 * @author Savan;
 *
 */

public class LoadFile {

	/**
	 * @param colourIndexKey  - Stores the number of key in the map
	 * @param colourIndexDoor - Stores the number of doors in the map
	 * @param direction       Index - Stores the amount of direction in the map
	 * @param teleIndex       - Stores the amount of teleporters in the map
	 * @param scan            - Creates a scanner
	 * @param gameMapReturn   - a 2dArray that contains the map
	 * @param gameMapCreate   - an empty 2dArray that stores the temp map
	 * @param colourList      - A array that contains the colours in a string list
	 * @param directionList   - A array that contains the directios of straight line
	 *                        enemies
	 * @param takenList       - Contains the token count needed for a token door
	 * @param                 telelist- Contain entering and exiting teleporter
	 *                        coordinates
	 * @param filename
	 * @param highSize
	 * @param widthSize
	 * @parm tokenCount Shows current player token count
	 */
	private int colourIndexKey = 0;
	private int colourIndexDoor = 0;
	private int directionIndex;
	private int teleIndex;
	private int colourIndex;
	private static Scanner scan = new Scanner(System.in);
	private static ArrayList<ArrayList<Cell>> gameMapReturn = new ArrayList<>();
	private static ArrayList<ArrayList<Cell>> gameMapCreate = new ArrayList<>();
	private static ArrayList<String> colourList = new ArrayList<>();
	private static ArrayList<String> directionList = new ArrayList<>();
	private static ArrayList<Integer> tokenList = new ArrayList<>();
	private static ArrayList<Integer> teleList = new ArrayList<>();
	private static String filename;
	private static int hightSize;
	private static int widthSize;
	private static int tokenCount;

	public int getColourIndex() {
		return colourIndex;
	}

	public static Scanner getScan() {
		return scan;
	}

	public static ArrayList<ArrayList<Cell>> getGameMapReturn() {
		return gameMapReturn;
	}

	public static ArrayList<ArrayList<Cell>> getGameMapCreate() {
		return gameMapCreate;
	}

	public static String getFilename() {
		return filename;
	}

	private boolean fileFound = true;
	private static int redKeyCount;
	private static int greenKeyCount;
	private static int blueKeyCount;
	private static boolean hasFlipper;
	private static boolean hasFireBoots;
	private static int playerXPos;
	private static int playerYPos;

	/**
	 * Constuctor
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public LoadFile(String filename) throws FileNotFoundException {

		this.filename = filename;
		if (readFile(filename) == null) {
			this.fileFound = false;

		} else {
			gameMapReturn = readToArry(readFile(filename), colourList, directionList, tokenList, teleList);
		}
	}

	/**
	 * Reads a map file
	 * 
	 * @param filename
	 * @return Map to the game state
	 */
	public static Scanner readFile(String filename) {
		File file = new File(filename);
		try {
			Scanner in = new Scanner(file); // saves the information from the file into scanner in
			scan = in;
			return in;
		} catch (FileNotFoundException e) {
			System.out.println("No file with the name: " + filename);
			return null;
		}
	}

	public ArrayList<ArrayList<Cell>> readToArry(Scanner in, ArrayList<String> colourList,
			ArrayList<String> directionList, ArrayList<Integer> tokenList, ArrayList<Integer> teleList)
			throws FileNotFoundException {

		ArrayList<ArrayList<Cell>> gameMap = new ArrayList<>();
		String mapLine = in.nextLine();
		System.out.println(mapLine);
		Scanner line = new Scanner(mapLine);
		line.useDelimiter(",");
		hightSize = line.nextInt();
		widthSize = line.nextInt();
		for (int lengthOfList = 1; lengthOfList < hightSize; lengthOfList++) {

			gameMap.add(new ArrayList<Cell>());
		}
		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");

		playerXPos = line.nextInt();
		playerYPos = line.nextInt();

		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");

		tokenCount = line.nextInt();
		redKeyCount = line.nextInt();
		greenKeyCount = line.nextInt();
		blueKeyCount = line.nextInt();
		hasFlipper = line.nextBoolean();
		hasFireBoots = line.nextBoolean();

		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");
		while (line.hasNext()) {
			colourList.add(line.next());
		}
		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");
		while (line.hasNext()) {
			directionList.add(line.next());

		}
		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");
		while (line.hasNext()) {

			tokenList.add(line.nextInt());

		}
		mapLine = in.nextLine();
		line = new Scanner(mapLine);
		line.useDelimiter(",");
		System.out.println(mapLine);
		while (line.hasNext()) {
			teleList.add(line.nextInt());

		}
		Ground cell;
		char cellSymbol = 'A'; // make private
		for (int j = 0; j < hightSize; j++) {

			mapLine = in.nextLine();
			ArrayList<Cell> tempArray = new ArrayList<>();
			for (int i = 0; i < widthSize; i++) {

				// Change to switch statement
				cellSymbol = mapLine.charAt(i);
				switch (cellSymbol) {
				case '#':
					tempArray.add(new Wall(i, j, false, false, "wall.png"));
					break;
				case 'o':
					tempArray.add(new Ground(i, j, true, true, "dirt.png"));
					break;
				case 'w':
					tempArray.add(new Water(i, j, true, false, "water.png"));
					break;
				case 'f':
					tempArray.add(new Fire(i, j, true, false, "fire.png"));
					break;
				case 'b':
					tempArray.add(new Flipper(i, j, true, false, "flipper.png"));
					break;
				case 't':
					System.out.println(teleList.get(teleIndex));
					tempArray.add(new Teleporter(i, j, true, false, "teleporter.png", teleList.get(teleIndex),
							teleList.get(teleIndex) + 1));
					teleIndex += 2;
					break;
				case 'k':
					tempArray.add(new Key(i, j, true, false, colourList.get(colourIndexKey) + "Key.png",
							colourList.get(colourIndexKey)));
					colourIndexKey++;
					break;
				case 'c':
					tempArray.add(new Token(i, j, true, false, "token.png"));
					break;
				case 'j':
					tempArray.add(new FireBoots(i, j, true, false, "fireboots.png"));
					break;
				case 'g':
					tempArray.add(new Goal(i, j, true, false, "goal.png"));
					break;
				case 'r':
					tempArray.add(new ColourKeyDoor(i, j, false, false, colourList.get(colourIndexDoor) + "Door.png",
							colourList.get(colourIndexDoor)));
					colourIndexDoor++;
					break;
				case 'd':
					tempArray.add(new TokenDoor(i, j, false, false, "tokenDoor.png"));
					break;
				case 's':
					System.out.println("***" + directionList.get(directionIndex) + "***");
					cell = new Ground(i, j, true, false, "Straight_Line.png");
					if (directionList.get(directionIndex).equals("up")) {
						cell.setEnemy('s', "down");
					} else if (directionList.get(directionIndex).equals("down")) {
						cell.setEnemy('s', "up");
					} else if (directionList.get(directionIndex).equals("right")) {
						cell.setEnemy('s', "right");
					} else if (directionList.get(directionIndex).equals("left")) {
						cell.setEnemy('s', "left");
					}
					tempArray.add(cell);
					break;
				case 'e':
					cell = new Ground(i, j, true, false, "Wall_Following.png");
					cell.setEnemy('e', "");

					tempArray.add(cell);
					break;
				case 'p':
					cell = new Ground(i, j, true, false, "Dumb.png");
					cell.setEnemy('p', "");

					tempArray.add(cell);
					break;
				case 'q':
					cell = new Ground(i, j, true, false, "Smart.png");
					cell.setEnemy('q', "");

					tempArray.add(cell);
					// gameMap.get(i).get(j).setEnemy(new Smart(j, j));
					break;
				default:
					System.out.println("This cell isn't recognised in loadFile");
					tempArray.add(new Cell(i, j, true, true, "dirt.png"));
				}
			}
			gameMapCreate.add(tempArray);
			for (int i = 0; i < gameMap.size(); i++) {
			}

		}
		return gameMapCreate;
	}

	// Getters for game map parameters
	public static ArrayList<ArrayList<Cell>> getGameMap() {
		System.out.println("test*");
		return gameMapReturn;
	}

	public ArrayList<String> getColourList() {
		return colourList;
	}

	public ArrayList<String> getDirectionList() {
		return directionList;
	}

	public ArrayList<Integer> getTokenList() { // array in case if we want more token doors
		return tokenList;
	}

	public ArrayList<Integer> getTeleList() {
		return teleList;
	}

	public int getHightSize() {
		return hightSize;

	}

	public int getWidthSize() {
		return widthSize;

	}

//
	public static int getTokenCount() {
		return tokenCount;
	}

	public static int getRedKeyCount() {
		return redKeyCount;
	}

	public static int getGreenKeyCount() {
		return greenKeyCount;
	}

	public static int getBlueKeyCount() {
		return blueKeyCount;
	}

	public static boolean getHasFlipper() {
		return hasFlipper;
	}

	public static boolean getHasFireBoots() {
		return hasFireBoots;
	}

	public static int getPlayerXPox() {
		return playerXPos;
	}

	public static int getPlayerYPox() {
		return playerYPos;
	}

	public String getName() {
		return filename;
	}

	public Boolean getFileFound() {
		return fileFound;
	}

}
