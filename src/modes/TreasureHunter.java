package modes;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.*;
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
	private ColorChecker cs;
	
	private int searchAlgorithm;
	
	public TreasureHunter(IRSListener irsl, Motors motors, ColorSensorArm csa, ColorChecker cs) {
		this.irsl = irsl;
		this.motors = motors;
		this.csa = csa;
		this.cs = cs;
		
		this.searchAlgorithm = 1;
	}
	
	public void treasureHunter() {
		
		int remoteCommand = this.irsl.checkForTwoButtonCommands(0);
		
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
	
	public void nextPattern() {
		if(this.searchAlgorithm == 1) {
			this.searchAlgorithm++;
		} else {
			this.searchAlgorithm = 1;
		}
	}
	
	public void previousPattern() {
		if(this.searchAlgorithm == 2) {
			this.searchAlgorithm--;
		} else {
			this.searchAlgorithm = 2;
		}
	}
	
	public void startPattern() {
		
		switch(this.searchAlgorithm) {
		case 1:
			spiralPattern();
			break;
		case 2:
			zigZagPattern();
		}
	}
	
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
	
	public void party() {
		
		this.motors.stopDriveMotors();
		this.csa.colorSensorArmUp();
		
		LCD.drawString("Treasure found! PARTY!", 1, 1);
		
	}
	
	public int advanceCollideCounter(int collideCounter) {
		if(collideCounter < 4) {
			return (collideCounter++);
		} else {
			return 0;
		}
	}
	
	public int advanceSpiralCounter(int collideCounter, int spiralCounter) {
		if(collideCounter == 4) {
			return (spiralCounter++);
		} else {
			return spiralCounter;
		}
	}
	
	public boolean treasureFound() {
		if(this.cs.getCurrentColor() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hitWall() {
		if(this.cs.getCurrentColor() == 7) {
			return true;
		} else {
			return false;
		}
	}
	
	public void stopAndBoink() {
		this.motors.stopDriveMotors();
		LCD.drawString("BOINK", 1, 1);
		Delay.msDelay(500);
		LCD.clear();
	}
	
	public void backTurnAndGo(int spiralCount) {
		for(int i = 0; i < spiralCount; i++) {
			this.motors.driveBackward(1000);
		}
		this.motors.turnRight(90);
		this.motors.driveForward();
	}
	
	
	public void zigZagPattern() {
		csa.setColorSensorArmSpeed(50); 	//limit arm speed to prevent trouble
		csa.colorSensorArmDown();			//lower arm to reading position
		LCD.drawString("" + this.cs.getCurrentColor(), 1, 1);
		Delay.msDelay(3000);
		LCD.clear();
		
		motors.setDriveAcceleration(50);
		
		
		int collideCounter = 0; 			//keep track of collisions to search area borders
		boolean foundTreasure = false;
		
		motors.driveForward();
		
		while(!foundTreasure) {				//search until treasure found
			
			if(this.cs.getCurrentColor() == 7) { 	//if we are on a black area
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
			
			if (this.cs.getCurrentColor() == 0) {
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
