package test;
import logic.Motors;
import logic.ColorChecker;
import lejos.hardware.port.SensorPort;

public class MoveStraightTest {

	public static void main(String[] args) {
		
		Motors motors = new Motors();
		ColorChecker checker = new ColorChecker(SensorPort.S3);
		
		motors.driveForward();
		checker.run();
		
		int lastCheckedColor = 1000;
		int currentColor;
		
		while(true) {
			currentColor = checker.getCurrentColor();
			if (currentColor == 1 && lastCheckedColor != currentColor) {
				
				motors.stopMotors();
				motors.turnLeft(180);
				motors.driveForward();
				lastCheckedColor = currentColor;
				
			}
		}
	}
	
}
