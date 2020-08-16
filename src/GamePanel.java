import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Sample application that demonstrates the use of JavaFX Canvas for a Game.
 * This class is intentionally not structured very well. This is just a starting
 * point to show how to draw an image on a canvas and respond to arrow key
 * presses.
 *
 *@author Christophe 
 * the window rendered after running the game, this is redrawn when the 
 * player makes a move input and the map is updated
 * 
 */
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

//make grid move around player
//large grid but only part of the grid is shown
public class GamePanel extends Application {
	public Stage primary;
	Label empty;
	Label tokens;
	Label time;
	Label level;
	Label notification;
	// current playing song location
	BorderPane root;
	VBox stats;

	private boolean originalCheck = true;
	// options bar(HBox layout
	private static final Insets OPTIONS_PADDING = new Insets(5, 5, 5, 5);
	private static final int OPTIONS_SPACING = 5;
	// stats (VBox) layout
	private static final Insets STATS_PADDING = new Insets(10, 10, 10, 10);
	private static final int STATS_SPACING = 50;
	private static final Insets BOTTOM_LAYOUT = new Insets(300);
	// window size
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 750;

	// Grid size
	private static final int GRID_SIZE = 15;
	private static final int SCROLL_GRID_SIZE = 12;

	// The size of each cell
	private static int GRID_CELL_SIZE = 50;

	// The dimensions of the canvas
	private static int canvasWidth = SCROLL_GRID_SIZE * GRID_CELL_SIZE;
	private static int canvasHeight = SCROLL_GRID_SIZE * GRID_CELL_SIZE;

	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private static Canvas canvas;
	private Label scoreBox;

	// test
	private HBox toolbar;

	// Score counter
	private int score = 0;
	// interaction messages
	private String output = " ";

	// Game Map
	private static ArrayList<ArrayList<Cell>> gameMap = new ArrayList<>();

	// Start time
	private static long startTime = 0;

	// current playing song location

	// Loaded images
	private static Image player;
	private static Image dirt;
	private static Image icon;
	private static Image space;

	private static int xBound;
	private static int yBound;

	// used to track the first load of the game
	private static boolean start = true;
	// private static ArrayList<ArrayList<Cell>> originalMap = gameMap;
	// X and Y coordinate of player
	private static int playerX = (SCROLL_GRID_SIZE / 2);
	private static int playerY = (SCROLL_GRID_SIZE / 2);
	// private int playerCoord[][] = new int[0][0]; to return
	private LoadFile map;
	// timer
	private static int gridSizeX=15;
	private static int gridSizeY=15;
	private static int timer = 5000;
	private Character garry;
	private ArrayList<ArrayList<Cell>> originalMap = new ArrayList<>();
	private User user;
	// token list
	private static ArrayList<Integer> tokenList = new ArrayList<>();

/**
	 * makes a gamepanel
	 * @param garry
	 * @param map
	 * @param user
	 */
	public GamePanel(Character garry, LoadFile map,User user) {
		System.out.println("tester2198");
		//this.primary = primary;
		this.map = map;
		this.garry = garry;
		this.user = user;
		this.gameMap = map.getGameMap();
		this.gridSizeX = map.getWidthSize();
		this.gridSizeY = map.getHightSize();
		// this.originalMap=map.getGameMap();
	}
/**
	 * sets the character
	 * @param garry
	 */
	public void setCharacter(Character garry) {
		this.garry = garry;
	}
	/**
	 * starts gamepanel
	 */	
	public void start(Stage primaryStage) throws Exception {
		/*
		 * this.primary=primaryStage; System.out.println("test"); startTime =
		 * System.currentTimeMillis(); // Build the GUI getImages(); Pane root =
		 * buildGUI(); // playerCoord[0][0] = playerCoord[playerX][playerY]; // Create a
		 * scene from the GUI Scene scene = new Scene(root, WINDOW_WIDTH,
		 * WINDOW_HEIGHT);
		 * 
		 * // Register an event handler for key presses
		 * scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> { try {
		 * processKeyEvent(event); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } }); // getCharacter("85") // Display the scene on the
		 * stage
		 * 
		 * drawGame(); // if (timer > 0) { // Thread.sleep(1); // drawGame(); // }
		 * primaryStage.setScene(scene); primaryStage.show();
		 * 
		 */}
/**
	 * gets images
	 * @throws FileNotFoundException
	 */
	public static void getImages() throws FileNotFoundException {
		player = new Image(new FileInputStream("Resources/Images/player.png"));
		dirt = new Image(new FileInputStream("Resources/Images/dirt.png"));
		space = new Image(new FileInputStream("Resources/Images/space.png"));
	}

