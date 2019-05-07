import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {
	
	int row; //player row index
	int column; //player column index
	double x; //player X position
	double y; //player Y position
	double size; //player size
	boolean drowning = false; //is the player drowning?
	Color color; //player color
	
	public Player(double playerX, double playerY, double playerSize, Color playerColor)
	{
		x = playerX;
		y = playerY;
		size = playerSize;
		color = playerColor;
		
		this.setRadius(playerSize);
		this.setFill(playerColor);
		this.setTranslateX(playerX);
		this.setTranslateY(playerY);
	} //constructor

	//Method that will execute when the player collides with an object
	public void move(double newPlayerX, double newPlayerY)
	{	
		//Set player position
		this.setTranslateX(newPlayerX);
		this.setTranslateY(newPlayerY);
	} //move
	
} //class
