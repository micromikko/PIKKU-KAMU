package modes;

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
			int i = 0;
			int remoteCommand = irSensor.getRemoteCommand(3);
			switch(remoteCommand) {
				case 1:
					LCD.clear();
					jukebox.setVolume(5);
					LCD.drawInt(jukebox.getVolume(), 0, 0);
					break;
				case 2:
					LCD.clear();
					jukebox.setVolume(-5);
					LCD.drawInt(jukebox.getVolume(), 0, 0);
					break;
				case 3:
					LCD.clear();
					jukebox.searchPlayList();
					break;
				case 4:
					while(true)
					{
						remoteCommand = irSensor.getRemoteCommand(3);
						if(remoteCommand==1){
							LCD.clear();
							i++;
							LCD.drawInt(i, 0, 0);
						}
						else if(remoteCommand==2){
							LCD.clear();
							i--;
							LCD.drawInt(i, 0, 0);
						}
						else if(remoteCommand==3){
							break;
						}
						Delay.msDelay(500);
						
					}
					jukebox.removeFromPlayList(i);
					break;
				case 5:
					LCD.clear();
					jukebox.jaska();
					//LCD.drawInt(4, 0, 0);
					break;
				case 8:
					LCD.clear();
					jukebox.addToPlayList();
				default:
					continue;
			}
			Delay.msDelay(1000);
		
		}
		LCD.drawString("Exiting Jukebox", 0, 0);
		Delay.msDelay(1000);
	}
}
