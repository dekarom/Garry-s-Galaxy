import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 *Main.java
 * Interactive 2D tile based puzzle game based of chips challenge using javafx
 * 
 * @author Ben
 */

public class Main extends Application {

	private static GamePanel game;
	private static final int INDEFINITE = -1;
	private static Stage gameScene; // Stage to play the game on
	private static Stage firstScene; // Stage for the main menu
	private Pane root;
	Boolean validation;

	/* 
	 * Opens up starting menu panel
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(loadScreenOne(), 1000, 500);
		firstScene = stage;
		stage.setScene(scene);
		stage.show();
		
	}

	/**
	 * The main method to launch the game.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The loading screen for the 
	 *
	 * @return vBox shows the vBox
	 * @throws FileNotFoundException If User file is not found throws exception
	 */
	public VBox loadScreenOne() throws FileNotFoundException {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);

		// User is asked to enter their username here
		final TextField userInput = new TextField();
		userInput.setText("Input Username");
		userInput.setPrefSize(280, 50);
		userInput.onActionProperty();

		final Button load = new Button("Load Screen"); // Button to Load Screen
		final Button start = new Button("Start");		// Button for user to launch the game after entering name
		Text text2 = new Text("Enter your UserName to begin:");

		HBox hbox = new HBox(userInput, start);

