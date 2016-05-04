package modes;

import lejos.hardware.sensor.EV3IRSensor;
import logic.Motors;

public class ManualControl {
	
	private Motors motors;
	private EV3IRSensor irSensor;
	
	public ManualControl(Motors importedMotors, EV3IRSensor irSensor) {
		this.motors = importedMotors;
		this.irSensor = irSensor;
	}
	
	public void manualControl() {
		
		int remoteCommand = irSensor.getRemoteCommand(0);
		
		switch(remoteCommand) {
			case 1:
				// both forward
				this.motors.setDriveAcceleration(10000000);
				while (irSensor.getRemoteCommand(0) != 0) {
					this.motors.driveForward();
				}
				this.motors.stopMotors();
				break;
			case 2:
				// left, forward
				this.motors.setDriveAcceleration(10000000);
				while (irSensor.getRemoteCommand(0) != 0) {
					this.motors.turnRight();
				}
				this.motors.stopMotors();
				break;
			case 3:
				// both backward
				this.motors.setDriveAcceleration(10000000);
				while(irSensor.getRemoteCommand(0) != 0) {		// Both motors backward while button is pressed
					this.motors.driveBackward();
				}
				this.motors.stopMotors();							// stop all motors. same principle applies to all four controller buttons
				break;
			case 4:
				// right, forward
				this.motors.setDriveAcceleration(10000000);
				while (irSensor.getRemoteCommand(0) != 0) {
					this.motors.turnLeft();
				}
				this.motors.stopMotors();
				break;
			default:											// if no button is pressed the loop will start again
		}
	}
	
}