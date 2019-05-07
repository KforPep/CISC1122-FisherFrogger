/**
 * This class is the main window. It handles key press events
 * and send them to the Grid.
 */

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application{
	
	/***
	 * This class opens and controls the window.
	 * It shows the scene from Grid inside of it
	 */
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage mainStage) {
		try {
			
			// Set the window to the screen dimensions
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			mainStage.setWidth(screenSize.getWidth()); 		//Set the width of the window to fill screen
			mainStage.setHeight(screenSize.getHeight());	//Set the height of the window to fill screen
			
			// Add the gameplay Grid to the stage
			Grid gameGrid = new Grid();
			Pane returnedGamePane = gameGrid.start(mainStage.getHeight());	// Starts new Grid using mainStage's height for sizing
			
			// These Panes cover the left and right side of the screen
			// to hide vehicles entering and exiting the scene
			Pane leftSideCover = new Pane();
			leftSideCover.setMaxSize(((mainStage.getWidth() - mainStage.getHeight())/2), mainStage.getHeight());
			leftSideCover.setMinWidth((mainStage.getWidth() - mainStage.getHeight())/2);
			leftSideCover.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			Pane rightSideCover = new Pane();
			rightSideCover.setMaxSize((mainStage.getWidth() - mainStage.getHeight()/2), mainStage.getHeight());
			rightSideCover.setMinWidth((mainStage.getWidth() - mainStage.getHeight())/2);
			rightSideCover.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			
			// Set left, gameplay, and right on a BorderPane
			BorderPane totalScreen = new BorderPane();
			totalScreen.setCenter(returnedGamePane);
			totalScreen.setRight(rightSideCover);
			totalScreen.setLeft(leftSideCover);
			
			// Set up scene and send all key presses to the Grid
			Scene scene = new Scene(totalScreen);
			scene.setOnKeyPressed(e -> 
			{
				gameGrid.sendKeyEvent(e);
			});
			
			mainStage.setTitle("Class Simulator");
			mainStage.setScene(scene);
			mainStage.show();
			
			// Handles window size changes and resizes the leftSideCover and rightSideCover
			// without changing the game play region
			ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
			{
				leftSideCover.setMaxSize(((mainStage.getWidth() - gameGrid.getGameSize())/2), mainStage.getHeight());
				leftSideCover.setMinSize(((mainStage.getWidth() - gameGrid.getGameSize())/2), mainStage.getHeight());
				rightSideCover.setMaxSize(((mainStage.getWidth() - gameGrid.getGameSize())/2), mainStage.getHeight());
				rightSideCover.setMinSize(((mainStage.getWidth() - gameGrid.getGameSize())/2), mainStage.getHeight());
			};

		    mainStage.widthProperty().addListener(stageSizeListener);
		    mainStage.heightProperty().addListener(stageSizeListener); 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
