package logic;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3IRSensor;
import logic.Motors;
//import ui.UI;


public class IRSListenerTest extends Thread{
	private EV3IRSensor irSensor;
	private Motors motors;
	
	private boolean isRunning = true;		// tracks if thread is still running
	int irCommand;							// int that stores the pressed button on the IR controller

	public IRSListenerTest(EV3IRSensor sensor) {
	this.irSensor = sensor;
	this.motors = new Motors();
	}
	
	public void run() {
		
		this.motors.motorSync(this.motors.getMotorLeft(), this.motors.getMotorRight());
		
		while(isRunning) {
			
			if(Button.ESCAPE.isDown()) {						// thread will be killed if ESC is pressed on EV3
				motors.closeMotors();							// close motors when thread is killed
				kill();
			}
			
			irCommand = irSensor.getRemoteCommand(3);			// stores the pressed IR controller button to irCommand (channel 4)
			
			switch(irCommand) {
			case 1:
				// both backward
				while(irSensor.getRemoteCommand(3) != 0) {		// Both motors backward while button is pressed
					motors.driveBackward();
//					UI.bothBackward();							// prints issued command to screen
				}
				motors.stopMotors();							// stop all motors. same principle applies to all four controller buttons
				break;
			case 2:
				// left, forward
				while (irSensor.getRemoteCommand(3) != 0) {
					motors.turnRight();
//					UI.leftForward();
				}
				motors.stopMotors();
				break;
			case 3:
				// both forward
				while (irSensor.getRemoteCommand(3) != 0) {
					motors.driveForward();
//					UI.bothForward();
				}
				motors.stopMotors();
				break;
			case 4:
				// right, forward
				while (irSensor.getRemoteCommand(3) != 0) {
					motors.turnLeft();
//					UI.rightForward();
				}
				motors.stopMotors();
				break;
			default:											// if no button is pressed the loop will start again
				continue;
				
			}
		}
	}
	
	/**
	 * Kills the thread listening to IR controller traffic
	 */
	public void kill() {
		this.isRunning = false;
//		LCD.clear();
//		LCD.drawString("Listener killed.", 1, 3);
	}
}




//1 TOP-LEFT
//2 BOTTOM-LEFT
//3 TOP-RIGHT
//4 BOTTOM-RIGHT
//5 TOP-LEFT + TOP-RIGHT
//6 TOP-LEFT + BOTTOM-RIGHT
//7 BOTTOM-LEFT + TOP-RIGHT
//8 BOTTOM-LEFT + BOTTOM-RIGHT
//9 CENTRE/BEACON
//10 BOTTOM-LEFT + TOP-LEFT
//11 TOP-RIGHT + BOTTOM-RIGHT