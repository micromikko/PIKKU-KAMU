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
	
	/**
	 * Constructor
	 * @param sensor EV3IRSensor Which sensor should the listener be attached to
	 */
	public IRSListener(EV3IRSensor sensor) {
	this.irSensor = sensor;
	this.isRunning = true;
	}
	
	/**
	 * IRSListener creates a thread that listens to all IR channels and stores their values
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

	/**
	 * Kill the thread
	 */
	public void kill() {
		this.isRunning = false;
	}
	
	/**
	 * Checks if two buttons are pressed at the same time with a 200ms leeway
	 * @param channel int An integerr that corresponds to the channel to be read
	 * @return int Returns an integer that corresponds to a certain button combination on the IR remote
	 */
	public int checkForTwoButtonCommands(int channel) {
		
		int oneButtonCommand = this.irSensor.getRemoteCommand(channel);
		
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
	}
}
