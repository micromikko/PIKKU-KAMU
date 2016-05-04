package modes;

import logic.Motors;

public class ManualControl {
	
	private Motors motors;
	
	public ManualControl(Motors importedMotors) {
		this.motors = importedMotors;
	}
	
	public void manualControl(int remoteCommand, int remoteChannel) {
		
		switch(remoteCommand) {
			case 1:
				// both forward
				this.motors.setAcceleration(10000000);
				while (remoteChannel != 0) {
					this.motors.driveForward();
		//			UI.bothForward();
				}
				this.motors.stopMotors();
				break;
			case 2:
				// left, forward
				this.motors.setAcceleration(10000000);
				while (remoteChannel != 0) {
					this.motors.turnRight();
		//			UI.leftForward();
				}
				this.motors.stopMotors();
				break;
			case 3:
				// both backward
				this.motors.setAcceleration(10000000);
				while(remoteChannel != 0) {		// Both motors backward while button is pressed
					this.motors.driveBackward();
		//			UI.bothBackward();							// prints issued command to screen
				}
				this.motors.stopMotors();							// stop all motors. same principle applies to all four controller buttons
				break;
			case 4:
				// right, forward
				this.motors.setAcceleration(10000000);
				while (remoteChannel != 0) {
					this.motors.turnLeft();
		//			UI.rightForward();
				}
				this.motors.stopMotors();
				break;
			default:											// if no button is pressed the loop will start again
		}
	}
	
}