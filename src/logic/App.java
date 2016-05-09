package logic;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import modes.ManualControl;

/**
 * 
 * @author micromikko
 *
 */
public class App {
	
	private EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S1);
	private IRSListener irsl = new IRSListener(irSensor);
	
	private Motors motors = new Motors();
	private ColorSensorArm csa = new ColorSensorArm();
	
	private ManualControl mc = new ManualControl(this.irsl, this.motors, this.csa);
//	private TreasureHunter th = new TreasureHunter();
//	private RobotDog rd = new RobotDog();
//	private Jukebox jb = new Jukebox();
	
	/**
	 * Tracks if main loop of the program is running
	 */
	private boolean isRunning = true;

	/**
	 * Main loop of the program
	 */
	public void run() {
		
		LCD.drawString("jaska", 4, 3);
		
		this.irsl.start();
		this.motors.motorSync(this.motors.getMotorLeft(), this.motors.getMotorRight());
		
		while(this.isRunning) {
			
			if(Button.ESCAPE.isDown()) {
				this.motors.closeDriveMotors();
				kill();
			}
			
			this.mc.manualControl();
//			this.th.treasureHunter();
//			this.rd.robotDog();
//			this.jb.jukebox();
		}
		
		LCD.clear();
		LCD.drawString("seon moro", 2, 3);
		Delay.msDelay(500);
	}
	
	/**
	 * Kills the main loop of the program
	 */
	public void kill() {
		this.isRunning = false;
	}
}
