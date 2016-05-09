package test;

import lejos.hardware.sensor.*;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import logic.Motors;
import logic.IRSListener;
import logic.ColorChecker;

public class ArmTest {
	
	public static void main(String[] args) {
		
		Motors motors = new Motors();
		ColorChecker colorChecker = new ColorChecker(SensorPort.S4);
		motors.setColorSensorArmSpeed(100);
		
		
		motors.colorSensorArmDown();
		LCD.drawString("" + motors.getColorSensorArmPosition(), 1, 1);
		Delay.msDelay(3000);
		LCD.clear();
		motors.colorSensorArmUp();
		LCD.drawString("" + motors.getColorSensorArmPosition(), 1, 1);
		Delay.msDelay(3000);
		LCD.clear();
		motors.colorSensorArmDown();
		
		Delay.msDelay(3000);
		colorChecker.start();
		LCD.clear();
		
		while(!Button.ESCAPE.isDown()) {
			LCD.clear();
			LCD.drawString("" + colorChecker.getCurrentColor(), 1, 1);
			Delay.msDelay(300);
			
		}

		
	
	}
	
	

}
