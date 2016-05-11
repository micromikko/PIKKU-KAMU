//package test;
//
//import lejos.hardware.sensor.*;
//import lejos.utility.Delay;
//import lejos.hardware.port.SensorPort;
//
//import java.util.List;
//
//import lejos.hardware.Button;
//import lejos.hardware.lcd.*;
//import logic.Motors;
//import logic.IRSListener;
//import logic.ColorChecker;
//import logic.ColorSensorArm;
//import test.ZigZagSearchTest;
//
//public class ArmTest {
//	
//	public static void main(String[] args) {
//		
//		Motors motors = new Motors();
//		ColorSensorArm cs = new ColorSensorArm();
//		ColorChecker colorChecker = new ColorChecker(SensorPort.S4);
//		cs.setColorSensorArmSpeed(100);
//		
//		
//		
//		//List lista = colorChecker.mySensor.getAvailableModes();
//		
//		/*int count = 1;
//		for(Object jee : lista) {
//			LCD.drawString(jee.toString(), 1, count);
//			count++;
//		}
//		
//		while(!Button.ESCAPE.isDown()) {
//			continue;
//		}*/
//		
//		Object paska = colorChecker.mySensor.getColorIDMode();
//		LCD.drawString(paska.toString(), 1, 1);
//		
//		Delay.msDelay(5000);
//		
//		
//		
//	
//		
//		//ZigZagSearchTest searchtest = new ZigZagSearchTest();
//		
//		//LCD.drawString("Preparing for treasure hunt", 1, 1);
//		//Delay.msDelay(3000);
//		//LCD.clear();
//		
//		//searchtest.doSearch();
//		
//		/*while(!Button.ESCAPE.isDown()) {
//			LCD.clear();
//			LCD.drawString("" + colorChecker.getCurrentColor(), 1, 1);
//			Delay.msDelay(100);
//			
//		}*/
//		
//		
//		
//		/* motors.colorSensorArmDown();
//		LCD.drawString("" + motors.getColorSensorArmPosition(), 1, 1);
//		Delay.msDelay(3000);
//		LCD.clear();
//		motors.colorSensorArmUp();
//		LCD.drawString("" + motors.getColorSensorArmPosition(), 1, 1);
//		Delay.msDelay(3000);
//		LCD.clear();
//		motors.colorSensorArmDown();
//		
//		Delay.msDelay(3000);
//		colorChecker.start();
//		LCD.clear();
//		
//		while(!Button.ESCAPE.isDown()) {
//			LCD.clear();
//			LCD.drawString("" + colorChecker.getCurrentColor(), 1, 1);
//			Delay.msDelay(300); 
//			
//		}
//		*/
//
//		
//	
//	}
//	
//	
//
//}
