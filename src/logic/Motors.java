/**
 * 
 */
package logic;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
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
	private EV3MediumRegulatedMotor motorColorSensorArm;
//	private RegulatedMotor motorUndefined;
	private RegulatedMotor[] motorSync = {this.motorRight};
	
	
	public Motors() {
		this.motorLeft = new EV3LargeRegulatedMotor(MotorPort.D);
		this.motorRight = new EV3LargeRegulatedMotor(MotorPort.A);
		this.motorColorSensorArm = new EV3MediumRegulatedMotor(MotorPort.B);
//		this.motorColorSensor = new EV3MediumRegulatedMotor(MotorPort.B);
//		this.motorUndefined = new EV3LargeRegulatedMotor(MotorPort.C);
	}
	
//	public void motorSync(RegulatedMotor regMotor, RegulatedMotor[] motorSync) {
//		regMotor.synchronizeWith(motorSync);
//	}
	
	
	
	
	//DRIVING RELATED STUFF
	
	
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
	
	public  void setDriveAcceleration(int acceleration) {
		this.motorLeft.setAcceleration(acceleration);
		this.motorRight.setAcceleration(acceleration);
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
		this.motorLeft.rotate(Toolbox.distanceToDeg(distance), true);
		this.motorRight.rotate(Toolbox.distanceToDeg(distance), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopMotors();
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
		this.motorLeft.rotate(-(Toolbox.distanceToDeg(distance)), true);
		this.motorRight.rotate(-(Toolbox.distanceToDeg(distance)), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopMotors();
		return "Driving " + distance + " cm";
	}

	public String turnLeft() {
		this.motorLeft.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		return "Turning left";
	}

	public String turnLeft(int botDegrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(-(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees))), true);
		this.motorRight.rotate(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees)), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopMotors();
		return "Left " + botDegrees + " degrees";
	}

	public String turnRight() {
		this.motorLeft.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		return "Turning right";
	}

	public String turnRight(int botDegrees) {
		this.motorLeft.startSynchronization();
		this.motorLeft.rotate(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees)), true);
		this.motorRight.rotate(-(Toolbox.distanceToDeg(Toolbox.botDegToDistance(botDegrees))), true);
		this.motorLeft.endSynchronization();
		this.motorLeft.waitComplete();
		this.motorRight.waitComplete();
		stopMotors();
		return "Right " + botDegrees + " degrees";
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
	
	//COLOR SENSOR ARM
	
	
	
	
	public void setColorSensorArmAcceleration(int acceleration) {
		this.motorColorSensorArm.setAcceleration(acceleration);
		return;
	}
	
	public float getColorSensorArmPosition() {
		return this.motorColorSensorArm.getPosition();
	}
	
	public String turnColorSensorArm(int degrees) {
		this.motorColorSensorArm.rotateTo(degrees);
		this.motorColorSensorArm.waitComplete();
		return "Color sensor arm turned" + degrees + "degrees";
	}
	
	public String colorSensorArmDown() {
		if(getColorSensorArmPosition() != 90) {
			turnColorSensorArm(90);
		}
		return "Color sensor arm in reading position";
		
	}
	
	public String colorSensorArmUp() {
		if(getColorSensorArmPosition() != -90) {
			turnColorSensorArm(-90);
		}
		return "Color sensor arm up";
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
