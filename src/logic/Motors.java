/**
 * 
 */
package logic;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
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
	private RegulatedMotor[] motorSync = {this.motorRight};
	
	/**
	 * Constructor
	 */
	public Motors() {
		this.motorLeft = new EV3LargeRegulatedMotor(MotorPort.D);
		this.motorRight = new EV3LargeRegulatedMotor(MotorPort.A);
	}
	
	/**
	 * Used to sync the two drive motors
	 * @param regMotor Left motor
	 * @param regMotor2 Right motor
	 */
	
	public void motorSync(RegulatedMotor regMotor, RegulatedMotor regMotor2) {
		regMotor.synchronizeWith(new RegulatedMotor[] {regMotor2});
	}
	
	/**
	 * Get motorLeft
	 * @return RegulatedMotor Returns the left motor
	 */
	public RegulatedMotor getMotorLeft() {
		return this.motorLeft;
	}
	
	/**
	 * Get motorRight
	 * @return RegulatedMotor Returns the right motor
	 */
	public RegulatedMotor getMotorRight() {
		return this.motorRight;
	}
	
	/**
	 * Gets the array of motors to sync
	 * @return Returns an array of RegulatedMotors
	 */
	public RegulatedMotor[] getMotorSync() {
		return this.motorSync;
	}
	
	/**
	 * Set the acceleration of the drive motors
	 * @param acceleration Acceleration measured in degrees/second/second (deg/s^2)
	 */
	public  void setDriveAcceleration(int acceleration) {
		this.motorLeft.setAcceleration(acceleration);
		this.motorRight.setAcceleration(acceleration);
	}

	/**
	 * Synchronously drives forward with both drive motors until stopped
	 * @return String "Driving forward"
	 */
	public String driveForward() {
		this.motorLeft.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		return "Driving forward";
	}

	/**
	 * Synchronously drives forward (*distance* / 10000) metres
	 * @param distance int Distance measured in 0.1 millimetres
	 * @return String "Driving *distance*"
	 */
	public String driveForward(int distance) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(Toolbox.distanceToDeg(distance), true);
		this.motorRight.rotate(Toolbox.distanceToDeg(distance), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopDriveMotors();
		return "Driving " + distance;
	}

	/**
	 * Synchronously drives backward with both drive motors until stopped
	 * @return String "Driving backward"
	 */
	public String driveBackward() {
		this.motorLeft.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		return "Driving backward";
	}

	/**
	 * Synchronously drives backward (*distance* / 10000) metres
	 * @param distance int Distance measured in 0.1 metres
	 * @return String "Driving *distance*"
	 */
	public String driveBackward(int distance) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(-(Toolbox.distanceToDeg(distance)), true);
		this.motorRight.rotate(-(Toolbox.distanceToDeg(distance)), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopDriveMotors();
		return "Driving " + distance;
	}

	/**
	 * Turns left. Reverses left motor and forwards right motor synchronously until stopped
	 * @return String "Turning left"
	 */
	public String turnLeft() {
		this.motorLeft.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		return "Turning left";
	}

	/**
	 * Turns the bot left *botDegrees*
	 * @param botDegrees int botDegrees is the amount of degrees you want the bot to turn in the real world
	 * @return String "Left *botDegrees* degrees"
	 */
	public String turnLeft(int botDegrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(-(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees))), true);
		this.motorRight.rotate(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees)), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopDriveMotors();
		return "Left " + botDegrees + " degrees";
	}

	/**
	 * Turns left. Reverses right motor and forwards left motor synchronously until stopped
	 * @return "Turning right"
	 */
	public String turnRight() {
		this.motorLeft.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		return "Turning right";
	}

	/**
	 * Turns the bot right *botDegrees*
	 * @param botDegrees int botDegrees is the amount of degrees you want the bot to turn in the real world
	 * @return String "Right *botDegrees* degrees"
	 */
	public String turnRight(int botDegrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees)), true);
		this.motorRight.rotate(-(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees))), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopDriveMotors();
		return "Right " + botDegrees + " degrees";
	}
	
	/**
	 * Stops both drive motors synchronously
	 * @return String "Motors stopped"
	 */
	public String stopDriveMotors() {
		this.motorLeft.startSynchronization();
		this.motorLeft.stop();
		this.motorRight.stop();
		this.motorLeft.endSynchronization();
		return "Motors stopped";
	}
	
	/**
	 * Closes both drive motors synchronously
	 * @return String "Motors closed"
	 */
	public String closeDriveMotors() {
		this.motorLeft.startSynchronization();
		this.motorLeft.close();
		this.motorRight.close();
		this.motorLeft.endSynchronization();
		return "Motors closed";
	}
}
