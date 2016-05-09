package test;

import lejos.hardware.sensor.*;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.lcd.*;
import logic.Motors;
import logic.IRSListener;
import logic.ColorChecker;


public class ZigZagSearchTest {
	
	Motors motors;
	EV3IRSensor irsensor;
	IRSListener listener;
	ColorChecker colorChecker;
	
	
	public ZigZagSearchTest() {
		this.irsensor = new EV3IRSensor(SensorPort.S1);
		this.motors = new Motors();
		this.listener = new IRSListener(this.motors, this.irsensor);
		this.colorChecker = new ColorChecker(SensorPort.S3);
		
	}
	
	public void doSearch() {
		motors.colorSensorArmDown();
		motors.motorSync(motors.getMotorLeft(), motors.getMotorRight());
		motors.setDriveAcceleration(50);
		
		
		int collideCounter = 0;
		boolean foundTreasure = false;
		
		motors.driveForward();
		
		while(!foundTreasure) {
			
			if(colorChecker.getCurrentColor() == 1) {
				//stop, we hit a border of the search area!
				motors.stopMotors();
				Delay.msDelay(500);
				
				if(collideCounter % 2 == 0) {
					//check if we have to make a left or right turn
					motors.turnRight(90);
					Delay.msDelay(500);
					motors.driveForward(10); //FIXME: adjust this distance according to the width of the device
					Delay.msDelay(500);
					motors.turnRight(90);
				} else {
					motors.turnLeft(90);
					Delay.msDelay(500);
					motors.driveForward(10); //FIXME: adjust this distance according to the width of the device
					Delay.msDelay(500);
					motors.turnLeft(90);
				}
				collideCounter++;
				Delay.msDelay(500);
				motors.driveForward(); //we are facing the right way, let's continue the search by
						       //driving forward
			}
			
			if (colorChecker.getCurrentColor() == 5) {
				motors.stopMotors();
				LCD.drawString("Found the treasure!", 1, 1);
				Delay.msDelay(3000);
				foundTreasure = true;
				
			}
		}
	}
}
