/* TO-DO:
 * Prevent diagonal movement
 * Collision detection with objects
 * Movement of player on moving objects? (non square-based horizontal movement?)
 */

package application;


import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class Grid extends Application
{

	//Class constants & variables
	private final int GRID_WIDTH = 13; //Number of tiles in each row (13 default)			/* SCALING ONLY WORKS WHEN
	private final int GRID_HEIGHT = 14; //Number of tiles in each column (14 default)		 * GRID_WIDTH IS ODD &
	private int playerY = GRID_HEIGHT - 1; //Index of player row							 * GRID_HEIGHT IS EVEN
	private int playerX = (int)(GRID_WIDTH/2); //Index of player column						 */
	
	public static void main(String[] args)
	{
		launch(args);
	} //main
	
	@Override
	public void start(Stage mainStage) throws MalformedURLException 
	{
		mainStage.setWidth(900); //Set the width of the window, used to calculate grid tile size
		
		final double TILE_SIZE = (double)((int) ((mainStage.getWidth() - 100) / GRID_WIDTH)) ; //Calculate grid tile size: (windowWidth - padding) / grid width
		final double GRID_Y = TILE_SIZE * GRID_HEIGHT; //Grid height measurement
		final double PLAYER_SIZE = (TILE_SIZE - 5)/2; //Size of the player sprite
		
		ArrayList<ArrayList<Rectangle>> grid = gridGen(TILE_SIZE, GRID_WIDTH, GRID_HEIGHT); //generate game grid
		
		//Create a VBox to hold the grid
		VBox vbxGrid = new VBox();
		
		vbxGrid.setAlignment(Pos.CENTER);
		vbxGrid.toBack();
		
		//Draw the squares from the grid array list on to the scene
		for (int n = 0; n < GRID_HEIGHT; n++)  //1 iteration = 1 row
		{
			HBox row = new HBox(); //Create an HBox to hold a row
			
			for (int i = 0; i < GRID_WIDTH; i++) //Fill the row with squares (1 iteration = 1 square)
			{
				row.getChildren().add(grid.get(n).get(i)); //Take square from grid arraylist and add it to the row
				
				if ((n == playerY) && (i == playerX))
				{
					//playerStartX = row.getChildren().get(i).getBoundsInParent()
				}
			}
			
			row.setAlignment(Pos.CENTER); //Align the row to the center
			vbxGrid.getChildren().add(row); //Add the row to the grid VBox
		}
		
		//Draw the player
		Circle player = new Circle(PLAYER_SIZE);
		
		player.setFill(Color.YELLOW);
		player.setTranslateX(0); //Player start X
		player.setTranslateY((TILE_SIZE/2) + (TILE_SIZE*((GRID_HEIGHT/2)-1))); //Player start Y
		
		//Stack pane to put objects on top of each other
		StackPane stack = new StackPane();
		
		stack.getChildren().add(vbxGrid);
		stack.getChildren().add(player);
		
		//Border pane
		BorderPane pane = new BorderPane();
		
		pane.setCenter(stack);
		
		//Scene
		Scene mainScene = new Scene(pane, 800, GRID_Y + 100);
		
		mainScene.getStylesheets().add("style.css"); //import style sheet
		
		//Stage
		mainStage.setTitle("Class Simulator");
		mainStage.setScene(mainScene);
		mainStage.show();
		
		/* EVENT LISTENERS */
		
		//Key press
		mainScene.setOnKeyPressed(e -> {
			
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
				
			}
			
		}); //key press event
		
	} //start
	
	//Generate a 2d array list of squares to form a grid
	public ArrayList<ArrayList<Rectangle>> gridGen(double tileSize, int gridWidth, int gridHeight) throws MalformedURLException 
	{		
		ArrayList<ArrayList<Rectangle>> grid = new ArrayList<ArrayList<Rectangle>>(); //2d array list to hold rows
		
		//Coordinates of square being drawn
		double x = 0;
		double y = 0;
		   File file = new File("C:\\Users\\dixonb33916\\Downloads\\CISC1122-FisherFrogger-master\\Grid\\src\\application\\user-top-view.png"); 
		      File file1 = new File("C:\\Users\\dixonb33916\\Downloads\\CISC1122-FisherFrogger-master\\Grid\\src\\application\\desktop.png");
		      File file2 = new File("C:\\Users\\dixonb33916\\Downloads\\CISC1122-FisherFrogger-master\\Grid\\src\\application\\house.png");
		      String localUrl = file.toURI().toURL().toString();
		      String localUrl1 = file1.toURI().toURL().toString();
		      String localUrl2 = file2.toURI().toURL().toString();
		      // don't load in the background
		      Image localImage = new Image(localUrl);
		      Image localImage1 = new Image(localUrl1);
		      Image localImage2 = new Image(localUrl2);
		      ImagePattern pattern = new ImagePattern(localImage);
		      ImagePattern pattern1 = new ImagePattern(localImage1);
		      ImagePattern pattern2 = new ImagePattern(localImage2);
			
		
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
						if(n == 13 || n == 7) //if the row number is either 14 or 8, color purple for safe space
						{
						gridRow.get(i).setFill(Color.WHITE); //color rectangle at current index purple
						}
						else if (n == 1) //if the row number is 2, color purple for safe space
						{
							gridRow.get(i).setFill(pattern); //color rectangle at current index purple
						}
						else if (n == 3 && (i == 3 || i == 5)) //colors rectangles blue to show where moving object will be placed
						{
							gridRow.get(i).setFill(Color.BLUE); //colors rectangle blue
						}
						else {
							gridRow.get(i).setFill(Color.BLACK); //color rectangle at current index black
						}
					}
					else if(n == 13 || n == 7) //if the row number is either 14 or 8, color purple for safe space
					{
						gridRow.get(i).setFill(pattern2); //color rectangle at current index purple
					}
					else if(n == 1) //if the row number is 2, color purple for safe space
					{
						if (i % 2 != 0) //if the column number is even
						gridRow.get(i).setFill(pattern2); //color rectangle at current index purple
						else
							gridRow.get(i).setFill(Color.GREY); //color rectangle at current index purple
					}	
					else if (n == 3 && (i == 4)) //colors rectangles blue to show where moving object will be placed
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
						gridRow.get(i).setFill(Color.GREY); //color rectangle purple
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

}
