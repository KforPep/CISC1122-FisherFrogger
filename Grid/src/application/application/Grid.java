package application;
/* TO-DO:
 * Prevent diagonal movement?
 * Movement of player on moving objects
 * Smoother animations
 */

//package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.TextAlignment;

public class Grid extends Application
{
	//Class constants & variables
	
	//SCALING ONLY WORKS WHEN GRID_WIDTH IS ODD & GRID_HEIGHT IS EVEN!
	private final int GRID_WIDTH = 19; //Number of tiles in each row (13 default)			
	private final int GRID_HEIGHT = 14; //Number of tiles in each column (14 default)
	private final double PADDING = 16; //Width of spacing around the grid
	private final double WINDOW_WIDTH = 900; //width of the window
	
	private int playerY = GRID_HEIGHT - 1; //Index of player row
	private int playerX = (int)(GRID_WIDTH/2); //Index of player column
	private double TILE_SIZE; //Size of the tile, calculated on launch
	private double GRID_Y; //Grid height measurement
	private double PLAYER_SIZE; //Size of player object
	private double OBJECT_SPAWN_DISTANCE; //distance moving objects will spawn away from the grid
	private double BOTTOM_ROW, RIGHT_COLUMN, LEFT_COLUMN, MIDDLE_COLUMN; //Layout positions
	private double RESPAWN_X, RESPAWN_Y; //Respawn position
	private int lives = 3;
	boolean hitOrRun;
	
	//Main method
	public static void main(String[] args)
	{
		launch(args);
	} //main
	
