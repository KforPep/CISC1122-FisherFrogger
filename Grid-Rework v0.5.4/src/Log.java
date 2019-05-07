/**
 * 
 */


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.util.*;

/**
 * @author eshlemanj
 *
 */

    
public class Log extends Application{
	Circle ball = new Circle(100, Color.WHITE);
	long startTime = System.currentTimeMillis();
	String currTime;
	
	public void start(Stage stage) {
		//creates the playing field
	    Pane canvas = new Pane();
	    Scene scene = new Scene(canvas, 700, 500, Color.BLACK);
	    stage.setScene(scene);
	    stage.show();
	    stage.setTitle("Don't be too afraid");
	    System.out.println("Start");
		//calls newBall() to start the game
		
	    canvas.getChildren().add(ball);
	    ball.relocate(5, 200);
	   
	   
	    
	    
	    
	
	
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {

        	double dx = 5; //Step on x or velocity
        	double dy = 0; //Step on y
        	
        	 @Override
        	 public void handle(ActionEvent t) {
              	//move the ball
         	
              	ball.setLayoutX(ball.getLayoutX() + dx);
              	ball.setLayoutY(ball.getLayoutY() + dy);
              	
             

                  Bounds bounds = canvas.getBoundsInLocal();
                  
                  //If the ball hits the left or right border reverse the x velocity
                  if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) || 
                          ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){

                 	 dx = -dx;
                 	 //newBall();
                 	 System.out.println("x");
                 	FadeTransition fadeTransition = new FadeTransition();
            	    fadeTransition.setDuration(Duration.millis(3000));
            	    fadeTransition.setNode(canvas);
            	    fadeTransition.setFromValue(0);
            	    fadeTransition.setToValue(1);
            	    fadeTransition.play();
            	    
                  }
                  //If the ball hits the bottom or top border reverse the y velocity
                  if((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) || 
                          (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){

                  	dy = -dy;
                  	System.out.println("y");
                  }
              }
		}));
	timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
	}
	public static void main(String[] args) {System.out.println("Main");
		// TODO Auto-generated method stub
	launch();
	}
	
}
