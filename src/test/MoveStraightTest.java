package test;
import logic.Motors;
import lejos.hardware.Button;
import logic.ColorChecker;
import lejos.hardware.port.SensorPort;

public class MoveStraightTest {

	public static void main(String[] args) {
		
		Motors motors = new Motors();
		
		while(!Button.ESCAPE.isDown()) {
		
			motors.driveForward(1000);
			motors.turnLeft(180);
		
		}
		

		
		
	}
	
}
