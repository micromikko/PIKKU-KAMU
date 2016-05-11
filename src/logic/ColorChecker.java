package logic;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;


public class ColorChecker extends Thread {
	
	private EV3ColorSensor mySensor;
	private int myColor;

	/**
	 * Contructor
	 * @param sensorPort Port The sensor port to which the ColorSensor is connected to
	 */
	public ColorChecker(Port sensorPort) {
		this.mySensor = new EV3ColorSensor(sensorPort);
	}
	
	/**
	 * Creates a thread that continuously reads the current color
	 */
	public void run() {		
		while (true) {
			this.myColor = this.mySensor.getColorID();
		}
	}
	
	/**
	 * Gets the current color
	 * @return int Returns the current color as an integer
	 */
	public int getCurrentColor() {
		return this.myColor;
	}
}
