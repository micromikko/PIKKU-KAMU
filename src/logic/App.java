package logic;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import modes.ManualControl;

public class App {
	
	// lower level classes
	
	// ir sensor and listener
	private EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
	private IRSListener irsl = new IRSListener(irSensor);
	
	// drive motors and arm motor
	private Motors motors = new Motors();

	// modes
	private ManualControl mc = new ManualControl(irsl, motors);
//	private TreasureHunter th = new TreasureHunter();
//	private RobotDog rd = new RobotDog();
//	private Jukebox jb = new Jukebox();
	
	
	private boolean isRunning = true;		// tracks if thread is still running

	public void run() {
		
		LCD.drawString("jaska", 4, 3);
		this.irsl.start();
		this.motors.motorSync(this.motors.getMotorLeft(), this.motors.getMotorRight());
		
		while(this.isRunning) {
			
			if(Button.ESCAPE.isDown()) {
				this.motors.closeDriveMotors();
				kill();
			}
			
			mc.manualControl();
		}
		
		LCD.clear();
		LCD.drawString("seon moro", 2, 3);
		Delay.msDelay(500);
	}
	
	/**
	 * Kills the thread listening to IR controller traffic
	 */
	public void kill() {
		this.isRunning = false;
	}
}
