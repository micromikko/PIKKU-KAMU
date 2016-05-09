package modes;

//package music;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

public class Jukebox extends Thread {
	final File soundFile1 = new File("Space_Alert3.wav");
	final File soundFile2 = new File("laaseri.wav");
	final File soundFile3 = new File("Space_Alert2.wav");
	final File soundFile4 = new File("Space_Alert1");
	private EV3IRSensor irSensor;
	private int soundNumber;
	private int volume;
	private boolean playSound;
	private ArrayList<File> playList = new ArrayList<>();
	Map<File, String> map = new HashMap<File, String>();
	
	public Jukebox () {
		soundNumber = 0;
		volume = 0;
		playSound = true;
		map.put(soundFile1, "Space Alert 3");
		map.put(soundFile2, "Laaseri");
		map.put(soundFile3, "Space Alert 2");
		map.put(soundFile4, "Space Alert 1");
		playList.add(soundFile1);
		playList.add(soundFile2);
		playList.add(soundFile3);
		playList.add(soundFile4);
		
		
	}
	
	public void run() {
		for (File J : playList){
			if(playSound==true){
				Sound.playSample(J, volume);
				LCD.drawString(map.get(J), 0, 0);
				Delay.msDelay(1000);
			}	
		}
		
		
	}
	public void musicControl() {
		int remoteCommand = irSensor.getRemoteCommand(0);
		switch(remoteCommand) {
			case 1:
				setVolume(5);
			case 2:
				setVolume(-5);
			case 3:
				
			case 4:
	
	
		}
	}
	
	public int setSound(int sound){
		soundNumber = sound;
		return sound;
	}
	
	public int setVolume(int a){
		this.volume = volume + a;
		return volume;
	}
	
	public void killSound(){
		playSound = false;
	}
	
	public void addToPlayList(){
		
	}
	
	public void removeFromPlayList(int i){
		int a = 0;
		for (File J : playList){
			i++;
			if(i==a){
				playList.add();
				Delay.msDelay(1000);
			}	
		}
	}
}
