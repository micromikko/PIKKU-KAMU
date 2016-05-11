package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class MusicController extends Thread {
	final File soundFile1 = new File("Space_Alert3.wav");
	final File soundFile2 = new File("laaseri.wav");
	final File soundFile3 = new File("Space_Alert2.wav");
	final File soundFile4 = new File("Space_Alert1.wav");
	//private int soundNumber;
	private int volume;
	private boolean playSound;
	private ArrayList<File> playList = new ArrayList<>();
	Map<File, String> map = new HashMap<File, String>();
	
	public MusicController () {
		//soundNumber = 0;
		volume = 50;
		playSound = true;
		map.put(soundFile1, "Space Alert 3");
		map.put(soundFile2, "Laaseri");
		map.put(soundFile3, "Space Alert 2");
		map.put(soundFile4, "Space Alert 1");
	}
	
	public void jaska() {
		for (File J : playList){
			LCD.clear();
			LCD.drawString(map.get(J), 0, 0);
			Sound.playSample(J, volume);
			Delay.msDelay(5000);
		}
	}
	
	public void musicPlay() {
		
	}
	
	/*public int setSound(int sound){
		soundNumber = sound;
		return sound;
	}*/
	
	public int getVolume(){
		return volume;
	}
	
	public int setVolume(int a){
		this.volume = volume + a;
		return volume;
	}
	
	public void killSound(){
		playSound = false;
	}
	
	public void addToPlayList(){
		playList.clear();
		playList.add(soundFile1);
		playList.add(soundFile2);
		playList.add(soundFile3);
		playList.add(soundFile4);
	}
	
	public void removeFromPlayList(int removeSong){
		int i = 0;
		for (File J : playList){
			i++;
			if(i==removeSong){
				playList.remove(J);
			}
		}
	}
	
	public void searchPlayList(){
		LCD.clear();
		int i = 0;
		for(File J : playList){
			LCD.drawString(map.get(J) + i, 0, i);
			i++;
		}
	}
}