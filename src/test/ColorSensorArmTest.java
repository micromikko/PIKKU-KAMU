package test;

import lejos.hardware.sensor.*;
import lejos.hardware.port.SensorPort;
import logic.Motors;
import logic.IRSListener;


public class ColorSensorArmTest {
	
	public static void main(String[] args) {
		EV3IRSensor irsensor = new EV3IRSensor(SensorPort.S1);
		Motors motors = new Motors();
		IRSListener listener = new IRSListener(motors, irsensor);
		
		
		
		
		
	}
	
	public void armTest() {
		return;
	}

}
