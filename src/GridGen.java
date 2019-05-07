/*
 * Bridget Dixon, Caitlin, and Phil Nicholson
 * This class creates and returns the Grid to the main program to
 * display as the background.
 */

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GridGen {
	//Generate a 2d array list of squares to form a grid
	
	public GridGen()
	{
		
	}
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
}
