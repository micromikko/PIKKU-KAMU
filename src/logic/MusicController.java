package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class MusicController extends Thread {
	private final File soundFile1 = new File("Space_Alert3.wav");
	private final File soundFile2 = new File("laaseri.wav");
	private final File soundFile3 = new File("Space_Alert2.wav");
	private final File soundFile4 = new File("Space_Alert1.wav");
	private int volume;
//	private boolean playSound;
	private ArrayList<File> playList = new ArrayList<>();
	Map<File, String> map = new HashMap<File, String>();
	
	/**
	 * Constructor
	 */
	public MusicController () {
		volume = 50;
//		playSound = true;
		/**
		 * Give a name for each soundFile
		 */
		map.put(soundFile1, "Space Alert 3");
		map.put(soundFile2, "Laaseri");
		map.put(soundFile3, "Space Alert 2");
		map.put(soundFile4, "Space Alert 1");
	}
	
	/**
	 * Go through the playList and play each song
	 * Shows the name of the song on the LCD
	 */
	public void musicPlay() {
		for (File J : playList){
			LCD.clear();
			LCD.drawString(map.get(J), 0, 0);
			Sound.playSample(J, volume);
			Delay.msDelay(5000);
		}
	}
	
	/**
	 * Get current volume
	 * @return int Returns the current volume (0-100)
	 */
	public int getVolume(){
		return volume;
	}
	
	/**
	 * Set device volume
	 * @param a int increase volume by *a* (int 0-100)
	 * @return int Returns total volume
	 */
	public int setVolume(int a){
		this.volume = volume + a;
		return volume;
	}
	
//	public void killSound(){
//		playSound = false;
//	}
	
	/**
	 * Clears playlist and adds 4 sound files to said list
	 */
	public void addToPlayList(){
		playList.clear();
		playList.add(soundFile1);
		playList.add(soundFile2);
		playList.add(soundFile3);
		playList.add(soundFile4);
	}
	
	/**
	 * Go through the playList and remove the specified soundFile
	 * @param removeSong the number of the song to be removed
	 */
	public void removeFromPlayList(int removeSong){
		int i = 0;
		for (File J : playList){
			i++;
			if(i==removeSong){
				playList.remove(J);
			}
		}
	}
	
	/**
	 * Go trough the playList and print the soundFiles as string
	 */
	public void searchPlayList(){
		LCD.clear();
		int i = 0;
		for(File J : playList){
			LCD.drawString(map.get(J) + i, 0, i);
			i++;
		}
	}
}
