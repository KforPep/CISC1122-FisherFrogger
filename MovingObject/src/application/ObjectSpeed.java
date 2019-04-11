package application;

import java.util.Random;

/**
* @author nicholsonp
*
*/
public class ObjectSpeed {

	SpeedTypes myType;
	int currentSpeed = 0;

	enum SpeedTypes {
		FastToSlow,
		SlowFastSlow,
		FastSlowFast,
		SlowToFast,
		Constant;
	}

// On construction we need to decide which speed type is for this object
// and then return what the speed is

		public ObjectSpeed()
		{
			int newSpeed = new Random().nextInt(5) + 1;
			switch (newSpeed)
			{
			case 1: // FastToSlow
				myType = SpeedTypes.FastToSlow;
				currentSpeed = 5;
				break;
			case 2: // SlowFastSlow
				myType = SpeedTypes.SlowFastSlow;
				currentSpeed = 2;
				break;
			case 3: // FastSlowFast
				myType = SpeedTypes.FastSlowFast;
				currentSpeed = 5;
				break;
			case 4: // SlowToFast
				myType = SpeedTypes.SlowToFast;
				currentSpeed = 2;
				break;
			default: // Constant and catch all
				myType = SpeedTypes.Constant;
				currentSpeed = 5;
			}
			
			
		}

		public int getCurrentSpeed(int currentXLocation, int direction)
		{
			int returnSpeed = 5;
			// Using location, decide what speed is object is moving and return it
			if (myType == SpeedTypes.FastToSlow)
			{
				// Has split 1/2 way across screen
				// First half: speed 5
				// Second half: speed 2

				// If direction == 1 (ltr)
				// if currentXLocation > SCREEN_MIDDLE
				// speed changes to 2
				// Else if direction == -1 (rtl)
				// if currentXLocation < SCREEN_MIDDLE
				// speed changes to 2
			}
			if (myType == SpeedTypes.Constant)
			{
				returnSpeed = currentSpeed;
			}
			return returnSpeed;
			
		}
		
		
		
		
		
}

// TUTORIAL CODE FOUND ONLINE FOR JAVAFX INTERPOLATOR

//private void init(Stage primaryStage) {
//    Group root = new Group();
//    primaryStage.setResizable(false);
//    primaryStage.setScene(new Scene(root, 250, 90));
//
//    //create circles by method createMovingCircle listed below
//    Circle circle1 = createMovingCircle(Interpolator.LINEAR); //default interpolator
//    circle1.setOpacity(0.7);
//    Circle circle2 = createMovingCircle(Interpolator.EASE_BOTH); //circle slows down when reached both ends of trajectory
//    circle2.setOpacity(0.45);
//    Circle circle3 = createMovingCircle(Interpolator.EASE_IN);
//    Circle circle4 = createMovingCircle(Interpolator.EASE_OUT);
//    Circle circle5 = createMovingCircle(Interpolator.SPLINE(0.5, 0.1, 0.1, 0.5)); //one can define own behaviour of interpolator by spline method
//    
//    root.getChildren().addAll(
//            circle1,
//            circle2,
//            circle3,
//            circle4,
//            circle5
//    );
//}




