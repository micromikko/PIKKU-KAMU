package modes;

import logic.ColorSensorArm;
import logic.IRSListener;
import logic.Motors;

/**
 * 
 * @author micromikko
 *
 */
public class ManualControl {

	private IRSListener irsl;
	private Motors motors;
	private ColorSensorArm csa;
	private boolean armDown = false;
	
	public ManualControl(IRSListener irsl, Motors importedMotors, ColorSensorArm csa) {
		this.irsl = irsl;
		this.motors = importedMotors;
		this.csa = csa;
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
				while(this.irsl.checkForTwoButtonCommands(0) != 0) {
					this.motors.driveBackward();
				}
				this.motors.stopDriveMotors();
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
				// toggle arm up/down
				armToggle();
			default:
		}
	}
	
	public void armToggle() {
		if(!this.armDown) {
			this.csa.colorSensorArmDown();
			this.armDown = true;
		} else {
			this.csa.colorSensorArmUp();
			this.armDown = false;
		}
	}
}
