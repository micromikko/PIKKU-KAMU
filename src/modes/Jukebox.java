package modes;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import logic.IRSListener;
import logic.MusicController;

public class Jukebox {
	private IRSListener irsl;
	private MusicController jukebox;
	
	public Jukebox(MusicController importedJukebox, IRSListener irsl){
		this.jukebox = importedJukebox;
		this.irsl = irsl;
	}
		
	public void jukebox(){
		while(!Button.ESCAPE.isDown()){
			int i = 0;
			int remoteCommand = this.irsl.checkForTwoButtonCommands(3);
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
						remoteCommand = this.irsl.checkForTwoButtonCommands(3);
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