	/**
	 * Process a key event due to a key being pressed, e.g., to the player.
	 * 
	 * @param event The key event that was pressed.
	 */
	private static int xPosChange = 0;
	private static int yPosChange = 0;

	public void processKeyEvent(KeyEvent event) throws FileNotFoundException {
		System.out.println("tester2198");
		if (originalCheck == true) {
			this.originalMap = this.gameMap;
			this.originalCheck = false;
		}
		System.out.println(xPosChange + " , " + yPosChange);
		switch (event.getCode()) {
		case RIGHT:
			// Right key was pressed. So move the player right by one cell.
			// if (playerX < SCROLL_GRID_SIZE - 1) {
			// makes token door walkable if player has enough tokens
			if (gameMap.get(playerY).get(playerX + 1).getId() == "token door"
					&& this.garry.getTokenCount() >= map.getTokenList().get(0)) {
				gameMap.get(playerY).get(playerX + 1).setWalkPlayer(true);
			} else if ((gameMap.get(playerY).get(playerX + 1).getId() == "colour door")
					&& (gameMap.get(playerY).get(playerX + 1).getColour().equals("red")
							&& this.garry.getRedKeyCount() > 0
							|| gameMap.get(playerY).get(playerX + 1).getColour().equals("blue")
									&& this.garry.getBlueKeyCount() > 0
							|| gameMap.get(playerY).get(playerX + 1).getColour().equals("green")
									&& this.garry.getGreenKeyCount() > 0)) {
				gameMap.get(playerY).get(playerX + 1).setWalkPlayer(true);

			} else if (gameMap.get(playerY).get(playerX + 1).getId() == "teleporter") {
				for (int i = -(SCROLL_GRID_SIZE / 2); i < (SCROLL_GRID_SIZE / 2); i++) {
					if (i < -(playerX + 1 - gameMap.get(playerY).get(playerX + 1).getXPos()) - 2) {
						if ((playerX + 1 - gameMap.get(playerY).get(playerX + 1).getXPos()) > 0) {
							// xPosChange -= 1;
							playerX = playerX + 1;
							garry.setCharacterPos(garry.getPosX() + 1, garry.getPosY());
							drawGame();
							System.out.println("player: " + playerX + " , " + playerY);
							System.out.println("garry: " + garry.getPosX() + " , " + garry.getPosY());
							System.out.println("position change: " + xPosChange + " , " + yPosChange);
							drawGame();
						}
						playerX = playerX + 1;
						xPosChange -= 1;

					}
				}
				for (int i = -(SCROLL_GRID_SIZE / 2); i < (SCROLL_GRID_SIZE / 2); i++) {
					if (i < (playerY - gameMap.get(playerY).get(playerY + 1).getYPos()) + 1) {
						yPosChange += 1;
						playerY = playerY - 1;
						garry.setCharacterPos(garry.getPosY(), garry.getPosY() - 1);

						drawGame();
					}
				}
			}
			if (gameMap.get(playerY).get(playerX + 1).getWalkPlayer() == true) {
				playerX = playerX + 1;
				xPosChange -= 1;
				garry.setCharacterPos(garry.getPosX() + 1, garry.getPosY());
			}
			// player = gameMap[playerX+1]
			// }
			break;
		case LEFT:
			// Left key was pressed. So move the player left by one cell.
			// if (playerX > 0) {

			if (gameMap.get(playerY).get(playerX - 1).getId() == "token door"
					&& this.garry.getTokenCount() >= map.getTokenList().get(0)) {
				gameMap.get(playerY).get(playerX - 1).setWalkPlayer(true);
			} else if ((gameMap.get(playerY).get(playerX - 1).getId() == "colour door")
					&& (gameMap.get(playerY).get(playerX - 1).getColour().equals("red")
							&& this.garry.getRedKeyCount() > 0
							|| gameMap.get(playerY).get(playerX - 1).getColour().equals("blue")
									&& this.garry.getBlueKeyCount() > 0
							|| gameMap.get(playerY).get(playerX - 1).getColour().equals("green")
									&& this.garry.getGreenKeyCount() > 0)) {
				gameMap.get(playerY).get(playerX - 1).setWalkPlayer(true);
			}

			if (gameMap.get(playerY).get(playerX - 1).getWalkPlayer() == true) {
				xPosChange += 1;
				playerX = playerX - 1;
				garry.setCharacterPos(garry.getPosX() - 1, garry.getPosY());
			}
			// }

			break;
		case UP:
			// Right key was pressed. So move the player right by one cell.
			// if (playerY > 0) {

			if (gameMap.get(playerY - 1).get(playerX).getId() == "token door"
					&& this.garry.getTokenCount() >= map.getTokenList().get(0)) {
				gameMap.get(playerY - 1).get(playerX).setWalkPlayer(true);
			} else if ((gameMap.get(playerY - 1).get(playerX).getId() == "colour door")
					&& ((gameMap.get(playerY - 1).get(playerX).getColour().equals("red")
							&& this.garry.getRedKeyCount() > 0)
							|| (gameMap.get(playerY - 1).get(playerX).getColour().equals("blue")
									&& this.garry.getBlueKeyCount() > 0)
							|| (gameMap.get(playerY - 1).get(playerX).getColour().equals("green")
									&& this.garry.getGreenKeyCount() > 0))) {
				gameMap.get(playerY - 1).get(playerX).setWalkPlayer(true);

			}

			if (gameMap.get(playerY - 1).get(playerX).getWalkPlayer() == true) {
				playerY = playerY - 1;
				yPosChange += 1;
				garry.setCharacterPos(garry.getPosX(), garry.getPosY() - 1);
			}
			// }
			break;
		case DOWN:
			// Left key was pressed. So move the player left by one cell.
			// if (playerY < SCROLL_GRID_SIZE - 1) {
			if (gameMap.get(playerY + 1).get(playerX).getId() == "token door"
					&& this.garry.getTokenCount() >= map.getTokenList().get(0)) {
				gameMap.get(playerY + 1).get(playerX).setWalkPlayer(true);
			} else if ((gameMap.get(playerY + 1).get(playerX).getId() == "colour door")
					&& (gameMap.get(playerY + 1).get(playerX).getColour().equals("red")
							&& this.garry.getRedKeyCount() > 0
							|| gameMap.get(playerY + 1).get(playerX).getColour().equals("blue")
									&& this.garry.getBlueKeyCount() > 0
							|| gameMap.get(playerY + 1).get(playerX).getColour().equals("green")
									&& this.garry.getGreenKeyCount() > 0)) {
				gameMap.get(playerY + 1).get(playerX).setWalkPlayer(true);
			}

			if (gameMap.get(playerY + 1).get(playerX).getWalkPlayer() == true) {
				playerY = playerY + 1;
				yPosChange -= 1;
				garry.setCharacterPos(garry.getPosX(), garry.getPosY() + 1);
			}
			// }
			break;
		default:
			// Do nothing
			break;
		}

		// GameState.runEvents(gameMap); ****************

		// move icon if player is at icon coordinates
		// if (playerX == iconX && playerY == iconY) {
		// Random random = new Random();
		// iconX = random.nextInt(GRID_SIZE - 1);
		// iconY = random.nextInt(GRID_SIZE - 1);
		// increase then display the score
//			score++;
//			displayScore();
//		}
		// Redraw game as the player may have moved.

		// to be moved to seperate method
		boolean runOnceSmart = true;
		boolean runOnceDumb = true;
		boolean runOnceWall = true;
		boolean runOnceStraight = true;
		int colourListIncrement = 0;
		for (int y = 0; y < gridSizeY; y++) {
			for (int x = 0; x < gridSizeY; x++) {

				if (gameMap.get(y).get(x).getId() == "colour door" || gameMap.get(y).get(x).getId() == "red door"
						|| gameMap.get(y).get(x).getId() == "blue door"
						|| gameMap.get(y).get(x).getId() == "green door") {
					// System.out.println(gameMap.get(y).get(x).getColour());
				}

				if (this.garry.getPosX() == gameMap.get(y).get(x).getXPos()
						&& this.garry.getPosY() == gameMap.get(y).get(x).getYPos()
						&& (gameMap.get(y).get(x).getId() == ("dumb enemy")
								|| gameMap.get(y).get(x).getId() == ("smart enemy")
								|| gameMap.get(y).get(x).getId() == ("wall following")
								|| gameMap.get(y).get(x).getId() == ("straight line"))) {
					output = "You ran into an enemy";
					try {
						playSoundFX("death.wav");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					try {
						this.playerX = 7;
						this.playerY = 7;
						this.yPosChange = 0;
						this.xPosChange = 0;
						this.gameMap = this.originalMap;
						
						Main.die();
						
						;

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					output = "You ran into an enemy";
					
				} else if (gameMap.get(y).get(x).getId() == "colour door") {
					gameMap.get(y).get(x).setColour(map.getColourList().get(colourListIncrement));
					colourListIncrement++;
				} else if (gameMap.get(y).get(x).getId() == "smart enemy" && runOnceSmart) {
					// System.out.println(gameMap.get(y).get(x).getSmart().getXPos() + " " +
					// gameMap.get(y).get(x).getSmart().getYPos());

					gameMap.get(y).get(x).getSmart().setEnemyGrid(gameMap);

					// System.out.println(this.garry.getPosX());

					// System.out.println("garry:" + this.garry.getPosX() + "," +
					// this.garry.getPosY());
					gameMap.get(y).get(x).getSmart().executeAlgorithm(this.garry.getPosX(), this.garry.getPosY());
					// System.out.println("suggested move: " +
					// gameMap.get(y).get(x).getSmart().getXPos() + " "
					// + gameMap.get(y).get(x).getSmart().getYPos());

					// System.out.println("enemt:" + gameMap.get(y).get(x).getSmart().getXPos() + "
					// , " + gameMap.get(y).get(x).getSmart().getYPos());
					if (gameMap.get(y).get(x).getSmart().getXPos() != x
							|| gameMap.get(y).get(x).getSmart().getYPos() != y) {
						gameMap.get(gameMap.get(y).get(x).getSmart().getYPos()).set(
								gameMap.get(y).get(x).getSmart().getXPos(),
								new Ground(gameMap.get(y).get(x).getSmart().getXPos(),
										gameMap.get(y).get(x).getSmart().getYPos(), true, true, "Smart.png"));

						gameMap.get(gameMap.get(y).get(x).getSmart().getYPos())
								.get(gameMap.get(y).get(x).getSmart().getXPos())
								.setSmart(gameMap.get(y).get(x).getSmart());

						// System.out.println(gameMap.get(gameMap.get(y).get(x).getSmart().getYPos()).get(gameMap.get(y).get(x).getSmart().getXPos()).getId());
						// gameMap.get(y).set(x,new Ground(gameMap.get(y).get(x).getSmart().getXPos(),
						// gameMap.get(y).get(x).getSmart().getYPos(), true, true, "dirt.png"));
						gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
						// System.out.println(gameMap.get(y).get(x).getId());
					}
					runOnceSmart = false;

				} else if (gameMap.get(y).get(x).getId() == "dumb enemy" && runOnceDumb) {

					gameMap.get(y).get(x).getDumb().setEnemyGrid(gameMap);

					// System.out.println(gameMap.get(y).get(x).getDumb().getXPos() + " , " +
					// gameMap.get(y).get(x).getDumb().getYPos() );
					gameMap.get(y).get(x).getDumb().moveDumb(garry.getPosX(), garry.getPosY());
					// System.out.println(gameMap.get(y).get(x).getDumb().getXPos() + " , " +
					// gameMap.get(y).get(x).getDumb().getYPos());

					if (gameMap.get(y).get(x).getDumb().getXPos() != x
							|| gameMap.get(y).get(x).getDumb().getYPos() != y) {
						System.out.println("test**********");
						gameMap.get(gameMap.get(y).get(x).getDumb().getYPos()).set(
								gameMap.get(y).get(x).getDumb().getXPos(),
								new Ground(gameMap.get(y).get(x).getDumb().getXPos(),
										gameMap.get(y).get(x).getDumb().getYPos(), true, true, "Dumb.png"));

						// System.out.println(gameMap.get(y).get(x).getDumb().getYPos());

						gameMap.get(gameMap.get(y).get(x).getDumb().getYPos())
								.get(gameMap.get(y).get(x).getDumb().getXPos())
								.setDumb(gameMap.get(y).get(x).getDumb());

						gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));

					}
					runOnceDumb = false;

					/*
					 * if (this.garry.getPosX() == gameMap.get(y).get(x).getXPos() &&
					 * this.garry.getPosY() == gameMap.get(y).get(x).getYPos()) {
					 * System.out.println("You ran into a dumb enemy"); }
					 */

				} else if (gameMap.get(y).get(x).getId() == "wall following" && runOnceWall) {

					gameMap.get(y).get(x).getWallFollowing().setEnemyGrid(gameMap);

					gameMap.get(y).get(x).getWallFollowing().followWall();

					gameMap.get(gameMap.get(y).get(x).getWallFollowing().getYPos()).set(
							gameMap.get(y).get(x).getWallFollowing().getXPos(),
							new Ground(gameMap.get(y).get(x).getWallFollowing().getXPos(),
									gameMap.get(y).get(x).getWallFollowing().getYPos(), true, false,
									"Wall_Following.png"));
					gameMap.get(gameMap.get(y).get(x).getWallFollowing().getYPos())
							.get(gameMap.get(y).get(x).getWallFollowing().getXPos())
							.setWallFollowing(gameMap.get(y).get(x).getWallFollowing());

					gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
					runOnceWall = false;

				} else if (gameMap.get(y).get(x).getId() == "straight line" && runOnceStraight == true) {

					gameMap.get(y).get(x).getStraightLine().setEnemyGrid(gameMap);

					// System.out.println(gameMap.get(y).get(x).getStraightLine().getXPos() + " , "
					// +
					// gameMap.get(y).get(x).getStraightLine().getYPos() );

					gameMap.get(y).get(x).getStraightLine().moveStraightLine();

					// System.out.println(gameMap.get(y).get(x).getStraightLine().getXPos() + " , "
					// +
					// gameMap.get(y).get(x).getStraightLine().getYPos() );

					gameMap.get(gameMap.get(y).get(x).getStraightLine().getYPos()).set(
							gameMap.get(y).get(x).getStraightLine().getXPos(),
							new Ground(gameMap.get(y).get(x).getStraightLine().getXPos(),
									gameMap.get(y).get(x).getStraightLine().getYPos(), true, true,
									"Straight_Line.png"));

					gameMap.get(gameMap.get(y).get(x).getStraightLine().getYPos())
							.get(gameMap.get(y).get(x).getStraightLine().getXPos())
							.setStraightLine(gameMap.get(y).get(x).getStraightLine());

					gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));

					runOnceStraight = false;

				}
				if (this.garry.getPosX() == gameMap.get(y).get(x).getXPos()
						&& this.garry.getPosY() == gameMap.get(y).get(x).getYPos()) {

					if (gameMap.get(y).get(x).getId() == "water" && this.garry.getFlipper() == false) { // water
						// player dies
						output = "Game Over. You Died! You Drowned";
						try {
							playSoundFX("death.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							this.playerX = 7;
							this.playerY = 7;
							this.yPosChange = 0;
							this.xPosChange = 0;
							this.gameMap = this.originalMap;
							
							Main.die();
							
							;

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else if (gameMap.get(y).get(x).getId() == "fire" && this.garry.getFireBoots() == false) { // fire
						// player dies
						output = "Game Over. You Died! You Burnt.";
						try {
							playSoundFX("death.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							this.playerX = 7;
							this.playerY = 7;
							this.yPosChange = 0;
							this.xPosChange = 0;
							this.gameMap = this.originalMap;
							
							Main.die();
							
							;

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (gameMap.get(y).get(x).getId() == "flipper") { // flipper
						gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
						this.garry.setFlipper(true);
						output = "picked up flipper";
						try {
							playSoundFX("Itempickup2.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (gameMap.get(y).get(x).getId() == "fire boots") { // fire boots
						gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
						output = "picked up fire boots";
						this.garry.setFireBoots(true);
						try {
							playSoundFX("Itempickup2.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (gameMap.get(y).get(x).getId() == "token") { // token
						gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
						this.garry.setTokenCount(this.garry.getTokenCount() + 1);
						output = "picked up token";
						// Token.giveItem(this.garry);
						System.out.println("You have " + this.garry.getTokenCount() + " tokens.");
						try {
							playSoundFX("Itempickup2.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (gameMap.get(y).get(x).getId() == "token door") {// token door (takes away coins)
						if (this.garry.getTokenCount() >= map.getTokenList().get(0)) {
							gameMap.get(y).get(x).setWalkPlayer(true);
							gameMap.get(y).set(x, new TokenDoor(x, y, true, false, "tokenDoorOpen.png"));
							this.garry.setTokenCount(this.garry.getTokenCount() - map.getTokenList().get(0));
							output = "Token Door opened";
							try {
								playSoundFX("doors.wav");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else if (gameMap.get(y).get(x).getId() == "goal") {// token door (takes away coins)
						gameMap.get(y).set(x, new Goal(x, y, true, false, "goalReached.png"));
						output = "Goal reached";
						try {
							playSoundFX("Itempickup2.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (gameMap.get(y).get(x).getId() == "key") {
						try {
							playSoundFX("Itempickup2.wav");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (gameMap.get(y).get(x).getColour().equals("red")) {
							this.garry.setRedKeyCount(this.garry.getRedKeyCount() + 1);
							gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
							output = "red key picked up";
						} else if (gameMap.get(y).get(x).getColour().equals("blue")) {
							this.garry.setBlueKeyCount(this.garry.getBlueKeyCount() + 1);
							gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
							output = "blue key picked up";
						} else if (gameMap.get(y).get(x).getColour().equals("green")) {
							this.garry.setGreenKeyCount(this.garry.getGreenKeyCount() + 1);
							gameMap.get(y).set(x, new Ground(x, y, true, true, "dirt.png"));
							output = "green key picked up";
						}
					} else if (gameMap.get(y).get(x).getId() == "colour door") {
						if (gameMap.get(y).get(x).getColour().equals("red") && this.garry.getRedKeyCount() > 0) {
							gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "redDoorOpen.png", "red"));
							gameMap.get(y).get(x).setColour("red");
							output = "red door opened";
							try {
								playSoundFX("doors.wav");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (gameMap.get(y).get(x).getColour().equals("blue")
								&& this.garry.getBlueKeyCount() > 0) {
							gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "blueDoorOpen.png", "blue"));
							gameMap.get(y).get(x).setColour("blue");
							output = "blue key picked up";
							try {
								playSoundFX("doors.wav");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (gameMap.get(y).get(x).getColour().equals("green")
								&& this.garry.getGreenKeyCount() > 0) {
							gameMap.get(y).set(x, new ColourKeyDoor(x, y, true, false, "greenDoorOpen.png", "green"));
							gameMap.get(y).get(x).setColour("green");
							output = "green key picked up";
							try {
								playSoundFX("doors.wav");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}

			}
		}

		// more - tempArray.add(new Cell(x, y, true, true, "icon.png"))

		drawGame();

		/*
		 * for (int y = 0; y < gridSize; y++) { ArrayList<Cell> tempArray = new
		 * ArrayList<>(); for (int x = 0; x < gridSize; x++) { if (x == 7 && y == 7) {
		 * tempArray.add(new Cell(x, y, true, true, "icon.png")); // was fire } else {
		 * tempArray.add(new Cell(x, y, true, true, "dirt.png")); } }
		 * grid.add(tempArray);
		 * 
		 * }
		 */

		/*
		 * for (int i = 0; i > 0; i++) { timer -= 1; try { Thread.sleep(1); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); System.out.println("ERROR - time"); } drawGame(); }
		 */

		// Consume the event. This means we mark it as dealt with. This stops other GUI
		// nodes (buttons etc) responding to it.
		event.consume();

	}

	public static int getXLowerBound() {
		if (playerX - (SCROLL_GRID_SIZE / 2) >= 0) {
			xBound = playerX - (SCROLL_GRID_SIZE / 2);
		} else {
			xBound = 0;
		}
		return xBound;
	}

	public static int getYLowerBound() {
		if (playerY - (SCROLL_GRID_SIZE / 2) >= 0) {
			yBound = playerY - (SCROLL_GRID_SIZE / 2);
		} else {
			yBound = 0;
		}
		return yBound;
	}

	public static int getXUpperBound() {
		if (playerX - (SCROLL_GRID_SIZE / 2) < gridSizeX) {
			xBound = playerX + (SCROLL_GRID_SIZE / 2);
		} else {
			xBound = gridSizeX;
		}

		return xBound;
	}

	public static int getYUpperBound() {
		if (playerY - (SCROLL_GRID_SIZE / 2) < gridSizeY) {
			yBound = playerY + (SCROLL_GRID_SIZE / 2);
		} else {
			yBound = gridSizeY;
		}

		return yBound;
	}

	/**
	 * Draw the game on the canvas.
	 * 
	 * @throws FileNotFoundException
	 */
	public void drawGame() throws FileNotFoundException {

		this.tokens.setText("Tokens: " + this.garry.getTokenCount());
		this.time.setText("Time: " + (System.currentTimeMillis() - startTime) / 1000);

		this.level.setText("Current level:" + "<levelVar>");
		this.notification.setText(output);

		// GameState.setGridMap();
		// gameMap = GameState.getGrid();*********************
		// Should only read gameMap from start to start the loop *******************

		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// Draw row of dirt images
		// We multiply by the cell width and height to turn a coordinate in our grid
		// into a pixel coordinate.
		// We draw the row at y value 2.
		// time.setText("Time: " + (System.currentTimeMillis() - startTime) / 1000);

		for (int y = getYLowerBound(); y < getYUpperBound() && y < gridSizeY; y++) {
			for (int x = getXLowerBound(); x < getXUpperBound() && x < gridSizeX; x++) {

				gc.drawImage(gameMap.get(y).get(x).getImage(),
						(gameMap.get(y).get(x).getXPos() + xPosChange) * GRID_CELL_SIZE,
						(gameMap.get(y).get(x).getYPos() + yPosChange) * GRID_CELL_SIZE);
				// System.out.println(gameMap.get(y).get(x).getId());

			}

			// int x = GRID_SIZE-1;
			// int y = GRID_SIZE-1;
			// gc.drawImage(gameMap.get(y).get(x).getImage(),
			// (gameMap.get(y).get(x).getXPos() * GRID_CELL_SIZE),
			// (gameMap.get(y).get(x).getYPos() * GRID_CELL_SIZE));
			// When x moves down the position of the image goes up (change in event?)
			// System.out.println(GameState.getGridMap().get(y).get(x).getXPos());

			// - render around character

			// Draw player at current location
			gc.drawImage(player, (SCROLL_GRID_SIZE / 2) * GRID_CELL_SIZE, (SCROLL_GRID_SIZE / 2) * GRID_CELL_SIZE);
			// gridMap[playerX][playerY] = "player";
			// gc.drawImage(icon, iconX * GRID_CELL_SIZE, iconY * GRID_CELL_SIZE);
//		gridMap[iconX][iconY] = "icon";
		}

	}

	public void displayScore() {
		scoreBox.setText("Score : " + score);
	}

	/**
	 * Restart the game.
	 * 
	 * @throws FileNotFoundException
	 */
	public void restartGame() throws FileNotFoundException {
		// We just move the player to cell (0, 0)
		playerX = 0;
		playerY = 0;
		score = 0;
		drawGame();
		// displayScore();
	}

	/**
	 * Move the player to roughly the center of the grid.
	 */
//	public void movePlayerToCenter() {
	// We just move the player to the middle of the grid.
	// If GridSize is odd then increase middle number by 1
//		playerX = GRID_SIZE / 2;
//		playerY = GRID_SIZE / 2;
//		drawGame();
//	}

	/**
	 * Create the GUI.
	 * 
	 * @return The panel that contains the created GUI.
	 * @throws Exception
	 */
	public Pane buildGUI() throws Exception {
		System.out.println("tester21****");
		getImages();
		// Create top-level panel that will hold all GUI
		this.root = new BorderPane();

		// Create canvas
		canvas = new Canvas(canvasWidth, canvasHeight);
		BackgroundFill background = new BackgroundFill(new ImagePattern(space), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		BackgroundFill scoreBack = new BackgroundFill(Color.rgb(20, 200, 60), new CornerRadii(1),
				new Insets(0.0, 0.0, 0.0, 0.0));
		root.setBackground(new Background(background));

		HBox optionsBar = new HBox();
		optionsBar.setSpacing(OPTIONS_SPACING);
		optionsBar.setPadding(OPTIONS_PADDING);
		Button save = new Button("Save");
		Button load = new Button("Load");
		Button moreOptions = new Button("More Options");
		optionsBar.getChildren().addAll(save, load, moreOptions);

		save.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        save.setText("Done...");
		        try {
		        	 System.out.println("TESTgui");
					SaveFile save = new SaveFile(map.getGameMap(), map.getColourList(), map.getDirectionList(), map.getTokenList(),map.getTeleList(), garry,map.getHightSize(),map.getWidthSize(),user);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("TESTguiFAIL");
				}
		    }public ArrayList<Integer> getTokenList() { // array in case if we want more token doors
				return tokenList;
			}
		});
		
		// stats to track time/score ect on left
		this.stats = new VBox();
		stats.setSpacing(STATS_SPACING);
		stats.setPadding(STATS_PADDING);
		this.empty = new Label("");
		this.tokens = new Label("Tokens:" + this.garry.getTokenCount());
		this.time = new Label("Time: " + (System.currentTimeMillis() - startTime) / 1000);
		this.level = new Label("Current level:" + "<levelVar>");
		this.notification = new Label(output);
		stats.setBackground(new Background(scoreBack));
		stats.getChildren().addAll(empty, tokens, time, level, notification);
		
		
		
		HBox bottomBar = new HBox();

		MessageOfTheDay message = new MessageOfTheDay();
		String messageString = message.getMessage();
		Label messageOfDay = new Label(messageString);
		messageOfDay.setFont(new Font("Arial", 20));
		messageOfDay.setTextFill(Color.CYAN);
		bottomBar.getChildren().add(messageOfDay);

		// .canvas contains game cells

		// populate canvas method then called

		// adds nodes to the borderpane
		root.setCenter(canvas);
		root.setTop(optionsBar);
		root.setRight(stats);
		root.setBottom(bottomBar);
		drawGame();
		System.out.println(root);
		return root;
	}

	public void playSoundFX(String filename) throws Exception {

		String fx = new File("Resources\\Music\\" + filename).toURI().toString();
		Media mediaFx = new Media(fx);
		MediaPlayer player = new MediaPlayer(mediaFx);
		player.play();
	}
}
