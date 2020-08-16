import java.io.*;
import java.net.*;

/**
 * MessageOfTheDay.java
 *  Gets a message
 * 
 * @author Savan
 *
 */
public class MessageOfTheDay {

	/**
	 * Gets the message of the day from the given website
	 * 
	 * @param urlToRead
	 * @return message(message of the day)
	 * @throws Exception
	 */

	/**
	 * Sends a connection to the url,and take the given string from the website and
	 * decypts it
	 * 
	 * @param urlToRead
	 * @return the first website, decrypted message
	 * @throws Exception
	 */
	private static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		StringBuilder message = new StringBuilder();
		int asciiOfchar;
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		System.out.println(result);
		for (int i = 0; i < result.length(); i++) {
			char c = result.charAt(i);
			if (i % 2 == 0) {
				asciiOfchar = (int) c + 1;
				if (asciiOfchar == 91) {
					asciiOfchar = 65;
				}
				// System.out.println(asciiOfchar);
			} else {
				asciiOfchar = (int) c - 1;
				if (asciiOfchar == 64) {
					asciiOfchar = 90;
				}
			}
			message.append((char) asciiOfchar);

		}
		return message.toString();
	}

	/**
	 * This method takes the given url with the decryted message on the end, and
	 * requests a connection to get the full on message of the day
	 * 
	 * @param urlToRead
	 * @return the message of the day
	 * @throws Exception
	 */
	private static String returnSolution(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();

		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		String reusultMessage = result.toString();
		System.out.println(reusultMessage);

		return reusultMessage;
	}

	/**
	 * Gets the message string
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMessage() throws Exception {
		String message = getHTML("http://cswebcat.swan.ac.uk/puzzle");
		String url = "http://cswebcat.swan.ac.uk/message?solution=" + message;
		String rightMessage = returnSolution(url);
		return rightMessage.toString();

	}

}
