/**
 * 
 */
package logic;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class ColorSensorArm {
	
	private EV3MediumRegulatedMotor motorColorSensorArm;

	/**
	 * Contructor
	 */
	public ColorSensorArm() {
		this.motorColorSensorArm = new EV3MediumRegulatedMotor(MotorPort.B);
	}
	
	/**
	 * Set ColorSensor arm's acceleration
	 * @param acceleration void Acceleration measured in degrees/second/second (deg/s^2)
	 */
	public void setColorSensorArmAcceleration(int acceleration) {
		this.motorColorSensorArm.setAcceleration(acceleration);
		return;
	}
	
	/**
	 * Set ColorSensor arm's speed
	 * @param speed void Speed measured in degrees per second (deg/s)
	 */
	public void setColorSensorArmSpeed(int speed) {
		this.motorColorSensorArm.setSpeed(speed);
	}
	
	/**
	 * Get ColorSensor arm's position
	 * @return int  tacho count
	 */
	public int getColorSensorArmPosition() {
		return this.motorColorSensorArm.getTachoCount();
	}
	
	/**
	 * Rotate ColorSensor arm to position x
	 * @param degrees int Degrees
	 * @return String "Color sensor arm turned x degrees"
	 */
	public String turnColorSensorArm(int degrees) {
		this.motorColorSensorArm.rotateTo(degrees);
		this.motorColorSensorArm.waitComplete();
		return "Color sensor arm turned" + degrees + "degrees";
	}
	
	/**
	 * Lowers ColorSensor arm
	 * @return String "Color sensor arm in reading position"
	 */
	public String colorSensorArmDown() {
		motorColorSensorArm.forward();
		
		while(!motorColorSensorArm.isStalled()) {
			continue;
		}
		motorColorSensorArm.stop();
		motorColorSensorArm.flt();
		return "Color sensor arm in reading position";
		
	}
	
	/**
	 * Raises ColorSensor arm
	 * @return String "Color sensor arm up"
	 */
	public String colorSensorArmUp() {
		motorColorSensorArm.backward();
		
		while(!motorColorSensorArm.isStalled()) {
			continue;
		}
		motorColorSensorArm.stop();
		motorColorSensorArm.flt();
		
		return "Color sensor arm up";
		
	}
	
	/**
	 * Stop the ColorSensor arm's motor
	 * @return String "Color sensor arm motor stopped"
	 */
	public String stopColorSensorArmMotor() {
		this.motorColorSensorArm.stop();
		return "Color sensor arm motor stopped";
	}
	
	/**
	 * Closes the ColorSensor arm's motor
	 * @return String "Color sensor arm motor closed"
	 */
	public String closeColorSensorArmMotor() {
		this.motorColorSensorArm.close();
		return "Color sensor arm motor closed";
	}
}
