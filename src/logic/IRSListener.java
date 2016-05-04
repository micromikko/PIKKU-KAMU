package logic;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import logic.Motors;
import support.Toolbox;
//import ui.UI;


public class IRSListener extends Thread{
	private EV3IRSensor irSensor;
	private Motors motors;
	
	private boolean isRunning = true;		// tracks if thread is still running
	private int irCommand0;							// int that stores the pressed button on the IR controller
	private int irCommand1;
	private int irCommand2;
	private int irCommand3;
	
	public IRSListener(Motors motors, EV3IRSensor sensor) {
	this.irSensor = sensor;
	this.motors = motors;
	}
	
	public void run() {
		
		this.motors.motorSync(this.motors.getMotorLeft(), this.motors.getMotorRight());
		this.motors.setDriveAcceleration(50);
		
		while(isRunning) {
			
			if(Button.ESCAPE.isDown()) {						// thread will be killed if ESC is pressed on EV3
				motors.closeMotors();							// close motors when thread is killed
				kill();
			}
			
			
		}	
	}

	
	/**
	 * Kills the thread listening to IR controller traffic
	 */
	public void kill() {
		this.isRunning = false;
//		LCD.clear();
//		LCD.drawString("Listener killed.", 1, 3);
	}
}




//1 TOP-LEFT
//2 BOTTOM-LEFT
//3 TOP-RIGHT
//4 BOTTOM-RIGHT
//5 TOP-LEFT + TOP-RIGHT
//6 TOP-LEFT + BOTTOM-RIGHT
//7 BOTTOM-LEFT + TOP-RIGHT
//8 BOTTOM-LEFT + BOTTOM-RIGHT
//9 CENTRE/BEACON
//10 BOTTOM-LEFT + TOP-LEFT
//11 TOP-RIGHT + BOTTOM-RIGHT