		start.setOnAction(e -> {
			LoadFile map = null;
			// try {
			String userText = userInput.getText();
			User user = new User(userText);
			
			validation = false;
			// Validation is to check whether the file for this user exists and therefore whether the user exists
			while (validation != true) {

				try {

					map = new LoadFile("UserData\\" + userText + "Level1" + ".txt");
				} catch (FileNotFoundException e1) {
					System.out.print("Savan broke it again");
					e1.printStackTrace();
				}
				// sets validation to true if user exists to break the loop
				validation = true;
			}
			
			Character Garry = new Character(map.getPlayerXPox(), map.getPlayerYPox(), map.getHasFireBoots(),
					map.getHasFlipper(), map.getTokenCount(), map.getRedKeyCount(), map.getBlueKeyCount(),
					map.getGreenKeyCount());

			game = new GamePanel(Garry, map, user);
			try {
				root = game.buildGUI();
			} catch (Exception e2) {
				
				e2.printStackTrace();
				System.out.println("buildGui not found");
			}
			String song = new File("Resources/Music/Main_Theme_v2.wav").toURI().toString();
			MediaPlayer player = new MediaPlayer(new Media(song));
			player.setCycleCount(INDEFINITE); //Loops the in game music indefinitely
			player.play();
		
			try {
				GamePanel.getImages();
			} catch (FileNotFoundException e1) {
				// FileNotFoundException if Main_Theme_v2 does not exist
				e1.printStackTrace();
			}
			
			if (map.getFileFound() == true) {

				try {
					gameStart(game);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			
			// Changes text and colour if there is no user profile with that username
			text2.setText("Enter an existing username");
			text2.setFill(Color.RED);

		});
		VBox vbox = new VBox(text2, hbox);

		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(5);
		vbox.setPrefSize(400, 100);

		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		hbox.setPrefSize(400, 60);
//
		final Button show = new Button("Show Leaderboard"); // Button from main menu to show the leaderboard
		
		// Action for press of "Show Leaderboard" button
		show.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				try {
					System.out.println("leaderboard");
					Leaderboard showScores = new Leaderboard(firstScene);
					showScores.start(firstScene);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// Button to create a new Player
		final Button newPlayer = new Button("Create New Account");
		
		show.setPrefSize(220, 35);
		show.setStyle("-fx-border-color: #a9a9a9; -fx-border-width: 1;");
		
		newPlayer.setPrefSize(220, 35);
		newPlayer.setStyle("-fx-border-color: #a9a9a9; -fx-border-width: 1;");
		
		// Action for press of "Create new Account"
		newPlayer.setOnAction(new EventHandler<ActionEvent>() {

			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					newPlayer.getScene().setRoot(PickPlayerScreen()); // Take user to a screen where they can create a new account
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		text2.setFont(new Font("Arial", 40));
		text2.setFill(Color.WHITE);
		text2.setStroke(Color.BLACK);
		
		start.setPrefSize(60, 50);
		Text text = new Text("Garry's Galaxy");
		text.setFont(new Font("Comic Sans MS", 80));
		text.setFill(Color.WHITE);

		vBox.getChildren().addAll(vbox, newPlayer, show);
		vBox.setSpacing(50);
		
		
		Image space = new Image(new FileInputStream("Resources/Images/Background2.png"));
		BackgroundFill background = new BackgroundFill(new ImagePattern(space), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		
		// Changes background of the vBox to the given image
		vBox.setBackground(new Background(background));
		// Changes the screen
		return vBox;

	}

	/**
	 * Screen to create a new player account
	 *
	 * @return vBox to show the new screen
	 * @throws FileNotFoundException throws if map file cannot be found
	 */
	public VBox PickPlayerScreen() throws FileNotFoundException {
		VBox vBox = new VBox();

		vBox.setAlignment(Pos.CENTER);
		
		final TextField userInput = new TextField();
		
		userInput.setStyle("Input name");
		userInput.setMaxSize(280, 100);
		userInput.onActionProperty();

		// Button to proceed after entering desired username into text-field
		final Button go = new Button("Go");
		HBox hbox = new HBox(userInput, go);

		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(5);

		// Button to return to previous screen
		final Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					go.getScene().setRoot(loadScreenOne());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		// Loads map for user after account is created
		go.setOnAction(e -> {

			
			LoadFile map = null;
			
			String userText = userInput.getText();
			User user = new User(userText);
			try {
				user.profileWriter(userText);

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			user.setCurrentLevel(1);
			
			validation = true;
			// validation to check if user exists or not.
			while (validation == true) {
				try {
					map = new LoadFile("Levels\\test.txt");
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
				validation = false;
			}
			
			Character Garry = new Character(map.getPlayerXPox(), map.getPlayerYPox(), map.getHasFireBoots(),
					map.getHasFlipper(), map.getTokenCount(), map.getRedKeyCount(), map.getBlueKeyCount(),
					map.getGreenKeyCount());

			game = new GamePanel(Garry, map, user);
			
			try {
				root = game.buildGUI();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("buildGui not found");
			}
			// Plays music indefinitely upon creation of the game
			String song = new File("Resources/Music/Main_Theme_v2.wav").toURI().toString();
			MediaPlayer player = new MediaPlayer(new Media(song));
			player.setCycleCount(INDEFINITE);
			player.play();
			
			
			try {
				GamePanel.getImages();
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}

			if (map.getFileFound() == true) {

				try {
					gameStart(game);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});

	
		Text text = new Text("Pick a Username:"); 
		text.setFont(new Font("Arial", 30));
		text.setFill(Color.WHITE);
		text.setStrokeWidth(2);
		
		vBox.getChildren().addAll(text, hbox, back);
		vBox.setSpacing(50);

		Image space = new Image(new FileInputStream("Resources/Images/space.png"));
		BackgroundFill background = new BackgroundFill(new ImagePattern(space), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		vBox.setBackground(new Background(background)); //Sets background for the new screen
		return vBox; //Returns new screen
	}

	/**
	 * Level selector screen where a user can choose their level
	 *
	 * @return LevelSelecter vBox for the new screen
	 * @throws FileNotFoundException the file not found exception
	 */
	public VBox PickLevelScreen() throws FileNotFoundException {

		VBox LevelSelecter = new VBox();
		HBox title = new HBox();
		
		title.setAlignment(Pos.CENTER);
		
		Text titleR = new Text("Pick a Level:"); //title for the 
		titleR.setFill(Color.WHITE);
		titleR.setFont(new Font("Arial", 50));

		HBox titlent = new HBox();

		title.getChildren().add(titleR);
		
		VBox FillerSpace = new VBox();
		
		final ImageView garry = new ImageView();
		Image player = new Image(new FileInputStream("Resources\\Images\\player.png"));
		garry.setImage(player);

		FillerSpace.getChildren().add(garry);

		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(-2.5);
		
		// HBox for each level
		HBox levelone = new HBox();
		HBox leveltwo = new HBox();
		HBox levelthree = new HBox();
		
		final ImageView selectedImage = new ImageView();
		Image image = new Image(new FileInputStream("Resources\\Images\\level1.png"));
		selectedImage.setImage(image);
		
		//
		levelone.setPrefSize(280, 100);
		leveltwo.setPrefSize(280, 100);
		levelthree.setPrefSize(280, 100);
		
		levelone.setSpacing(25);
		leveltwo.setSpacing(25);
		levelthree.setSpacing(25);
		
		Text levels = new Text("Level 1:");
		Text levelt = new Text("Level 2:");
		Text levelu = new Text("Level 3:");
		
		
		levels.setFill(Color.WHITE);
		levelt.setFill(Color.WHITE);
		levelu.setFill(Color.WHITE);
		
		levels.setFont(new Font("Arial", 50));
		levelt.setFont(new Font("Arial", 50));
		levelu.setFont(new Font("Arial", 50));
		
		VBox forlevels = new VBox();
		VBox forlevelu = new VBox();
		VBox forlevelt = new VBox();
		
		
		levelone.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 3;"
				+ "-fx-border-color: yellow;");
		leveltwo.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 3;"
				+ "-fx-border-color: yellow;");
		levelthree.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 3;"
				+ "-fx-border-color: yellow;");
		//vBox to align the levels text (Level 1:)
		forlevels.getChildren().add(levels);
		forlevels.setAlignment(Pos.CENTER);
		//vBox to align the levelu text (Level 2:)
		forlevelu.getChildren().add(levelu);
		forlevelu.setAlignment(Pos.CENTER);
		//vBox to align the levelt text (Level 3:)
		forlevelt.getChildren().add(levelt);
		forlevelt.setAlignment(Pos.CENTER);
		
		levelone.getChildren().addAll(forlevels, new ImageView(image));
		leveltwo.getChildren().addAll(forlevelt, new ImageView(image));
		levelthree.getChildren().addAll(forlevelu, new ImageView(image));

		vBox.setPrefSize(600, 600);
		vBox.getChildren().addAll(levelone, leveltwo, levelthree);
		titlent.getChildren().addAll(FillerSpace, vBox);
		LevelSelecter.getChildren().addAll(title, titlent);
		

		Image spacerl = new Image(new FileInputStream("Resources/Images/garrysgalaxybg.png"));
		BackgroundFill background = new BackgroundFill(new ImagePattern(spacerl), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		LevelSelecter.setBackground(new Background(background));

		return LevelSelecter; //VBox for the levelSelecter page

	}

	/**
	 * Game start.
	 *
	 * @param game the game
	 * @throws Exception the exception
	 */
	public void gameStart(GamePanel game) throws Exception {
		System.out.println("tester2");
		// Build the GUI
		GamePanel.getImages();
		Pane root = game.buildGUI();
		
		// playerCoord[0][0] = playerCoord[playerX][playerY];
		// Create a scene from the GUI
		
		Scene scene = new Scene(root, GamePanel.WINDOW_WIDTH, GamePanel.WINDOW_HEIGHT);
		gameScene = new Stage();
		// Register an event handler for key presses
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			try {
				game.processKeyEvent(event);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});
		
		game.drawGame();
		gameScene.setScene(scene);
		gameScene.show();

	}

	/**
	 * Player death Method
	 *
	 * @throws Exception the exception
	 */
	public static void die() throws Exception {

		firstScene.close();
		Main test = new Main();
		test.start(firstScene);
		game.buildGUI();

	}
}