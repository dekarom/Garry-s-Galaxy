import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * SaveFile.Jav
 * Saves the current level to a text file
 *
 * @author Savan Patel
 */

public class SaveFile {
	/**
	 * @param gameMap       is a 2d ArrayList which contains the current map, that
	 *                      needs to be saved
	 * @param colourList    is 2d ArrayList which contains the current map, colour
	 *                      list
	 * @param directionList is 2d ArrayList which contains the current map,
	 *                      direction list
	 * @param tokenList     is 2d ArrayList which contains the current map, token
	 *                      list
	 * @param teleList      is 2d ArrayList which contains the current map,
	 *                      teleporters list
	 */
	private ArrayList<ArrayList<Cell>> gameMap = new ArrayList<>();
	private ArrayList<String> colourList = new ArrayList<>();
	private ArrayList<String> directionList = new ArrayList<>();
	private ArrayList<Integer> tokenList = new ArrayList<>();
	private ArrayList<Integer> teleList = new ArrayList<>();

	/**
	 * This constructor, takes in a a few variables, such as
	 * gameMap,colourList,directionList,tokenList,
	 * teleList,garray,hightSize,widthSize and user
	 * 
	 * @throws IOException
	 */
	public SaveFile(ArrayList<ArrayList<Cell>> gameMap, ArrayList<String> colourList, ArrayList<String> directionList,
			ArrayList<Integer> tokenList, ArrayList<Integer> teleList, Character garry, int hightSize, int widthSize,
			User user) throws IOException {
		generateFile(gameMap, colourList, directionList, tokenList, teleList, fileName(user), garry.getTokenCount(),
				garry.getRedKeyCount(), garry.getBlueKeyCount(), garry.getGreenKeyCount(), garry.getFlipper(),
				garry.getFireBoots(), garry.getPosX() + 1, garry.getPosY() + 1, hightSize, widthSize);
	}

	/**
	 * Creates the name of string where the file needs to be saved
	 * 
	 * @param username - takes current users, so it knows where to save it
	 * @returns string of filename
	 */
	public static String fileName(User username) {
		String filename = ("UserData\\" + username.getName() + "Level" + username.getCurrentLevel() + ".txt");
		return filename;

	}

	/**
	 * Removes the last digit on a given string
	 * 
	 * @param str - takes string
	 * @return updated strings
	 */
	public static String removeLastDigit(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * Creates a save of the current map, in game panel, saving it the same ways it
	 * loaded in,
	 *
	 * 
	 * @param gameMap       is a 2d ArrayList which contains the current map, that
	 *                      needs to be saved
	 * @param colourList    is 2d ArrayList which contains the current map, colour
	 *                      list
	 * @param directionList is 2d ArrayList which contains the current map,
	 *                      direction list
	 * @param tokenList     is 2d ArrayList which contains the current map, token
	 *                      list
	 * @param teleList      is 2d ArrayList which contains the current map,
	 *                      teleporters list
	 * @param               filename, a string containing the information on what
	 *                      file to overwrite
	 * @param tokenCount    the amount of tokens the user has
	 * @param redKeyCount   the amount of red keys the user has
	 * @param blueKeyCount  the amount of blue keys the user has
	 * @param greenKeyCount the amount of green keys the user has
	 * @param               hasFlipper, a boolean checking if the user has the
	 *                      flippers or not
	 * @param               hasFireBoots, a boolean checking if the user has the
	 *                      fire boots or not
	 * @param               garrysXpos, a int of garrys last x before the save
	 * @param               garrysYpos, a int of garrys last y before the save
	 * @param hightSize     a int of the current hight of the map
	 * @param widthSize     a int of the current width of the map
	 */
	private static void generateFile(ArrayList<ArrayList<Cell>> gameMap, ArrayList<String> colourList,
			ArrayList<String> directionList, ArrayList<Integer> tokenList, ArrayList<Integer> teleList, String filename,
			int tokenCount, int redKeyCount, int blueKeyCount, int greenKeyCount, Boolean hasFlipper,
			Boolean hasFireBoots, int garryXpos, int garryYpos, int hightSize, int widthSize) throws IOException {
		int colourSizeArray = 0;
		int directionSizeArray = 0;
		int tokenSizeArray = 0;
		int teleSizeArray = 0;
		String tempColourString = "";
		String tempDirectionString = "";
		String tempTokenString = "";
		String tempTeleString = "";
		File file = new File(filename);
		if (file.createNewFile())// Checks if the file alread exists or nto
		{
			System.out.println("File is created!");
		} else {
			System.out.println("Overwriting file");
		}
		FileWriter writer = new FileWriter(file);
		writer.write(hightSize + "," + widthSize);
		writer.write(garryXpos + "," + garryYpos + "\n");
		writer.write(tokenCount + "," + redKeyCount + "," + blueKeyCount + "," + greenKeyCount + "," + hasFlipper + ","
				+ hasFireBoots + "\n");

		while (colourSizeArray < colourList.size()) {
			tempColourString = tempColourString + colourList.get(colourSizeArray).toString() + ",";
			colourSizeArray++;
		}
		while (directionSizeArray < directionList.size()) {
			tempDirectionString = tempDirectionString + directionList.get(directionSizeArray).toString() + ",";
			directionSizeArray++;
		}

		while (tokenSizeArray < tokenList.size()) {
			tempTokenString = tempTokenString + tokenList.get(tokenSizeArray).toString() + ",";
			tokenSizeArray++;
		}

		while (teleSizeArray < teleList.size()) {
			tempTeleString = tempTeleString + teleList.get(teleSizeArray).toString() + ",";
			teleSizeArray++;
		}
		writer.write(removeLastDigit(tempColourString) + "\n");
		writer.write(removeLastDigit(tempDirectionString) + "\n");
		writer.write(removeLastDigit(tempTokenString) + "\n");
		writer.write(removeLastDigit(tempTeleString) + "\n");
		// Reads each cell in gameMap, and saves the right symbol based on its cellID
		for (int i = 0; i < hightSize; i++) {
			for (int j = 0; j < widthSize; j++) {
				String cellType = gameMap.get(i).get(j).getId();
				System.out.print(gameMap.get(i).get(j).getId());

				switch (cellType) {
				case "wall":
					writer.write('#');
					break;
				case "dirt":
					writer.write('o');
					break;
				case "fire":
					writer.write('f');
					break;
				case "water":
					writer.write('w');
					break;
				case "flipper":
					writer.write('b');
					break;
				case "goal":
					writer.write('g');
					break;
				case "teleporter":
					writer.write('t');
					break;
				case "key":
					writer.write('k');
					break;
				case "token":
					writer.write('c');
					break;
				case "fire boots":
					writer.write('j');
					break;
				case "key door":
					writer.write('r');
					break;
				case "straight line":
					writer.write('s');
					break;
				case "wall following":
					writer.write('e');
					break;
				case "smart enemy":
					writer.write('q');
					break;
				case "dumb enemy":
					writer.write('p');
					break;
				case "colour door":
					writer.write('r');
					break;
				case "token door":
					writer.write('d');
					break;

				}
				System.out.print(i + "\n");

			}
			writer.write('\n');

		}
		writer.close();
	}

}
