/**
 * 
 */
package logic;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import support.Toolbox;

/**
 * @author micromikko
 *
 */

public class Motors {
	
	private RegulatedMotor motorLeft;
	private RegulatedMotor motorRight;
//	private RegulatedMotor motorColorSensor;
//	private RegulatedMotor motorUndefined;
	private RegulatedMotor[] motorSync = {this.motorRight};
	
	
	public Motors() {
		this.motorLeft = new EV3LargeRegulatedMotor(MotorPort.D);
		this.motorRight = new EV3LargeRegulatedMotor(MotorPort.A);
//		this.motorColorSensor = new EV3MediumRegulatedMotor(MotorPort.B);
//		this.motorUndefined = new EV3LargeRegulatedMotor(MotorPort.C);
	}
	
//	public void motorSync(RegulatedMotor regMotor, RegulatedMotor[] motorSync) {
//		regMotor.synchronizeWith(motorSync);
//	}
	
	public void motorSync(RegulatedMotor regMotor, RegulatedMotor regMotor2) {
		regMotor.synchronizeWith(new RegulatedMotor[] {regMotor2});
	}
	
	public RegulatedMotor getMotorLeft() {
		return this.motorLeft;
	}
	
	public RegulatedMotor getMotorRight() {
		return this.motorRight;
	}
	
	public RegulatedMotor[] getMotorSync() {
		return this.motorSync;
	}
	
//	public void motorEndSync(RegulatedMotor regMotor) {
//		regMotor.endSynchronization();
//	}

	public String driveForward() {
		this.motorLeft.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		return "Driving forward";
	}

	public String driveForward(int distance) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(Toolbox.distanceToDeg(distance));
		this.motorRight.rotate(Toolbox.distanceToDeg(distance));
		this.motorLeft.endSynchronization();
		return "Driving " + distance + " cm";
	}

	public String driveBackward() {
		this.motorLeft.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		return "Driving backward";
	}

	public String driveBackward(int distance) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(-(Toolbox.distanceToDeg(distance)));
		this.motorRight.rotate(-(Toolbox.distanceToDeg(distance)));
		this.motorLeft.endSynchronization();
		return "Driving " + distance + " cm";
	}

	public String turnLeft() {
		this.motorLeft.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		return "Turning left";
	}

	public String turnLeft(int degrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(-degrees);
		this.motorRight.rotate(degrees);
		this.motorLeft.endSynchronization();
		return "Left " + degrees + " degrees";
	}

	public String turnRight() {
		this.motorLeft.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		return "Turning right";
	}

	public String turnRight(int degrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(degrees);
		this.motorRight.rotate(-degrees);
		this.motorLeft.endSynchronization();
		return "Right " + degrees + " degrees";
	}
	
	public String stopMotors() {
		this.motorLeft.startSynchronization();
		this.motorLeft.stop();
		this.motorRight.stop();
		this.motorLeft.endSynchronization();
		return "Motors stopped";
	}
	
	public String closeMotors() {
		this.motorLeft.startSynchronization();
		this.motorLeft.close();
		this.motorRight.close();
		this.motorLeft.endSynchronization();
		return "All motors closed";
	}
}

// multithread motors? simultaneous rotary initiation necessary
// for accurate and reliable navigation (no positioning beyond unreliable
// IR distance/bearing tracker)

// getMaxSpeed()
// based on current voltage

// void synchronizeWith(RegulatedMotor[] syncList)
// set sync list
// links motorLeft and motorRight together for synchronous initiation

// void startSynchronization()
// begin set of synchronized motor operations

// void endSynchronization()
// complete a set of synchronized motor operations
