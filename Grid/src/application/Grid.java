/* TO-DO:
 * Prevent diagonal movement?
 * Movement of player on moving objects
 * Smoother animations
 */

package application;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Grid extends Application
{

	//Class constants & variables
	private final int GRID_WIDTH = 13; //Number of tiles in each row (13 default)			/* SCALING ONLY WORKS WHEN
	private final int GRID_HEIGHT = 14; //Number of tiles in each column (14 default)		 * GRID_WIDTH IS ODD &
	private int playerY = GRID_HEIGHT - 1; //Index of player row							 * GRID_HEIGHT IS EVEN
	private int playerX = (int)(GRID_WIDTH/2); //Index of player column						 */
	final double PADDING = 16; //Width of spacing around the grid
	
	public static void main(String[] args)
	{
		launch(args);
	} //main
	
	@Override
	public void start(Stage mainStage) 
	{
		mainStage.setWidth(900); //Set the width of the window, used to calculate grid tile size
		
		final double TILE_SIZE = (double)((int) ((mainStage.getWidth() - PADDING) / GRID_WIDTH)) ; //Calculate grid tile size
		final double GRID_Y = TILE_SIZE * GRID_HEIGHT; //Grid height measurement
		final double PLAYER_SIZE = (TILE_SIZE - 5)/2; //Size of the player sprite
		final double OBJECT_SPAWN_DISTANCE = TILE_SIZE*1.5;
		
		//Layout positions of centers of tiles RELATIVE TO CENTER OF THE GRID
		final double BOTTOM_ROW = (TILE_SIZE * ((GRID_WIDTH - 1)/2)) + (TILE_SIZE/2);
			/* Use BOTTOM_ROW - (TILE_SIZE * row number) (base-0) to calculate the position of a row
			 * Example: Row 3 = (BOTTOM_ROW - (TILE_SIZE * 2))
			 */
		
		final double RIGHT_COLUMN = (TILE_SIZE * (GRID_WIDTH/2));
		final double LEFT_COLUMN = -RIGHT_COLUMN;
		final double MIDDLE_COLUMN = 0;
		
		//Player repsawn location
		final double RESPAWN_X = MIDDLE_COLUMN; //respawn column
		final double RESPAWN_Y = BOTTOM_ROW; //respawn row
		
		ArrayList<ArrayList<Rectangle>> grid = gridGen(TILE_SIZE, GRID_WIDTH, GRID_HEIGHT); //generate game grid
		
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
		
		//Draw the player
		Circle player = new Circle(PLAYER_SIZE);
		
		player.setFill(Color.YELLOW);
		player.setTranslateX(MIDDLE_COLUMN); //Player start X
		player.setTranslateY(BOTTOM_ROW); //Player start Y
		
		/* OBJECTS WITH COLLISION */
		
		//Cars 1 (row 2)
		int car1Count = 3; 
		int car1Space = 50; //MS delay between animations
		int car1Speed = 60; //MS delay between frame updates in the car animation
		int car1Velocity = 5; //Distance to move per frame
		double car1Size = TILE_SIZE - 10;
		double car1Width = car1Size;
		double car1Height = car1Size;
		double car1X = RIGHT_COLUMN + OBJECT_SPAWN_DISTANCE; //start column
		double car1Y = BOTTOM_ROW - (TILE_SIZE*1); //start row
		Color car1Color = Color.YELLOW;
		String car1AnimType = "LEFT";
		ArrayList<Rectangle> cars1 = createRectangleArray(car1Count, car1Width, car1Height, car1X, car1Y, car1Color); //Array to hold the cars
		
		//Cars 2 (row 3)
		int car2Count = 3;
		int car2Space = 50; //MS delay between animations
		int car2Speed = 55; //MS delay between frame updates in the car animation
		int car2Velocity = 5; //Distance to move per frame
		double car2Size = TILE_SIZE - 10;
		double car2Width = car2Size;
		double car2Height = car2Size;
		double car2X = LEFT_COLUMN - OBJECT_SPAWN_DISTANCE; //start column
		double car2Y = BOTTOM_ROW - (TILE_SIZE*2); //start row
		Color car2Color = Color.ORANGERED;
		String car2AnimType = "RIGHT";
		ArrayList<Rectangle> cars2 = createRectangleArray(car2Count, car2Width, car2Height, car2X, car2Y, car2Color); //Array to hold the cars
		
		
		//Cars 3 (row 4)
		int car3Count = 3; //number of cars
		int car3Space = 60; //frames to wait before the next car moves
		int car3Speed = 35; //milliseconds per frame
		int car3Velocity = 5; //Distance to move per frame
		double car3Height = TILE_SIZE - 10;
		double car3Width = car3Height + 15;
		double car3X = RIGHT_COLUMN + OBJECT_SPAWN_DISTANCE; //start column
		double car3Y = BOTTOM_ROW - (TILE_SIZE*3); //start row
		Color car3Color = Color.MEDIUMPURPLE;
		String car3AnimType = "LEFT";
		ArrayList<Rectangle> cars3 = createRectangleArray(car3Count, car3Width, car3Height, car3X, car3Y, car3Color); //create array of cars
		
		//Cars 4 (row 5)
		int car4Count = 2; //number of cars
		int car4Space = 125; //frames to wait before the next car moves
		int car4Speed = 30; //milliseconds per frame
		int car4Velocity = 5; //Distance to move per frame
		double car4Height = TILE_SIZE - 15;
		double car4Width = TILE_SIZE + 20;
		double car4X = LEFT_COLUMN - OBJECT_SPAWN_DISTANCE; //start column
		double car4Y = BOTTOM_ROW - (TILE_SIZE*4); //start row
		Color car4Color = Color.LAWNGREEN;
		String car4AnimType = "RIGHT";
		ArrayList<Rectangle> cars4 = createRectangleArray(car4Count, car4Width, car4Height, car4X, car4Y, car4Color); //create array of cars
		
		//Trucks (row 6)
		int truckCount = 2; //number of trucks
		int truckSpace = 75; //frames to wait before the next truck moves
		int truckSpeed = 45; //milliseconds per frame
		int truckVelocity = 5; //Distance to move per frame
		double truckWidth = (TILE_SIZE*2) - 10;
		double truckHeight = TILE_SIZE - 10;
		double truckX = RIGHT_COLUMN + OBJECT_SPAWN_DISTANCE; //Start column
		double truckY = BOTTOM_ROW - (TILE_SIZE*5); //Start row
		Color truckColor = Color.ANTIQUEWHITE;
		String truckAnimType = "LEFT";
		ArrayList<Rectangle> trucks = createRectangleArray(truckCount, truckWidth, truckHeight, truckX, truckY, truckColor); //Create array of trucks
		
		//Logs 1 (row 9)
		int log1Count = 3; //number of logs
		int log1Space = 85; //frames to wait before the next log moves
		int log1Speed = 55; //milliseconds per frame
		int log1Velocity = 5; //Distance to move per frame
		double log1Width = (TILE_SIZE*2);
		double log1Height = (TILE_SIZE) - 15;
		double log1X = LEFT_COLUMN - OBJECT_SPAWN_DISTANCE; //Start column
		double log1Y = BOTTOM_ROW - (TILE_SIZE*8); //Start row
		Color log1Color = Color.SADDLEBROWN;
		String log1AnimType = "RIGHT";
		ArrayList<Rectangle> logs1 = createRectangleArray(log1Count, log1Width, log1Height, log1X, log1Y, log1Color); //create array of logs
		
		//Logs 2 (row 10)
		int log2Count = 2; //number of logs
		int log2Space = 125; //frames to wait before the next log moves
		int log2Speed = 30; //millis per frame
		int log2Velocity = 5; //Distance to move per frame
		double log2Width = (TILE_SIZE*4);
		double log2Height = log1Height;
		double log2X = (LEFT_COLUMN - OBJECT_SPAWN_DISTANCE) - 75; //Start column
		double log2Y = BOTTOM_ROW - (TILE_SIZE*9); //Start row
		Color log2Color = log1Color;
		String log2AnimType = "RIGHT";
		ArrayList<Rectangle> logs2 = createRectangleArray(log2Count, log2Width, log2Height, log2X, log2Y, log2Color); //create array of logs
		
		//Logs 3 (row 12)
		int log3Count = 2; //number of logs
		int log3Space = 65; //frames to wait before next log
		int log3Speed = 55; //millis per frame
		int log3Velocity = 5; //Distance per frame
		double log3Width = (TILE_SIZE*3);
		double log3Height = log1Height;
		double log3X = (LEFT_COLUMN - OBJECT_SPAWN_DISTANCE) - 75; //Start column
		double log3Y = BOTTOM_ROW - (TILE_SIZE*11); //Start row
		Color log3Color = log1Color;
		String log3AnimType = "RIGHT";
		ArrayList<Rectangle> logs3 = createRectangleArray(log3Count, log3Width, log3Height, log3X, log3Y, log3Color); //create array of logs
		
		//Logs 3-2 (row 12)
		int log3_2Delay = 100; //frames to wait before starting the animation
		ArrayList<Rectangle> logs3_2 = createRectangleArray(log3Count, log3Width, log3Height, log3X, log3Y, Color.MEDIUMPURPLE); //array of logs
		
		//Stack pane to put objects on top of each other
		StackPane stack = new StackPane();
		
		stack.getChildren().add(vbxGrid); //Game grid
		stack.getChildren().add(player); //Player
		arrayListToStackPane(cars1, stack); //Cars (row 2)
		arrayListToStackPane(cars2, stack); //Cars (row 3)
		arrayListToStackPane(cars3, stack); //Cars (row 4)
		arrayListToStackPane(cars4, stack); //Cars (row 5)
		arrayListToStackPane(trucks, stack); //Trucks (row 6)
		arrayListToStackPane(logs1, stack); //Logs (row 9)
		arrayListToStackPane(logs2, stack); //Logs (row 10)
		arrayListToStackPane(logs3, stack); //Logs (row 12)
		arrayListToStackPane(logs3_2, stack); //Logs (row 12)
		
		stack.setAlignment(Pos.CENTER); //Stack pane alignment
		stack.setMaxHeight(GRID_HEIGHT);
		stack.setMaxWidth(GRID_WIDTH);
		
		//Border pane to hold the stack pane
		BorderPane pane = new BorderPane(stack);
		
		//Scene
		Scene mainScene = new Scene(pane, 800, GRID_Y + PADDING);
		
		mainScene.getStylesheets().add("style.css"); //import style sheet
		
		//Stage
		//mainStage.setResizable(false);
		mainStage.setTitle("Class Simulator");
		mainStage.setScene(mainScene);
		mainStage.show();
		
		/* OBJECT ANIMATIONS */
		
		//Create and start animations
		animateRectangles(cars1, car1AnimType, player, stack, car1Space, car1Speed, car1Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Cars 1 (row 2) animations
		animateRectangles(cars2, car2AnimType, player, stack, car2Space, car2Speed, car2Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Cars 2 (row 3) animations
		animateRectangles(cars3, car3AnimType, player, stack, car3Space, car3Speed, car3Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Cars 3 (row 4) animations
		animateRectangles(cars4, car4AnimType, player, stack, car4Space, car4Speed, car4Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Cars 4 (row 5) animations
		animateRectangles(trucks, truckAnimType, player, stack, truckSpace, truckSpeed, truckVelocity, 0, RESPAWN_X, RESPAWN_Y, true); //Trucks (row 6) animations
		animateRectangles(logs1, log1AnimType, player, stack, log1Space, log1Speed, log1Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Logs (row 9) animations
		animateRectangles(logs2, log2AnimType, player, stack, log2Space, log2Speed, log2Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Logs (row 10) animations
		animateRectangles(logs3, log3AnimType, player, stack, log3Space, log3Speed, log3Velocity, 0, RESPAWN_X, RESPAWN_Y, true); //Logs (row 12) animations
		animateRectangles(logs3_2, log3AnimType, player, stack, log3Space, log3Speed, log3Velocity, log3_2Delay, RESPAWN_X, RESPAWN_Y, true); //Logs (row 12) animations
		
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
					if (playerY > 0)
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
					if (playerY < (GRID_HEIGHT - 1))
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
					if (playerX > 0)
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
					if (playerX < (GRID_WIDTH - 1))
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
	public ArrayList<ArrayList<Rectangle>> gridGen(double tileSize, int gridWidth, int gridHeight) 
	{		
		ArrayList<ArrayList<Rectangle>> grid = new ArrayList<ArrayList<Rectangle>>(); //2d array list to hold rows
		
		//Coordinates of square being drawn
		double x = 0;
		double y = 0;
		
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
						if(n == 13 || n == 7 || n == 1) //if the row number is either 14, 8, or 2, color purple for safe space
						{
							gridRow.get(i).setFill(Color.PURPLE); //color rectangle at current index purple
						}
						else if (n >= 2 && n <= 6) //color rows 3-7 blue for water
						{
							gridRow.get(i).setFill(Color.BLUE); //colors rectangle blue
						}
						else {
							gridRow.get(i).setFill(Color.BLACK); //color rectangle at current index black
						}
					}
					else if(n == 13 || n == 7 || n == 1) //if the row number is either 14, 8 or 2, color purple for safe space
					{
						gridRow.get(i).setFill(Color.PURPLE); //color rectangle at current index purple
					}
					else if (n >= 2 && n <= 6) //color rows 3-7 blue for water
					{
						gridRow.get(i).setFill(Color.BLUE); //colors rectangle blue
					}
					else //if column number is even
					{		
						gridRow.get(i).setFill(Color.GREY); //color rectangle grey
					}
				}
				else //if the row number is even
				{
					if (i % 2 != 0) //if the column number is even
					{
						if (n == 0) //if the row number is 1, color purple for safe space
						{
							gridRow.get(i).setFill(Color.PURPLE); //color rectangle purple
						}			
						else if (n >= 2 && n <= 6) //color rows 3-7 blue for water
						{
							gridRow.get(i).setFill(Color.BLUE);
						}
						else {
							gridRow.get(i).setFill(Color.GREY); //color rectangle grey
						}
					}
					else if (n == 0) //if the row number is 1, color purple for safe space
					{
						gridRow.get(i).setFill(Color.PURPLE); //color rectangle purple
					}
					else if (n >= 2 && n <= 6) //color rows 3-7 blue for water
					{
						gridRow.get(i).setFill(Color.BLUE);
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
	
	//Create a rectangle array
	public ArrayList<Rectangle> createRectangleArray(int vehicleCount, double width, double height, double x, double y, Color color)
	{
		ArrayList<Rectangle> vehicles = new ArrayList<Rectangle>(); //Array to hold the vehicles
		int numberOfVehicles = vehicleCount;
		
		for (int i = 0; i < numberOfVehicles; i++)
		{
			//Create a truck
			Rectangle vehicle = new Rectangle();
			vehicle.setWidth(width);
			vehicle.setHeight(height);
			vehicle.setTranslateX(x);
			vehicle.setTranslateY(y);
			vehicle.setFill(color);
			
			//Put the truck into the trucks array
			vehicles.add(vehicle);
		}
		
		return vehicles;
	} //createRectangleArray
	
	//Add an array list's objects to the pane
	public void arrayListToStackPane(ArrayList<Rectangle> arrayList, StackPane pane)
	{
		int listSize = arrayList.size(); //Size of the array list
		
		//Cycle through each index and add the object to the pane
		for (int i = 0; i < listSize; i++)
		{
			pane.getChildren().add(arrayList.get(i));
		}
	} //arrayListToStackPane
	
	//Create an animation for an array list of rectangles
	public void animateRectangles(ArrayList<Rectangle> arrayList, String animationType, Circle player, StackPane stack,
									int space, int speed, int velocity, int startDelay, double playerCollisionNewX, double playerCollisionNewY, boolean indefinite)
	{
		try
		{
			for (int i = 0; i < arrayList.size(); i++)
			{	
				Timeline carAnimation = createTimeline(animationType, arrayList.get(i), player, stack.getBoundsInLocal(),
															(i * space), speed, velocity, (startDelay*i), playerCollisionNewX, playerCollisionNewY, true);
				carAnimation.play();
			}
		}
		catch (NullPointerException ex)
		{
			System.out.println("ERROR! Invalid animation type.");
			ex.printStackTrace();
		}
	} //animateRectangles
	
	//Create a timeline animation
	public Timeline createTimeline(String animationType, Rectangle object, Circle player, Bounds paneBounds,
									int delay, int speedMillis, int xVelocity, int startDelay, double newPlayerX, double newPlayerY, boolean indefinite)
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
							//Movement with collision
							movePlayer(player, newPlayerX, newPlayerY);
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
							//Movement with collision
							movePlayer(player, newPlayerX, newPlayerY);
						}
						
						frame++;
					}
				}
			}));
		}
		
		//Left animation delayed
		else if (animationType.equalsIgnoreCase("LEFT-DELAY"))
		{
			
		}
		
		//Determine whether the animation is indefinite
		if (indefinite == true)
		{
			animation.setCycleCount(Timeline.INDEFINITE);
		}
		
		return animation;
	} //createTimeline
	
	//Method that will execute when the player collides with an object
	public void movePlayer(Circle player, double newPlayerX, double newPlayerY)
	{	
		//Set player position
		player.setTranslateX(newPlayerX);
		player.setTranslateY(newPlayerY);
		playerX = (int)(GRID_WIDTH/2);
		playerY = GRID_HEIGHT - 1;
	} //movePlayer

} //class
