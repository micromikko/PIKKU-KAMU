package modes;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import logic.ColorSensorArm;
import logic.ColorChecker;
import logic.IRSListener;
import logic.Motors;
import support.Toolbox;

public class TreasureHunter {
	
	private IRSListener irsl;
	private Motors motors;
	private ColorSensorArm csa;
	private ColorChecker cc;
	
	private int searchAlgorithm;
	
	/**
	 * Constructor
	 * @param irsl IRSListener
	 * @param motors Motors
	 * @param csa ColorSensorArm
	 * @param cs ColorChecker
	 */
	public TreasureHunter(IRSListener irsl, Motors motors, ColorSensorArm csa, ColorChecker cc) {
		this.irsl = irsl;
		this.motors = motors;
		this.csa = csa;
		this.cc = cc;
		
		this.searchAlgorithm = 1;
	}
	
	/**
	 * Governs the robot's actions when using the IR remote in channel 1
	 */
	public void treasureHunter() {
		
		int remoteCommand = this.irsl.checkForTwoButtonCommands(1);
		
		switch(remoteCommand) {
			case 1:
				startPattern();
				break;
			case 2:
				previousPattern();
				break;
			case 3:
				// STOPS AND RESETS ALL SEARCHING
				break;
			case 4:
				nextPattern();
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
			default:
		}
	}
	
	/**
	 * Next pattern (set to work with two patterns atm)
	 */
	public void nextPattern() {
		if(this.searchAlgorithm == 1) {
			this.searchAlgorithm++;
		} else {
			this.searchAlgorithm = 1;
		}
	}
	
	/**
	 * Previous pattern (set to work with two patterns atm)
	 */
	public void previousPattern() {
		if(this.searchAlgorithm == 2) {
			this.searchAlgorithm--;
		} else {
			this.searchAlgorithm = 2;
		}
	}
	
	/**
	 * Start selected search pattern
	 */
	public void startPattern() {
		
		switch(this.searchAlgorithm) {
		case 1:
			spiralPattern();
			break;
		case 2:
			zigZagPattern();
		}
	}
	
	/**
	 * Spiral search pattern. Starts from "lower left" corner of a rectangle. Combs an marked area for a red X marker.
	 */
	public void spiralPattern() {
		
		int spiralCounter = 0;
		int collideCounter = 0;
		
		this.motors.setDriveSpeed(500);
		this.csa.colorSensorArmDown();
		
		while(!treasureFound()) {
			
			if(hitWall()) {
				collideCounter = advanceCollideCounter(collideCounter);
				spiralCounter = advanceSpiralCounter(collideCounter, spiralCounter);
				
				stopAndBoink();
				
				backTurnAndGo(spiralCounter);
			}
		}
		
		party();		
	}
	
	/**
	 * Stops the bot and raises the ColorSensorArm. Prints a victory message to the screen.
	 */
	public void party() {
		
		this.motors.stopDriveMotors();
		this.csa.colorSensorArmUp();
		
		LCD.drawString("Treasure found! PARTY!", 1, 1);
		
	}
	
	/**
	 * Increases the collideCounter. 
	 * @param collideCounter int the amount of "wall" collisions
	 * @return int If collideCounter is 4, change it to 0.
	 */
	public int advanceCollideCounter(int collideCounter) {
		if(collideCounter < 4) {
			return (collideCounter++);
		} else {
			return 0;
		}
	}
	
	/**
	 * Increases the collideCounter. 
	 * @param collideCounter int the amount of "wall" collisions
	 * @param spiralCounter int spiralCounter tracks the current "layer" of the spiral.
	 * @return int If collideCounter reaches 4, return spiralCounter + 1
	 */
	public int advanceSpiralCounter(int collideCounter, int spiralCounter) {
		if(collideCounter == 4) {
			return (spiralCounter++);
		} else {
			return spiralCounter;
		}
	}
	
	/**
	 * Checks to see if the treasure (red coloured tape) has been found
	 * @return boolean True if treasure found, else false
	 */
	public boolean treasureFound() {
		if(this.cc.getCurrentColor() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks to see if the bot has encountered a "wall" (black coloured tape marking the edges of the search area)
	 * @return boolean Returns true if the bot detects black, else false
	 */
	public boolean hitWall() {
		if(this.cc.getCurrentColor() == 7) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Stops, prints "BOINK", waits for 500ms, clears screen
	 */
	public void stopAndBoink() {
		this.motors.stopDriveMotors();
		LCD.drawString("BOINK", 1, 1);
		Delay.msDelay(500);
		LCD.clear();
	}
	
	/**
	 * The bot reverses spiralCount * 0.1m, turns 90 degrees to the right and starts forward
	 * @param spiralCount int The "layer" of the spiral.
	 */
	public void backTurnAndGo(int spiralCount) {
		for(int i = 0; i < spiralCount; i++) {
			this.motors.driveBackward(1000);
		}
		this.motors.turnRight(90);
		this.motors.driveForward();
	}
	
	/**
	 * Combs the search area in a zig-zag pattern until the red coloured tape has been found.
	 */
	public void zigZagPattern() {
		csa.setColorSensorArmSpeed(50); 	//limit arm speed to prevent trouble
		csa.colorSensorArmDown();			//lower arm to reading position
		LCD.drawString("" + this.cc.getCurrentColor(), 1, 1);
		Delay.msDelay(3000);
		LCD.clear();
		
		motors.setDriveAcceleration(50);
		
		
		int collideCounter = 0; 			//keep track of collisions to search area borders
		boolean foundTreasure = false;
		
		motors.driveForward();
		
		while(!foundTreasure) {				//search until treasure found
			
			if(this.cc.getCurrentColor() == 7) { 	//if we are on a black area
				//stop, we hit a border of the search area!
				motors.stopDriveMotors();
				LCD.drawString("BOINK",1,1);
				Delay.msDelay(2000);
				
				if(collideCounter % 2 == 0) {
					//check if we have to make a right turn...
					motors.turnRight(90);
					Delay.msDelay(500);
					motors.driveForward(Toolbox.BOT_WIDTH/2); // move the bot laterally by half of its width
					Delay.msDelay(500);
					motors.turnRight(90);
				} else {
					//...or a left turn
					motors.turnLeft(90);
					Delay.msDelay(500);
					motors.driveForward(Toolbox.BOT_WIDTH/2);
					Delay.msDelay(500);
					motors.turnLeft(90);
				}
				collideCounter++;							
				Delay.msDelay(500);
				motors.driveForward(); //we are facing the right way, let's continue the search by
						       		   //driving forward
			}
			
			if (this.cc.getCurrentColor() == 0) {
				//we are on a red area, which means we found the treasure!
				motors.stopDriveMotors();
				LCD.drawString("Found the treasure!", 1, 1); //declare our findings to the world
				
				Delay.msDelay(3000);
				foundTreasure = true;
				
			}
		}
	}
}


/*
1 TOP-LEFT
2 BOTTOM-LEFT
3 TOP-RIGHT
4 BOTTOM-RIGHT
5 TOP-LEFT + TOP-RIGHT
6 TOP-LEFT + BOTTOM-RIGHT
7 BOTTOM-LEFT + TOP-RIGHT
8 BOTTOM-LEFT + BOTTOM-RIGHT
9 CENTRE/BEACON
10 BOTTOM-LEFT + TOP-LEFT
11 TOP-RIGHT + BOTTOM-RIGHT
*/
