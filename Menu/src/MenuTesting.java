/*
 * Author: Christian Patterson and Jake Derpmando
 * Date: Spring 2019
 * Purpose: Recreate Pong with a bit of a scare twist >:)
 * */

import javafx.scene.input.KeyEvent;

import java.awt.Event;
import javafx.scene.input.MouseEvent;

import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.scene.control.MenuBar;

public class MenuTesting extends Application{
	private static int StageWidth = 800;
	private static int StageHeight = 600;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) {
		//Create the basics of the stage.
		myStage.setTitle("Creepy Pong");
		myStage.setHeight(StageHeight);
		myStage.setWidth(StageWidth);
		
		//Option Menu Stuff
		Menu menuOp = new Menu("Options");
		MenuItem username = new MenuItem("Username");
		MenuItem quit = new MenuItem("Quit");
		
		//Sound Menu Stuff
		Menu soundMenu = new Menu("Sound");
		//Character Noise Set up
		CheckBox charCheck = new CheckBox();
		Label charLabel = new Label("Character Noise");
		charCheck.setSelected(true);
		charLabel.setGraphic(charCheck);
		CustomMenuItem charNoise = new CustomMenuItem(charLabel);
		charNoise.setHideOnClick(false);
		
		//Ambient Noise Set up
		Label ambLabel = new Label("Ambient Noise");
		CheckBox ambCheck = new CheckBox();
		ambCheck.setSelected(true);
		ambLabel.setGraphic(ambCheck);
		CustomMenuItem ambNoise = new CustomMenuItem(ambLabel);
		ambNoise.setHideOnClick(false);
		
		//Add Menu Items
		menuOp.getItems().addAll(username, quit);
		soundMenu.getItems().addAll(charNoise, ambNoise);
		
		//Menu Events
		username.setOnAction(event ->{
			String user = JOptionPane.showInputDialog("Enter your username");
			JOptionPane.showMessageDialog(null, user);
		});
		
		quit.setOnAction(event ->{
			int op = JOptionPane.showConfirmDialog(null, "Really?","Don't Quit......staaaaaaaaaayyyy", JOptionPane.YES_NO_OPTION);
			if(op==0) {
				JOptionPane.showMessageDialog(null, "NOOOOO!!!!");
				myStage.close();
			}else {
				JOptionPane.showMessageDialog(null, "hehehe....BAHAHAHA");
			}
		});
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuOp, soundMenu);
		
		Pane setup = new Pane();
		setup.setPrefWidth(StageWidth);
		setup.setPrefHeight(StageHeight);
		
		setup.getChildren().addAll(menuBar);
		
		Scene scene = new Scene(setup, StageHeight, StageWidth);
		
		//Place the Scene on the Stage and reveal the application.
		myStage.setScene(scene);
		myStage.show();
	}
}
