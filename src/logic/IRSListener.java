///**
// * 
// */
//package logic;
//
//import lejos.hardware.Button;
//import lejos.hardware.sensor.EV3IRSensor;
///**
// * @author micromikko
// *
// */
//import logic.Motors;
//
//public class IRSListener extends Thread {
//	private EV3IRSensor irSensor;
//
//	public IRSListener(EV3IRSensor sensor) {
//		this.irSensor = sensor;
//	}
//	
//	// run as daemon (no need to deactivate)
//	public void run() {
//
//		int irCommand1;
//		int irCommand2;
//		int irCommand3;
//		int irCommand4;
//
//		while(!Button.ESCAPE.isDown()) {
//
//			irCommand1 = this.irSensor.getRemoteCommand(0);
//			irCommand2 = this.irSensor.getRemoteCommand(1);
//			irCommand3 = this.irSensor.getRemoteCommand(2);
//			irCommand4 = this.irSensor.getRemoteCommand(3);
//			
//			channelOne();
//			channelTwo();
//			channelThree();
//			channelFour();
//		}
//	}
//
//	public void channelOne(int irCommand1) {
//		if(irCommand1 != 0) {
//			
//			switch(irCommand1) {
//			// top-left
//			case 1:
//				
//				break;
//			// bottom-left
//			case 2:
//				
//				break;
//			// top-right
//			case 3:
//				
//				break;
//			// bottom-right
//			case 4:
//				
//				break;
//			// top-left + top-right
//			case 5:
//				
//				break;
//			case 6:
//		}
//	}
//
//	public void channelTwo() {
//		if(irCommand2 != 0) {
//			
//		}
//	}
//
//	public void channelThree() {
//		if(irCommand3 != 0) {
//			
//		}
//	}
//
//	public void channelFour() {
//		if(irCommand4 != 0) {
//			
//		}
//	}
//}
//
//
//// getSeekMode()
//// in seek mode the sensor locates up to four beacons and provides
//// the bearing and distance of each beacon
//
///*
//1 TOP-LEFT
//2 BOTTOM-LEFT
//3 TOP-RIGHT
//4 BOTTOM-RIGHT
//5 TOP-LEFT + TOP-RIGHT
//6 TOP-LEFT + BOTTOM-RIGHT
//7 BOTTOM-LEFT + TOP-RIGHT
//8 BOTTOM-LEFT + BOTTOM-RIGHT
//9 CENTRE/BEACON
//10 BOTTOM-LEFT + TOP-LEFT
//11 TOP-RIGHT + BOTTOM-RIGHT
//*/