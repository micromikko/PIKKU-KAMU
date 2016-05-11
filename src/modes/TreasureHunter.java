package modes;

import logic.ColorSensorArm;
import logic.IRSListener;
import logic.Motors;

public class TreasureHunter {
	
	private IRSListener irsl;
	private Motors motors;
	private ColorSensorArm csa;
	
	private int searchAlgorithm;
	
	public TreasureHunter(IRSListener irsl, Motors motors, ColorSensorArm csa) {
		this.irsl = irsl;
		this.motors = motors;
		this.csa = csa;
		
		this.searchAlgorithm = 1;
	}
	
	public void treasureHunter() {
		
		int remoteCommand = this.irsl.checkForTwoButtonCommands(0);
		
		switch(remoteCommand) {
			case 1:
				// START SELECTED SEARCH PATTERN
				break;
			case 2:
				previousAlgorithm();
				break;
			case 3:
				// STOPS AND RESETS ALL SEARCHING
				break;
			case 4:
				nextAlgorithm();
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
			default:
		}
	}
	
	public void nextAlgorithm() {
		this.searchAlgorithm++;
	}
	
	public void previousAlgorithm() {
		this.searchAlgorithm--;
	}
	
	public int getSearchAlgorithm() {
		return this.searchAlgorithm;
	}
	
	public void startAlgorithm() {
		
		switch(this.searchAlgorithm) {
		case 1:
			spiralPattern();
			break;
		}
	}
	
	public void spiralPattern() {
		
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
