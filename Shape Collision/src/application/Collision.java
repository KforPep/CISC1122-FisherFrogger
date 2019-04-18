/* Object Collision
 * Caitlin, Colin & Ed
 */

package application;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Collision extends Application {

	public static void main(String[] args) {
		launch(args);
	} //main
	
	@Override
	public void start(Stage mainStage) {
		Circle player = new Circle(50); //Circle representing the player
		Rectangle log = new Rectangle(100, 50); //Rectangle representing the object (in this case, a log)
		
		//Shape styling
		log.setX(0);
		log.setY(200);
		log.setFill(Color.DARKRED);
		
		player.setCenterX(350);
		player.setCenterY(100);
		player.setFill(Color.ALICEBLUE);
		
		//Setting the stage...
		Pane pane = new Pane();
		pane.getChildren().addAll(player, log); //add shapes to the pane
		
		Scene mainScene = new Scene(pane, 1000, 750); //Create a scene with the layout pane
		
		mainStage.setScene(mainScene);
		mainStage.setTitle("Object Collision");
		mainStage.show();
		
		/* Code from moving object
		 * Instead of using LayoutX, we altered the code to change the X instead so
		 * that we could retrieve the bounds of the rectangle and compare them
		 */
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
			double dx = 5; //X velocity of animation
			Bounds bounds = pane.getBoundsInLocal(); //Bounds of the layout pane
			
			@Override
			public void handle(ActionEvent e) {
				log.setX(log.getX() + dx); //Move log
				
				//If the log reaches the edge of the pane
				if(log.getX() <= (bounds.getMinX()) || 
                        log.getX() >= (bounds.getMaxX() - log.getWidth()))
				{
	               	dx = -dx; //reverse direction
	               	
	               	//Fade in
	               	FadeTransition fadeTransition = new FadeTransition();
	          	    fadeTransition.setDuration(Duration.millis(3000)); //duration of fade animation
	          	    fadeTransition.setNode(log);
	          	    fadeTransition.setFromValue(0);
	          	    fadeTransition.setToValue(1);
	          	    fadeTransition.play();
                }
				
				if(log.getBoundsInLocal().intersects(player.getBoundsInLocal())) //Flicker color with collision
				{
					if (player.getFill() == Color.ALICEBLUE)
					{
						player.setFill(Color.CYAN);
					}
					else
					{
						player.setFill(Color.ALICEBLUE);
					}
				}
			}
		})); //timeline
		
		timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
		
		//Events
		mainScene.setOnMouseMoved(e -> { //Move circle object with mouse
			player.setCenterX(e.getX());
			player.setCenterY(e.getY());
		});
		
	} //onStart
	
} //class
