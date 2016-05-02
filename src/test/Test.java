package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import logic.IRSListenerTest;

public class Test {

	public static void main(String[] args) {
		
		EV3IRSensor irs = new EV3IRSensor(SensorPort.S1);
		IRSListenerTest irsl = new IRSListenerTest(irs);
		
		while(!Button.ESCAPE.isDown()) {
			irsl.run();
		}
		LCD.drawString("Goodbye", 4, 3);
		Delay.msDelay(1000);
	}

}
