

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





