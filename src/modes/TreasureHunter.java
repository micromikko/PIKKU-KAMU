package modes;

import logic.ColorSensorArm;
import logic.IRSListener;
import logic.Motors;

public class TreasureHunter {
	
	private IRSListener irsl;
	private Motors motors;
	private ColorSensorArm csa;
	
	public TreasureHunter(IRSListener irsl, Motors motors, ColorSensorArm csa) {
		this.irsl = irsl;
		this.motors = motors;
		this.csa = csa;
	}
	
	public void manualControl() {
		
		int remoteCommand = this.irsl.checkForTwoButtonCommands(0);
		
		switch(remoteCommand) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
			
				break;
			case 8:
				
			default:
		}
	}
}
