package music;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

public class Jukebox {
	private EV3IRSensor irSensor;
	private MusicController jukebox;
	
	public Jukebox(MusicController importedJukebox, EV3IRSensor irSensor){
		this.jukebox = importedJukebox;
		this.irSensor = irSensor;
		musicControl();
	}
		
	public void musicControl(){
		while(!Button.ESCAPE.isDown()){
			LCD.clear();
			int remoteCommand = irSensor.getRemoteCommand(3);
			switch(remoteCommand) {
				case 1:
					jukebox.setVolume(5);
					LCD.drawInt(jukebox.getVolume(), 0, 0);
					break;
				case 2:
					jukebox.setVolume(-5);
					LCD.drawInt(jukebox.getVolume(), 0, 0);
					break;
				case 3:
					jukebox.searchPlayList();
					LCD.drawInt(3, 0, 0);
					break;
			/*case 4:
				jukebox.removeFromPlayList();*/
				case 4:
					jukebox.jaska();
					//LCD.drawInt(4, 0, 0);
					break;
				case 5:
					jukebox.addToPlayList();
				default:
					continue;
			}
			Delay.msDelay(500);
		
		}
	}
}
