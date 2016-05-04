package logic;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import logic.Motors;
import modes.ManualControl;
import support.Toolbox;
//import ui.UI;


public class IRSListenerTest extends Thread{
	
	private EV3IRSensor irSensor;
	private Motors motors;
	private ManualControl mc;
	
	private boolean isRunning = true;		// tracks if thread is still running
	private int irCommand0;					// int that stores the pressed button on the IR controller
	private int irCommand1;
	private int irCommand2;
//	private int irCommand3;
	
	public IRSListenerTest(EV3IRSensor sensor) {
	this.irSensor = sensor;
	this.motors = new Motors();
	this.mc = new ManualControl(this.motors);
	}
	
	public void run() {
		
		this.motors.motorSync(this.motors.getMotorLeft(), this.motors.getMotorRight());
		this.motors.setAcceleration(50);
		
		while(isRunning) {
			
			if(Button.ESCAPE.isDown()) {						// thread will be killed if ESC is pressed on EV3
				motors.closeMotors();							// close motors when thread is killed
				kill();
			}
			
			mc.manualControl(irCommand0, irSensor.getRemoteCommand(0));
			treasureHunter();
			treasureHunterTest();
			
		}	
	}
	
	
	public void treasureHunter() {
		
		irCommand1 = irSensor.getRemoteCommand(1);
		
		switch(irCommand1) {
		
		case 1:
			Toolbox.wheelRadiusIncrease();
			LCD.clear(2);
			LCD.drawString(Toolbox.getWHEEL_RADIUS(Toolbox.WHEEL_RADIUS), 0, 2);
			Delay.msDelay(300);
			break;
		case 2:
			Toolbox.wheelRadiusDecrease();
			LCD.clear(2);
			LCD.drawString(Toolbox.getWHEEL_RADIUS(Toolbox.WHEEL_RADIUS), 0, 2);
			Delay.msDelay(300);
			break;
		case 3:
			Toolbox.botRadiusIncrease();
			LCD.clear(3);
			LCD.drawString(Toolbox.getBOT_RADIUS(Toolbox.BOT_RADIUS), 0, 3);
			Delay.msDelay(300);
			break;
		case 4:
			Toolbox.botRadiusDecrease();
			LCD.clear(3);
			LCD.drawString(Toolbox.getBOT_RADIUS(Toolbox.BOT_RADIUS), 0, 3);
			Delay.msDelay(300);
			break;
		}
	}
	
	public void treasureHunterTest() {
		
		irCommand2 = irSensor.getRemoteCommand(2);
		boolean drivingForward = false;
		boolean drivingBackward = false;
		boolean turningLeft = false;
		boolean turningRight = false;
		
		switch(irCommand2) {
				
				case 1:
					if(!drivingForward) {
						drivingForward = true;
						motors.driveForward(10000);
					} else {
						drivingForward = false;
						motors.stopMotors();
					}
					Delay.msDelay(300);
					break;
				case 2:
					if(!turningLeft) {
						turningLeft = true;
						motors.turnLeft(90);
					} else {
						turningLeft = false;
						motors.stopMotors();
					}
					Delay.msDelay(300);
					break;
				case 3:
					if(!drivingBackward) {
						drivingBackward = true;
						motors.driveBackward(10000);
					} else {
						drivingBackward = false;
						motors.stopMotors();
					}
					Delay.msDelay(300);
					break;
				case 4:
					if(!turningRight) {
						turningRight = true;
						motors.turnRight(90);
					} else {
						turningRight = false;
						motors.stopMotors();
					}
					Delay.msDelay(300);
					break;
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



/*

public void manualControl() {
	
	irCommand0 = irSensor.getRemoteCommand(0);			// stores the pressed IR controller button to irCommand (channel 4)
	
	switch(irCommand0) {
	case 1:
		// both backward
		motors.setAcceleration(10000000);
		while(irSensor.getRemoteCommand(0) != 0) {		// Both motors backward while button is pressed
			motors.driveBackward();
//			UI.bothBackward();							// prints issued command to screen
		}
		motors.stopMotors();							// stop all motors. same principle applies to all four controller buttons
		break;
	case 2:
		// left, forward
		motors.setAcceleration(10000000);
		while (irSensor.getRemoteCommand(0) != 0) {
			motors.turnRight();
//			UI.leftForward();
		}
		motors.stopMotors();
		break;
	case 3:
		// both forward
		motors.setAcceleration(10000000);
		while (irSensor.getRemoteCommand(0) != 0) {
			motors.driveForward();
//			UI.bothForward();
		}
		motors.stopMotors();
		break;
	case 4:
		// right, forward
		motors.setAcceleration(10000000);
		while (irSensor.getRemoteCommand(0) != 0) {
			motors.turnLeft();
//			UI.rightForward();
		}
		motors.stopMotors();
		break;
	default:											// if no button is pressed the loop will start again
		
	}
}
*/