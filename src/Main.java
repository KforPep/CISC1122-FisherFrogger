	
import java.awt.Dimension;
import java.awt.Toolkit;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	/***
	 * This class opens and controls the window.
	 * It shows the scene from Grid inside of it
	 */
	@Override
	public void start(Stage mainStage) {
		try {
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			mainStage.setWidth(screenSize.getWidth()); 		//Set the width of the window to fill screen
			mainStage.setHeight(screenSize.getHeight());	//Set the height of the window to fill screen
			
			Grid gameGrid = new Grid();
			Pane returnedGamePane = gameGrid.start(mainStage.getHeight());	// Starts new Grid using mainStage's height for sizing
			
			Pane leftSideCover = new Pane();
			leftSideCover.setMaxSize(((mainStage.getWidth() - mainStage.getHeight())/2), mainStage.getHeight());
			leftSideCover.setMinWidth((mainStage.getWidth() - mainStage.getHeight())/2);
			leftSideCover.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			Pane rightSideCover = new Pane();
			rightSideCover.setMaxSize((mainStage.getWidth() - mainStage.getHeight()/2), mainStage.getHeight());
			rightSideCover.setMinWidth((mainStage.getWidth() - mainStage.getHeight())/2);
			rightSideCover.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			
			BorderPane totalScreen = new BorderPane();
			
			totalScreen.setCenter(returnedGamePane);
			totalScreen.setRight(rightSideCover);
			totalScreen.setLeft(leftSideCover);
			
			Scene scene = new Scene(totalScreen);
			
			mainStage.setTitle("Class Simulator");
			mainStage.setScene(scene);
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
