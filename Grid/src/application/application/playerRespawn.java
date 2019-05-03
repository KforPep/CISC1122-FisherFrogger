package application;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class playerRespawn extends Grid
{
	private int lives = 3;
	
	public void Respawn(Circle player)
	{
		player.setTranslateX(0);
		player.setTranslateY(0);
	}
	
	public void loseLife(Label label)
	{
		lives--;
		if (lives > 0)
		{
			label.setText("Lives: " + lives);
		}
		else if(lives == 0)
		{
			//Filler Spot for game over
		}
		
	}
}
