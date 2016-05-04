package test;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import logic.IRSListenerTest;

public class TestProper {

	public static void main(String[] args) {
		
		
		EV3IRSensor irs = new EV3IRSensor(SensorPort.S1);
		IRSListenerTest irsl = new IRSListenerTest(irs);
		
		LCD.drawString("Start", 4, 0);
		Delay.msDelay(700);
		LCD.clear();
		irsl.run();
		LCD.drawString("End", 4, 0);
		Delay.msDelay(1000);
	}

}
