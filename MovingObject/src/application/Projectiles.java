package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Parent Class
public class Projectiles 
{
	protected String fileName;
	protected double xvalue;
	protected double yvalue;
	protected int width;
	protected int height;
	protected double speed;
	protected int direction;
	protected Image image;
	protected ImageView view;
	
	public void setSpeed(int x)
	{
		speed = x;
	}
	public void setDirection(int x)
	{
		direction = x;
	}
	public ImageView getImage()
	{
		return view;
	}
}

class Book extends Projectiles //book inherents projectile's variables and set-get functions
{
	public Book(int x, int y) //Contructor requires x and y location values
	{
		fileName = "File:///" + System.getProperty("user.dir") + "/stackofbooks.png";
		width = 100;
		height = 100;
		xvalue = x;
		yvalue = y;
		setupImage();
	}
	
	
	private void setupImage() //returns imageview to be added to scene
	{
		image = new Image(fileName);
		view = new ImageView(image);
		view.setX(xvalue); 
	    view.setY(yvalue);
		view.setFitHeight(height); 
	    view.setFitWidth(width); 
	    view.setPreserveRatio(true); 
	}
}

class Code extends Projectiles //code inherents projectile's variables and set-get functions
{
	public Code(int x, int y)//Contructor requires x and y location values
	{
		fileName = "File:///" + System.getProperty("user.dir") + "/code.PNG";
		width = 100;
		height = 200;
		xvalue = x;
		yvalue = y;
		setupImage();
	}
	
	
	private void setupImage() //returns imageview to be added to scene
	{
		image = new Image(fileName);
		view = new ImageView(image);
		view.setX(xvalue); 
	    view.setY(yvalue);
		view.setFitHeight(width); 
	    view.setFitWidth(height);
	    
	}
}
