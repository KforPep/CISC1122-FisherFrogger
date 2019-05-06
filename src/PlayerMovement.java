/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package froggerapplication;

import javafx.util.Duration;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;

/**
 *
 * @author colin
 */
public class PlayerMovement extends Application {
	
	private static int stop = 1;
	private static boolean isMoving = false;
	private double justWorkX = 0;
	private double justWorkY = 0;
	private double justWorkA = 0;
	
	private int blastingSpeed = 250;
	
    public static void main(String args[]) {
        launch(args);
    }
    
    public void start(Stage myStage) {
        
        final double START_X = 0.0; 
        final double START_Y = 0.0;
        final double SCENE_WIDTH = 500.0;
        final double SCENE_HEIGHT = 500.0;
        //May want to change to Grid- width and height.
        final double PLAYER_WIDTH = 50.0;
        final double PLAYER_HEIGHT = 50.0;
        
        Rectangle player = new Rectangle();
        player.setX(START_X);
        player.setY(START_Y);
        player.setWidth(PLAYER_WIDTH);
        player.setHeight(PLAYER_HEIGHT);
        ImagePattern still = new ImagePattern(new Image("/Spongebob_Squarepants.png"));
        ImagePattern jump = new ImagePattern(new Image("/jump.jpg"));
        player.setFill(still);
        
        Pane window = new Pane(player);
        
        Scene scene = new Scene(window, SCENE_WIDTH, SCENE_HEIGHT);
        
        TranslateTransition drawer = new TranslateTransition(new Duration(blastingSpeed), player);
        drawer.setFromX(player.getX());
        drawer.setFromY(player.getY());
        
        RotateTransition rt = new RotateTransition(Duration.millis(200), player);
        rt.setFromAngle(justWorkA);
        
        scene.setOnKeyPressed( event -> 
        {
        	System.out.println("X = " + drawer.getFromX());
    		System.out.println("Y = " + drawer.getFromY());
            if (event.getText().equals("w") && justWorkY > 0 && isMoving==false) {
            	isMoving = true;
            	rt.setToAngle(0);
            	rt.play();
            	rt.setFromAngle(0);
            	drawer.setToY(justWorkY - PLAYER_HEIGHT);
            	justWorkY -= PLAYER_HEIGHT;
            }else
            if (event.getText().equals("s") && justWorkY < window.getHeight() - player.getHeight() && isMoving==false) {
            	isMoving = true;
            	rt.setToAngle(180);
            	rt.play();
            	rt.setFromAngle(180);
            	drawer.setToY(justWorkY + PLAYER_HEIGHT);
            	justWorkY += PLAYER_HEIGHT;
            }else
            if (event.getText().equals("a") && justWorkX > 0 && isMoving==false) {
            	isMoving = true;
            	rt.setToAngle(270);
            	rt.play();
            	rt.setFromAngle(270);
            	drawer.setToX(justWorkX - PLAYER_WIDTH);
            	justWorkX -= PLAYER_WIDTH;
            }else
            if (event.getText().equals("d") && justWorkX < window.getWidth() - player.getWidth() && isMoving==false) {
            	isMoving = true;
            	rt.setToAngle(90);
            	rt.play();
            	rt.setFromAngle(90);
            	drawer.setToX(justWorkX + PLAYER_WIDTH);
            	justWorkX += PLAYER_WIDTH;
            }else
            if(event.getText().equals("9")) {
            	JOptionPane.showMessageDialog(null, "You Win!!!");
            	myStage.close();
            }
            
            
            player.setFill(jump);
            drawer.play();
            drawer.setFromX(justWorkX);
            drawer.setFromY(justWorkY);
        });
        
        drawer.setOnFinished(event -> {
        	player.setFill(still);
        	isMoving = false;
        });
        
        myStage.setResizable(false);
        myStage.sizeToScene();
        myStage.setTitle("FROGGER!!!!!!!!!!!!!!!!!!!!!!!!!!");
        myStage.setScene(scene);
        myStage.show();
    }
    
}