	//Start method
	@Override
	public void start(Stage mainStage) throws MalformedURLException 
	{
		mainStage.setWidth(WINDOW_WIDTH); //Set the width of the window, used to calculate grid tile size
		
		//Resize window to full screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainStage.setWidth(screenSize.getWidth()); //Set the width of the window, used to calculate grid tile size
		mainStage.setHeight(screenSize.getHeight());
		int gameSceneWidth = (int) mainStage.getWidth() - 800;
		
		TILE_SIZE = (double)((int) ((gameSceneWidth - PADDING) / GRID_WIDTH)) ; //Calculate grid tile size
		GRID_Y = TILE_SIZE * GRID_HEIGHT; //Calculate grid height measurement
		PLAYER_SIZE = (TILE_SIZE - 5)/2; //Calculate size of the player sprite
		OBJECT_SPAWN_DISTANCE = TILE_SIZE*1.5; //Calculate object spawn distance
		
		//Calculate layout positions of centers of tiles RELATIVE TO CENTER OF THE GRID
		BOTTOM_ROW = (TILE_SIZE * ((GRID_WIDTH - 1)/2)) + (TILE_SIZE/2);
		RIGHT_COLUMN = (TILE_SIZE * (GRID_WIDTH/2));
		LEFT_COLUMN = -RIGHT_COLUMN;
		MIDDLE_COLUMN = 0;
		
		//Set player respawn location
		RESPAWN_X = MIDDLE_COLUMN; //respawn column
		RESPAWN_Y = row(4); //respawn row
		
		
		//generate game grid
		ArrayList<ArrayList<Rectangle>> grid = gridGen(TILE_SIZE, GRID_WIDTH, GRID_HEIGHT);
		
		//Create a VBox to hold the grid
		VBox vbxGrid = new VBox();
		vbxGrid.setAlignment(Pos.CENTER);
		
		//Draw the squares from the grid array list on to the scene
		for (int n = 0; n < GRID_HEIGHT; n++)  //1 iteration = 1 row
		{
			HBox row = new HBox(); //Create an HBox to hold a row
			
			for (int i = 0; i < GRID_WIDTH; i++) //Fill the row with squares (1 iteration = 1 square)
			{
				row.getChildren().add(grid.get(n).get(i)); //Take square from grid arraylist and add it to the row
			}
			
			row.setAlignment(Pos.CENTER); //Align the row to the center
			vbxGrid.getChildren().add(row); //Add the row to the grid VBox
		}
		
		//Applying Image Pattern to circles and rectangles
		File file12 = new File(System.getProperty("user.dir") + "/images/player.png"); 
		String localUrl12 = file12.toURI().toURL().toString();
		Image localImage12 = new Image(localUrl12);
		ImagePattern pattern12 = new ImagePattern(localImage12);
		
		File file8 = new File(System.getProperty("user.dir") + "/images/stackofbooks.jpg");
		String localUrl8 = file8.toURI().toURL().toString();
		Image localImage8 = new Image(localUrl8);
		ImagePattern pattern8 = new ImagePattern(localImage8);
		

		
		
		
		//Draw the player
		Circle player = new Circle(PLAYER_SIZE);
		
		player.setFill(pattern12);
		player.setTranslateX(MIDDLE_COLUMN); //Player start X
		player.setTranslateY(row(4)); //Player start Y
		
		/* OBJECTS WITH COLLISION */
		
		double rightSpawn = RIGHT_COLUMN + OBJECT_SPAWN_DISTANCE;
		double leftSpawn = LEFT_COLUMN - OBJECT_SPAWN_DISTANCE;
		double defaultSize = TILE_SIZE - 10;
		double logHeight = TILE_SIZE - 15;
		Color logColor = Color.SADDLEBROWN;
		
		//MovingObject(count, space, speed, velocity, width, height, startX, startY, color, animation type, carry player?)
		
		//Cars 1 (row 2)
		MovingObject car1 = new MovingObject(3, 50, 60, 5, defaultSize, defaultSize, rightSpawn, row(5), Color.YELLOW, "LEFT", false);
		//Cars 2 (row 3)
		MovingObject car2 = new MovingObject(3, 50, 55, 5, defaultSize, defaultSize, leftSpawn, row(6), Color.ORANGERED, "RIGHT", false);
		//Cars 3 (row 4)
		MovingObject car3 = new MovingObject(3, 60, 35, 5, defaultSize + 15, defaultSize, rightSpawn, row(7), Color.MEDIUMPURPLE, "LEFT", false);
		//Cars 4 (row 5)
		MovingObject car4 = new MovingObject(2, 125, 30, 5, defaultSize + 30, defaultSize - 5, leftSpawn, row(8), Color.LAWNGREEN, "RIGHT", false);
		//Trucks (row 6)
		MovingObject truck = new MovingObject(2, 75, 45, 5, (TILE_SIZE*2) - 10, defaultSize, rightSpawn, row(9), Color.ANTIQUEWHITE, "LEFT", false);
		//Logs 1 (row 9)
		MovingObject log1 = new MovingObject(3, 85, 55, 5, (TILE_SIZE*2), logHeight, leftSpawn, row(12), logColor, "RIGHT", true);
		//Logs 2 (row 10)
		MovingObject log2 = new MovingObject(2, 125, 30, 5, (TILE_SIZE*4), logHeight, leftSpawn-75, row(13), logColor, "RIGHT", true);
		//Logs 3 (row 12)
		MovingObject log3 = new MovingObject(2, 65, 55, 5, (TILE_SIZE*3), logHeight, leftSpawn-75, row(14), logColor, "RIGHT", true);
		
		//Stack pane to put objects on top of each other
		StackPane stack = new StackPane();
		stack.getChildren().add(vbxGrid); //Add game grid to stack pane
		stack.getChildren().add(player); //Add player to stack pane
		
		

		//Add the arrays of moving objects on to the stack pane
		car1.toPane(stack); //Cars 1 (row 2)
		car2.toPane(stack); //Cars 2 (row 3)
		car3.toPane(stack); //Cars 3 (row 4)
		car4.toPane(stack); //Cars 4 (row 5)
		truck.toPane(stack); //Trucks (row 6)
		log1.toPane(stack); //Logs 1 (row 9)
		log2.toPane(stack); //Logs 2 (row 10)
		log3.toPane(stack); //Logs 3 (row 12)
		
		//Stack pane layout
		stack.setAlignment(Pos.CENTER);
		stack.setMaxHeight(GRID_HEIGHT);
		stack.setMaxWidth(GRID_WIDTH);
		
		//Border pane to hold the stack pane
		BorderPane pane = new BorderPane(stack);
		
		//Scene
		Scene mainScene = new Scene(pane, WINDOW_WIDTH, GRID_Y + PADDING);
		
		mainScene.getStylesheets().add("style.css"); //import style sheet
		
		//Stage
		//mainStage.setResizable(false);
		mainStage.setTitle("Class Simulator");
		mainStage.setScene(mainScene);
		mainStage.show();
		
		
		//Add lives label
		Label livesLabel = new Label("Lives: " + lives);
		livesLabel.setFont(Font.font(50.0));
		livesLabel.setTextFill(Color.WHITE);
		pane.setTop(livesLabel);
		livesLabel.setAlignment(Pos.TOP_CENTER);
		
		
		/* OBJECT ANIMATIONS */
		
		//Create and start animations
		animateRectangles(car1, player, stack); //Cars 1 (row 2)
		animateRectangles(car2, player, stack); //Cars 2 (row 3)
		animateRectangles(car3, player, stack); //Cars 3 (row 4)
		animateRectangles(car4, player, stack); //Cars 4 (row 5)
		animateRectangles(truck, player, stack); //Trucks (row 6)
		animateRectangles(log1, player, stack); //Logs 1 (row 9)
		animateRectangles(log2, player, stack); //Logs 2 (row 10)
		animateRectangles(log3, player, stack); //Logs 3 (row 12)
		
		/* EVENT LISTENERS */
		
		//Key press
		mainScene.setOnKeyPressed(e -> 
		{
			//Player movement
			if ((e.getCode() == KeyCode.UP) || e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT)
			{
				//Store location before moving
				double currentX = player.getTranslateX();
				double currentY = player.getTranslateY();
				
				//move up
				if (e.getCode() == KeyCode.UP)
				{
					//Check if movement is within the bounds of grid
					//if (playerY > 0)
					//System.out.println("Player's y is - " + player.getTranslateY());
					if (player.getTranslateY() <= (row(16)))
					{}
					else 
					{
						double newY = currentY - TILE_SIZE; //Determine spot to move to
						player.setTranslateY(newY); //move player
						playerY -= 1; //Change player's row index
					}
				}
				
				//move down
				if (e.getCode() == KeyCode.DOWN)
				{	
					//Check if movement is within grid bounds
					if (player.getTranslateY() >= (row(4)))
					{}
					else  
					{
						double newY = currentY + TILE_SIZE; //New spot to move to
						player.setTranslateY(newY); //move player
						playerY += 1; //Change player's row index
					}
				}
				
				//move left
				if (e.getCode() == KeyCode.LEFT)
				{	
					//Check if movement is within grid bounds
					if (player.getTranslateX() >= (leftSpawn+(2*TILE_SIZE)))
					{
						double newX = currentX - TILE_SIZE; //Spot to move to
						player.setTranslateX(newX); //move player
						playerX -= 1; //Change player's column index
					}
				}
				
				//move right
				if (e.getCode() == KeyCode.RIGHT)
				{	
					//Check if movement is within grid bounds
					if (player.getTranslateX() <= (rightSpawn-(2*TILE_SIZE)))
					{
						double newX = currentX + TILE_SIZE; //Spot to move to
						player.setTranslateX(newX); //move player
						playerX += 1; //Change player's column index
					}
				}
				
			} //player movement
			
		}); //key press event
		
	} //start
	
