package modes;

import logic.IRSListener;
import logic.Motors;

public class ManualControl {
	
	private Motors motors;
	private IRSListener irsl;
	private boolean armDown = false;
	
	public ManualControl(IRSListener irsl, Motors importedMotors) {
		this.motors = importedMotors;
		this.irsl = irsl;
	}
	
	public void manualControl() {
		
		int remoteCommand = this.irsl.checkForTwoButtonCommands(0);
		
		switch(remoteCommand) {
			case 1:
				// both forward
				this.motors.setDriveAcceleration(10000000);
				while (this.irsl.checkForTwoButtonCommands(0) != 0) {
					this.motors.driveForward();
				}
				this.motors.stopDriveMotors();
				break;
			case 2:
				// left, forward
				this.motors.setDriveAcceleration(10000000);
				while (this.irsl.checkForTwoButtonCommands(0) != 0) {
					this.motors.turnRight();
				}
				this.motors.stopDriveMotors();
				break;
			case 3:
				// both backward
				this.motors.setDriveAcceleration(10000000);
				while(this.irsl.checkForTwoButtonCommands(0) != 0) {		// Both motors backward while button is pressed
					this.motors.driveBackward();
				}
				this.motors.stopDriveMotors();							// stop all motors. same principle applies to all four controller buttons
				break;
			case 4:
				// right, forward
				this.motors.setDriveAcceleration(10000000);
				while (this.irsl.checkForTwoButtonCommands(0) != 0) {
					this.motors.turnLeft();
				}
				this.motors.stopDriveMotors();
				break;
			case 8:
				// arm down
				armToggle();
			default:											// if no button is pressed the loop will start again
		}
	}
	
	public void armToggle() {
		if(!this.armDown) {
			this.motors.colorSensorArmDown();
			this.armDown = true;
		} else {
			this.motors.colorSensorArmUp();
			this.armDown = false;
		}
	}
}