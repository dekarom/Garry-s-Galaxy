import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Leaderboard.java
 *  Creates and displays leaderboard
 * 
 * @author Sam,Christophe, Jordan
 *
 */
public class Leaderboard extends Application {

	private static final int WINDOW_WIDTH = 1000; // Size of the leaderboard window
	private static final int WINDOW_HEIGHT = 600;
	private Stage primaryStage;

	public Leaderboard(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	/**
	 * Starts and displays the leaderboard
	 * 
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Leaderboard");

		Label rank = new Label("RANK");
		rank.setFont(new Font("Arial Black", 32));
		rank.setTextFill(Color.web("#ffff00"));
		Label r1 = new Label("1");
		r1.setFont(new Font("Arial Black", 32));
		r1.setTextFill(Color.web("#ffff00"));
		Label r2 = new Label("2");
		r2.setFont(new Font("Arial Black", 32));
		r2.setTextFill(Color.web("#ffff00"));

		Label r3 = new Label("3");
		r3.setFont(new Font("Arial Black", 32));
		r3.setTextFill(Color.web("#ffff00"));

		Label u = new Label("USER & SCORE");
		u.setFont(new Font("Arial Black", 32));
		u.setTextFill(Color.web("#ffff00"));

		LeaderboardReader l = new LeaderboardReader("Resources\\highScores.txt");
		l.addHighScores();
		l.sortHighScores();
		ArrayList<String> user = l.getSorted();

		Label u1 = new Label(user.get(0));
		u1.setFont(new Font("Arial Black", 32));
		u1.setTextFill(Color.web("#ffff00"));

		Label u2 = new Label(user.get(1));
		u2.setFont(new Font("Arial Black", 32));
		u2.setTextFill(Color.web("#ffff00"));
		Label u3 = new Label(user.get(2));
		u3.setFont(new Font("Arial Black", 32));
		u3.setTextFill(Color.web("#ffff00"));

		Image img = new Image("file:Resources\\Images\\garrysgalaxybg.png");
		ImageView bg = new ImageView(img);

		// private final Button back = new Button("Back");

		StackPane root = new StackPane();

		root.getChildren().addAll(bg);

		root.getChildren().addAll(rank, r1, r2, r3);
		root.setMargin(rank, new Insets(0, 0, 280, -300));
		root.setMargin(r1, new Insets(0, 0, 150, -300));
		root.setMargin(r2, new Insets(0, 0, 0, -300));
		root.setMargin(r3, new Insets(0, 0, -150, -300));

		root.getChildren().addAll(u, u1, u2, u3);
		root.setMargin(u, new Insets(0, 0, 280, 150));
		root.setMargin(u1, new Insets(0, 0, 150, 150));
		root.setMargin(u2, new Insets(0, 0, 0, 150));
		root.setMargin(u3, new Insets(0, 0, -150, 150));

		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);

		primaryStage.show();

	}

	/**
	 * Launches the display
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);

	}
}
