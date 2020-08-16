import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.layout.Pane;

/**
 * LeaderboardReader.java 
 * Reads a file and sorts scores.
 * 
 * @author Sam, Jordan
 *
 */
public class LeaderboardReader {
	/**
	 * @param filename
	 * @param sendScore        Passes a line of a file
	 * @param top
	 * @param sorter           Temporery list used for sorting.
	 * @param sorteName        Sorts names
	 * @param highScores       Contains unsorted name/highscores
	 * @param sortedHighScores Sorted array list
	 * 
	 */
	private String filename;
	private String sendScore;
	private ArrayList<Integer> top = new ArrayList<>();
	private ArrayList<Integer> sorter = new ArrayList<>();
	private ArrayList<String> sorterName = new ArrayList<>();
	private ArrayList<ArrayList<String>> highScores = new ArrayList<>();
	private ArrayList<String> sortedHighScores = new ArrayList<>();

	/**
	 * Constructor for file reader
	 * 
	 * @param filename
	 */
	public LeaderboardReader(String filename) {
		this.filename = filename;
	}

	/**
	 * Reads a file and adds to an array list
	 */
	public void addHighScores() {

		Scanner in = readFile(filename);
		String line;

		ArrayList<String> temp = new ArrayList<>();

		String nextline;
		String a = "";
		String b = "";
		String c = "";
		String word = "";
		Scanner scan = in;
		int hold = 0;
		while (scan.hasNext()) {
			a = "";
			b = "";
			c = "";
			word = "";
			nextline = in.nextLine();
			temp = new ArrayList<>();
			for (int i = 0; i < nextline.length(); i++) {
				if (nextline.charAt(i) == ' ') {
					if (a == "") {
						a = word;
						temp.add(a);
						word = "";
					} else if (b == "") {
						b = word;
						temp.add(b);
						word = "";
					} else if (c == "") {

						c = word;
						temp.add(c);
						word = "";
					}
				} else {

					word += nextline.charAt(i);
				}

			}

			highScores.add(temp);

		}

		sortHighScores();
	}

	/**
	 * Sorts the array list of stored scores and names.
	 * 
	 * @return
	 */
	public String sortHighScores() {
		int first = 0;
		String firstName = "";
		int second = 0;
		String secondName = "";
		int third = 0;
		String thirdName = "";
		int temp = 0;
		int tempPos = 0;
		for (int i = 0; i < highScores.size(); i++) {
			sorterName.add(highScores.get(i).get(0));
			sorter.add(Integer.parseInt(highScores.get(i).get(1)));
		}
		System.out.println("test2");
		for (int i = 0; i < sorter.size(); i++) {
			if (temp < sorter.get(i)) {
				temp = sorter.get(i);
				firstName = sorterName.get(i);
				tempPos = i;
			}
		}
		first = temp;
		sorter.remove(tempPos);
		temp = 0;

		for (int i = 0; i < sorter.size(); i++) {
			if (temp < sorter.get(i)) {
				temp = sorter.get(i);
				secondName = sorterName.get(i);
				tempPos = i;
			}
		}
		second = temp;
		sorter.remove(tempPos);
		temp = 0;
		for (int i = 0; i < sorter.size(); i++) {
			if (temp < sorter.get(i)) {
				temp = sorter.get(i);
				thirdName = sorterName.get(i);
				tempPos = i;
			}
		}
		third = temp;
		sorter.remove(tempPos);
		temp = 0;
		this.sortedHighScores.add(firstName + " " + first);
		this.sortedHighScores.add(secondName + " " + second);
		this.sortedHighScores.add(thirdName + " " + third);

		sendScore = firstName + " " + first + "\n" + secondName + " " + second + "\n" + thirdName + " " + third;
		this.sortedHighScores.add(sendScore);
		return sendScore;
	}

	// Getters for array lists.
	public ArrayList<String> getSorted() {
		return this.sortedHighScores;
	}

	public ArrayList<String> getSortHighScore() {
		return sortedHighScores;
	}

	public String getHighScores() {
		return this.sendScore;
	}

	/**
	 * Scanner that read a file
	 * 
	 * @param filename
	 * @return file inforamtion
	 */
	public Scanner readFile(String filename) {
		File file = new File(filename);
		try {
			Scanner in = new Scanner(file); // saves the information from the file into scanner in
			return in;
		} catch (FileNotFoundException e) {
			System.out.println("No file with the name: " + filename);
			System.exit(0);
			return null;
		}
	}

}