	//Generate a 2d array list of squares to form a grid
	public ArrayList<ArrayList<Rectangle>> gridGen(double tileSize, int gridWidth, int gridHeight) throws MalformedURLException 
	{		
		ArrayList<ArrayList<Rectangle>> grid = new ArrayList<ArrayList<Rectangle>>(); //2d array list to hold rows
		
		//Coordinates of square being drawn
		double x = 0;
		double y = 0;
		
		File file = new File(System.getProperty("user.dir") + "/images/user-top-view.png"); 
		File file1 = new File(System.getProperty("user.dir") + "/images/desktop.png");
		File file2 = new File(System.getProperty("user.dir") + "/images/house-1.png");
		File file3 = new File(System.getProperty("user.dir") + "/images/icetruck.png");
		File file4 = new File(System.getProperty("user.dir") + "/images/thai.png");
		File file5 = new File(System.getProperty("user.dir") + "/images/mcd.png");
		File file6 = new File(System.getProperty("user.dir") + "/images/grass-1.png");
		File file7 = new File(System.getProperty("user.dir") + "/images/desk.jpg");
		String localUrl = file.toURI().toURL().toString();
		String localUrl1 = file1.toURI().toURL().toString();
		String localUrl2 = file2.toURI().toURL().toString();
		String localUrl3 = file3.toURI().toURL().toString();
		String localUrl4 = file4.toURI().toURL().toString();
		String localUrl5 = file5.toURI().toURL().toString();
		String localUrl6 = file6.toURI().toURL().toString();
		String localUrl7 = file7.toURI().toURL().toString();
		// don't load in the background
		Image localImage = new Image(localUrl);
		Image localImage1 = new Image(localUrl1);
		Image localImage2 = new Image(localUrl2);
		Image localImage3 = new Image(localUrl3);
		Image localImage4 = new Image(localUrl4);
		Image localImage5 = new Image(localUrl5);
		Image localImage6 = new Image(localUrl6);
		Image localImage7 = new Image(localUrl7);
		ImagePattern pattern = new ImagePattern(localImage);
		ImagePattern pattern1 = new ImagePattern(localImage1);
		ImagePattern pattern2 = new ImagePattern(localImage2);
		ImagePattern pattern3 = new ImagePattern(localImage3);
		ImagePattern pattern4 = new ImagePattern(localImage4);
		ImagePattern pattern5 = new ImagePattern(localImage5);
		ImagePattern pattern6 = new ImagePattern(localImage6);
		ImagePattern pattern7 = new ImagePattern(localImage7);
		
		
		for (int n = 0; n < gridHeight; n++) //1 iteration = 1 row
		{
			ArrayList<Rectangle> gridRow = new ArrayList<Rectangle>(gridWidth); //Array list to hold squares in each row
			x = 0; //Reset X location of squares to 0
			
			for (int i = 0; i < gridWidth; i++) //fill row with squares
			{
				gridRow.add(new Rectangle(x, y, tileSize, tileSize)); //Create a square at the current index
				x += tileSize; //Set the X location of the next square
				
				//Color the square
				if (n % 2 != 0) //if the row number is odd
				{
					if (i % 2 != 0) //if column number is odd
					{
						
						if(n == 13) //if the row number is either 14 or 8, color purple for safe space
						{
						gridRow.get(i).setFill(pattern6); //color rectangle at current index purple
						}
						else if(n == 7)
							{
								gridRow.get(i).setFill(pattern5);
							}
						else if (n == 1) //if the row number is 2, color purple for safe space
						{
							if (i == 1 || i == 5 || i == 9 || i == 13 || i ==17)
								gridRow.get(i).setFill(pattern); //color rectangle at current index purple
							else
							{
								gridRow.get(i).setFill(pattern7);
							}
						}
						
						else if (n == 3 && (i == 3 || i == 5)) //colors rectangles blue to show where moving object will be placed
						{
							gridRow.get(i).setFill(Color.BLUE); //colors rectangle blue
						}
						else {
							gridRow.get(i).setFill(Color.BLACK); //color rectangle at current index black
						}
					}
					else if(n == 13) //if the row number is either 14 or 8, color purple for safe space
					{
						gridRow.get(i).setFill(pattern2); //color rectangle at current index purple
					}
					else if(n == 1) //if the row number is 2, color purple for safe space
					{
						if (i % 2 != 0) //if the column number is even
						gridRow.get(i).setFill(pattern2); //color rectangle at current index purple
						else
							gridRow.get(i).setFill(pattern7); //color rectangle at current index purple
					}	
					else if (n == 3 && (i == 4)) //colors rectangles blue to show where moving object will be placed
					{
						gridRow.get(i).setFill(Color.BLUE); //colors rectangle blue
					}
					else //if column number is even
					{		
						gridRow.get(i).setFill(Color.GREY); //color rectangle grey
						if (n==7)
							gridRow.get(i).setFill(pattern4);
					}
				}
				else //if the row number is even
				{
		
					
					if (i % 2 != 0) //if the column number is even
					{
						if (n == 0) //if the row number is 1, color purple for safe space
						{
						gridRow.get(i).setFill(pattern7); //color rectangle purple
						}			
						else {
							gridRow.get(i).setFill(Color.GREY); //color rectangle grey
						}
					}
					else if (n == 0) //if the row number is 1, color purple for safe space
					{
						gridRow.get(i).setFill(pattern1); //color rectangle purple
					}
					else //if the column number is odd
					{
						gridRow.get(i).setFill(Color.BLACK); //color rectangle black
					}
				}
			}
			
			grid.add(gridRow); //add the row to the grid
			y += tileSize; //set y coordinate of next row
		}
		
		return grid;
	} //gridGen
	
