package logic;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import modes.Jukebox;
import modes.ManualControl;
import modes.TreasureHunter;

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
	private ColorChecker cs = new ColorChecker(SensorPort.S4);
	private MusicController muCo = new MusicController();
	
	private ManualControl mc = new ManualControl(this.irsl, this.motors, this.csa);
	private TreasureHunter th = new TreasureHunter(this.irsl, this.motors, this.csa, this.cs);
//	private RobotDog rd = new RobotDog();
	private Jukebox jb = new Jukebox(this.muCo, this.irsl);
	
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
			this.th.treasureHunter();
//			this.rd.robotDog();
			this.jb.jukebox();
		}
		
		LCD.clear();
		LCD.drawString("Goodbye", 3, 3);
		Delay.msDelay(2000);
	}
	
	/**
	 * Kills the main loop of the program
	 */
	public void kill() {
		this.isRunning = false;
	}
}
