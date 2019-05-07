
import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MovingObject extends Rectangle
{
	
	public int count = 0;
	public int space = 0;
	public int speed = 0;
	public int velocity = 0;
	public double width = 0;
	public double height = 0;
	public double x = 0;
	public double y = 0;
	public Color color = Color.BLACK;
	String animType = "";
	public boolean infiniteAnim = true; //infinite animation?
	public boolean carry; //will the moving object carry the player?
	ArrayList<MovingObject> array;
	
	public MovingObject()
	{
		
	}
	
	public MovingObject(int objCount, int objSpace, int objSpeed, int objVelocity, double objWidth, double objHeight,
								double objX, double objY, Color objColor, String objAnimType, boolean objWillCarry)
	{
		count = objCount;
		space = objSpace;
		speed = objSpeed;
		velocity = objVelocity;
		width = objWidth;
		height = objHeight;
		x = objX;
		y = objY;
		color = objColor;
		animType = objAnimType;
		carry = objWillCarry;
		array = this.createArray();
	}
	
	//Create a moving object array
	public ArrayList<MovingObject> createArray()
	{
		ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>(); //Array to hold the vehicles
		int objCount = count;
		double objWidth = width;
		double objHeight = height;
		double objX = x;
		double objY = y;
		boolean objCarry = carry;
		Color objColor = color;
		
		for (int i = 0; i < objCount; i++)
		{
			//Create a truck
			MovingObject obj = new MovingObject();
			obj.setWidth(objWidth);
			obj.setHeight(objHeight);
			obj.setTranslateX(objX);
			obj.setTranslateY(objY);
			obj.setFill(objColor);
			//stores a boolean in the object that tells it to hit if false or carry if true
			obj.carry = objCarry;
			//Put the truck into the trucks array
			movingObjects.add(obj);
		}
		
		return movingObjects;
	} //createArray
	
	//Add the moving objects array to a pane
	public void toPane(StackPane pane)
	{
		int listSize = array.size(); //Size of array of objects
		
		//Cycle through each index and add the object to the pane
		for (int i = 0; i < listSize; i++)
		{
			pane.getChildren().add(array.get(i));
		}
	} //toPane
	public boolean getCarry(boolean objCarry) {
		return carry;
	}
	
} //class