	//Return the coordinates of a row
	public double row(int rowNumber)
	{
		rowNumber--;
		double row = BOTTOM_ROW - (TILE_SIZE*rowNumber);
		return row;
	} //row
	
	//Create an animation for an array list of rectangles
	public void animateRectangles(MovingObject obj, Circle player, StackPane stack)
	{
		try
		{
			for (int i = 0; i < obj.array.size(); i++)
			{	
				Timeline objAnimation = createTimeline(obj.animType, obj.array.get(i), player, stack.getBoundsInLocal(),
															(i * obj.space), obj.speed, obj.velocity, i, obj.infiniteAnim);
				objAnimation.play();
			}
		}
		catch (NullPointerException ex)
		{
			System.out.println("ERROR! Invalid animation type.");
			ex.printStackTrace();
		}
	} //animateRectangles
	
	//Create a timeline animation
	public Timeline createTimeline(String animationType, MovingObject object, Circle player, Bounds paneBounds,
									int delay, int speedMillis, int xVelocity, int startDelay, boolean indefinite)
	{
		Timeline animation = null;
		//Check animation type
		
		//Left animation
		if (animationType.equalsIgnoreCase("LEFT"))
		{
			animation = new Timeline(new KeyFrame(Duration.millis(speedMillis), new EventHandler<ActionEvent>()
			{
				double xv = xVelocity; //X velocity of animation
				double objectStartPosition = object.getTranslateX(); //Starting position of the object
				int frame = 0;
				Bounds bounds = paneBounds; //Bounds of the layout pane
				
				@Override
				public void handle(ActionEvent e) 
				{
					double objectX = object.getTranslateX();
					
					//Pause for the start delay
					if (frame <= startDelay)
					{
						frame++;
					}
					//Pause for spacing
					else if (frame <= delay)
					{
						frame++;
					}
					//Move object
					else
					{
						object.setTranslateX(objectX - xv); //Move object
						
						//If the object reaches the edge of the pane
						if (objectX <= (bounds.getMinX() - (bounds.getMaxX()/2)))
						{
							object.setTranslateX(objectStartPosition);
						}
						
						//Check for collision
						if(object.getBoundsInParent().intersects(player.getBoundsInParent()))
						{
							if (object.carry == false) //if the object does NOT carry player
							{
								hitOrRun = false;
								playerRespawn respawn = new playerRespawn();
								respawn.loseLife();
								respawn.Respawn(player);
							}
							else
							{
								//carry player
								hitOrRun = true;
								movePlayer(player, object.getTranslateX(), object.getTranslateY());
							}
						}
						
						frame++;
					}
				}
			}));
		} //Left animation
		
		//Right animation
		else if (animationType.equalsIgnoreCase("RIGHT"))
		{
			animation = new Timeline(new KeyFrame(Duration.millis(speedMillis), new EventHandler<ActionEvent>()
			{
				double xv = xVelocity; //X velocity of animation
				double objectStartPosition = object.getTranslateX(); //Starting position of the object
				int frame = 0;
				Bounds bounds = paneBounds; //Bounds of the layout pane
				
				@Override
				public void handle(ActionEvent e)
				{
					double objectX = object.getTranslateX();
					
					//Pause for the start delay
					if (frame <= startDelay)
					{
						frame++;
					}
					//Pause for spacing
					if (frame <= delay)
					{
						frame++;
					}
					else
					{
						object.setTranslateX(objectX + xv); //Move object
						
						//If the object reaches the edge of the pane
						if (objectX >= bounds.getMaxX())
						{
							object.setTranslateX(objectStartPosition);
						}
						
						//Check for collision
						if(object.getBoundsInParent().intersects(player.getBoundsInParent()))
						{
							if (object.carry == false) //if the object does NOT carry player
							{
								hitOrRun = false;
								movePlayer(player, RESPAWN_X, RESPAWN_Y);
							}
							else
							{
								//carry player
								hitOrRun = true;
								movePlayer(player, object.getTranslateX(), object.getTranslateY());
							}
						}
						
						frame++;
					}
				}
			}));
		}
		
		//Determine whether the animation is indefinite
		if (object.infiniteAnim == true)
		{
			animation.setCycleCount(Timeline.INDEFINITE);
		}
		
		return animation;
	} //createTimeline
	
	//Method that will execute when the player collides with an object
	public void movePlayer(Circle player, double newPlayerX, double newPlayerY)
	{	
		
		if (hitOrRun = true) {
			//Set player position
			player.setTranslateX(newPlayerX);
			player.setTranslateY(newPlayerY);
			}
			
			else {
			playerX = (int)(GRID_WIDTH/2);
			playerY = GRID_HEIGHT - 1;
			}
	} //movePlayer

} //class
