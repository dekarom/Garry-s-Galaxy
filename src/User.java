import java.io.*;
import java.util.Scanner;

/**
 * User.java 
 * Methods and attributes for user profile
 *
 * @author Ben
 */
public class User {
	/**
	 * @param name
	 * @param totalscore
	 * @param levelscore
	 * @param highestLevel
	 * @param currentLevel
	 */
	private String name;
	private int totalscore;
	private int levelscore;
	private int highestLevel;
	private int currentLevel;

	/**
	 * Instantiates a new user.
	 *
	 * @param name username
	 */
	public User(String name) {
		this.name = name;
		this.totalscore = 0;
		this.highestLevel = 0;

	}

	/**
	 * Instantiates a new user.
	 *
	 * @param name username
	 */
	public void profileWriter(String name) throws FileNotFoundException {
		try {
			User tempUser = new User(name);
			String fileName = tempUser.getName();
			File newFile = new File("UserData\\" + fileName + ".txt");
			FileWriter fileW = new FileWriter(newFile);
			BufferedWriter buffW = new BufferedWriter(fileW);
			buffW.write(tempUser.getName() + " " + tempUser.getTotalScore() + " " + tempUser.getHighestLevel());
			buffW.close();
			// level 1

			File level1 = new File("UserData\\" + fileName + "Level1" + ".txt");
			FileWriter level1W = new FileWriter(level1);

			File level1In = new File("Levels\\test.txt");
			Scanner sc = new Scanner(level1In);
			PrintWriter printer = new PrintWriter(level1);
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				printer.write(s + "\n");
			}
			printer.flush();

			File level2 = new File("UserData\\" + fileName + "Level2" + ".txt");
			FileWriter level2W = new FileWriter(level2);
			File level2In = new File("Levels\\test.txt");
			Scanner sc2 = new Scanner(level2In);
			PrintWriter printer2 = new PrintWriter(level2);
			while (sc2.hasNextLine()) {
				String s = sc2.nextLine();
				printer2.write(s + "\n");
			}
			printer2.flush();

			File level3 = new File("UserData\\" + fileName + "Level3" + ".txt");
			FileWriter level3W = new FileWriter(level3);
			File level3In = new File("Levels\\test.txt");
			Scanner sc3 = new Scanner(level3In);
			PrintWriter printer3 = new PrintWriter(level3);
			while (sc3.hasNextLine()) {
				String s = sc3.nextLine();
				printer3.write(s + "\n");
			}
			printer3.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the user's total score.
	 *
	 * @return the total score
	 */
	public int getTotalScore() {
		return totalscore;
	}

	/**
	 * Sets the total score.
	 *
	 * @param totalscore the new total score
	 */
	public void setTotalScore(int totalscore) {
		this.totalscore = totalscore;
	}

	/**
	 * Gets the level score for current level.
	 *
	 * @return the level score
	 */
	public int getLevelScore() {
		return levelscore;
	}

	/**
	 * Gets the user's total score.
	 *
	 * @return the total score
	 */
	public String getName() {
		return name;
	}

	public int getHighestLevel() {
		return highestLevel;
	}

	public void setHighestLevel(int highestLevel) {
		this.highestLevel = highestLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

}
