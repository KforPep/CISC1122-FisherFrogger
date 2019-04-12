package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//changes
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
}

class Book extends Projectiles
{
	public Book()
	{
		fileName = "File:///" + System.getProperty("user.dir") + "/stackofbooks.png";
		width = 100;
		height = 100;
		setupImage();
	}
	
	public Book(int x, int y)
	{
		fileName = "File:///" + System.getProperty("user.dir") + "/stackofbooks.png";
		width = 100;
		height = 100;
		xvalue = x;
		yvalue = y;
		setupImage();
	}
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
	private void setupImage()
	{
		image = new Image(fileName);
		view = new ImageView(image);
		view.setX(xvalue); 
	    view.setY(yvalue);
		view.setFitHeight(100); 
	    view.setFitWidth(500); 
	    view.setPreserveRatio(true); 
	}
}