package logic;

import lejos.hardware.port.Port;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;


public class ColorChecker extends Thread {
	/*T‰ss‰ luodaan instanssi. joka ohjaa v‰risensoria p‰‰ohjelmaloopin
	 * puolesta.
	 */
	
	private EV3ColorSensor mySensor;
	private int myColor;
	private boolean isRunning;
	
	
	
	
	
	
	
	public void run() {
		this.isRunning = true;
		
		while (isRunning) {
			
			this.myColor = this.mySensor.getColorID();
			
			if (Button.DOWN.isDown()) {
				this.isRunning = false;
			}
		}
	}
	
	
	
	public ColorChecker(Port sensorPort) {
		//konstruktori saa parametrina portin, johon sensori on kiinnitetty
		
		this.mySensor = new EV3ColorSensor(sensorPort);
		this.isRunning = false;
	}
	
	
	public int getCurrentColor() {
		//palauttaa kokonaislukuna v‰rin, jota sensori lukee t‰l‰ hetkell‰
		return this.myColor;
	}

}