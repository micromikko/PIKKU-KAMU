package test;
import logic.Motors;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import logic.ColorChecker;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class satan {

	public static void main(String[] args) {
		
		Motors motors = new Motors();
		boolean testRunning = false;
		
		LCD.drawString("jaska jaska", 3, 3);
		Delay.msDelay(1000);
		LCD.clear();
		
		motors.setAcceleration(50);
		
		while(!Button.ESCAPE.isDown()) {
			
			if(Button.ENTER.isDown()) {
				if(testRunning == false) {
					LCD.clear();
					LCD.drawString("testRunning = true",0, 3);
					testRunning = true;
				} else {
					LCD.clear();
					LCD.drawString("testRunning = false",0, 3);
					testRunning = false;
				}
			}
			
			if(testRunning) {
				while(!Button.ESCAPE.isDown()) {
					
					motors.turnRight(360);
					motors.stopMotors();
					Delay.msDelay(500);
					
					motors.driveForward(1000);
					motors.stopMotors();
					Delay.msDelay(500);
					
					motors.turnLeft(360);
					motors.stopMotors();
					Delay.msDelay(500);
					
					motors.driveBackward(1000);
					motors.stopMotors();
					Delay.msDelay(500);
				}
			}
			
		}
//		motors.turnLeft(90);
//		motors.driveForward(1000);
//		motors.turnRight(90);
		
		LCD.drawString("paska paska", 3, 3);
		Delay.msDelay(1000);
		
		motors.closeMotors();
		
	}
	
}
