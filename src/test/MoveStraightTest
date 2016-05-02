package test;
import logic.Motors;
import logic.ColorSensorController;
import lejos.hardware.port.SensorPort;

public class MoveStraightTest {

	public static void main(String[] args) {
		
		Motors motors = new Motors();
		ColorSensorController controller = new ColorSensorController(SensorPort.S3);
		
		motors.driveForward();
		
		while(true) {
			if (controller.getCurrentColor() == 1) {
				motors.stopMotors();
				break;
			}
		}
	}
	
}
