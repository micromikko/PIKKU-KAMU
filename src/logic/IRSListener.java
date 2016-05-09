package logic;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

/**
 * 
 * @author micromikko
 *
 */
public class IRSListener extends Thread{
	private EV3IRSensor irSensor;
	private boolean isRunning;
	
	// variables to store commands from each IR channel
	private int remoteCommand0;
	private int remoteCommand1;
	private int remoteCommand2;
	private int remoteCommand3;
	
	public IRSListener(EV3IRSensor sensor) {
	this.irSensor = sensor;
	this.isRunning = true;
	}
	
	/**
	 * IRSListener listens to all IR channels and stores their values
	 */
	public void run() {
		while(this.isRunning) {
			listenChannel0();
			listenChannel1();
			listenChannel2();
			listenChannel3();			
		}
	}
	
	/**
	 * Listens to channel 0 (remote channel 1)
	 */
	public void listenChannel0() {
		checkForTwoButtonCommands(0);
	}

	/**
	 * Listens to channel 1 (remote channel 2)
	 */
	public void listenChannel1() {
		checkForTwoButtonCommands(1);
	}
	
	/**
	 * Listens to channel 2 (remote channel 3)
	 */
	public void listenChannel2() {
		checkForTwoButtonCommands(2);
	}
	
	/**
	 * Listens to channel 3 (remote channel 4)
	 */
	public void listenChannel3() {
		checkForTwoButtonCommands(3);
	}
	
	/**
	 * @return the remoteCommand0
	 */
	public int getRemoteCommand0() {
		return remoteCommand0;
	}

	/**
	 * @return the remoteCommand1
	 */
	public int getRemoteCommand1() {
		return remoteCommand1;
	}

	/**
	 * @return the remoteCommand2
	 */
	public int getRemoteCommand2() {
		return remoteCommand2;
	}

	/**
	 * @return the remoteCommand3
	 */
	public int getRemoteCommand3() {
		return remoteCommand3;
	}

	public void kill() {
		this.isRunning = false;
	}
	
	public int checkForTwoButtonCommands(int channel) {
		
//		int buttonStillDown;
		int oneButtonCommand = this.irSensor.getRemoteCommand(channel);
		
//		if(channel >= 0) {
			if(oneButtonCommand != 0) {
				Delay.msDelay(200);
				int twoButtonCommand = this.irSensor.getRemoteCommand(channel);
				
				if(twoButtonCommand != oneButtonCommand) {
					return twoButtonCommand;
				} else {
					return oneButtonCommand;
				}
			} else {
				return 0;
			}
//		} else {
//			if(oneButtonCommand != 0) {
//				Delay.msDelay(200);
//				int twoButtonCommand = this.irSensor.getRemoteCommand(channel);
//				
//				if(twoButtonCommand != oneButtonCommand) {
//					return twoButtonCommand;
//				} else {
//					while(true) {
//						buttonStillDown = this.irSensor.getRemoteCommand(0);
//						if(!(oneButtonCommand == buttonStillDown)) {
//							break;
//						}
//					}
//				}
//			}
//		}
		
//		return -1;
	}
}
