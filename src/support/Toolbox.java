/**
 * 
 */
package support;


/**
 * @author micromikko
 *
 */
public class Toolbox {

	public static final double PI = 3.14159;
	public static final double RADIAN = 0.01745;

	public static int WHEEL_RADIUS = (374 / 2);
	public static int BOT_RADIUS = (2600 / 2);
	public static int BOT_WIDTH = 2100;

	/**
	 * Increases the wheel radius by 1. For debugging only.
	 */
	public static void wheelRadiusIncrease() {
		WHEEL_RADIUS++;
	}
	
	/**
	 * Decreases the wheel radius by 1. For debugging only.
	 */
	public static void wheelRadiusDecrease() {
		WHEEL_RADIUS--;
	}
	
	/**
	 * Increases the bot radius by 1. For debugging only.
	 */
	public static void botRadiusIncrease() {
		BOT_RADIUS++;
	}
	
	/**
	 * Decreases the wheel radius by 1. For debugging only.
	 */
	public static void botRadiusDecrease() {
		BOT_RADIUS--;
	}
	
	/**
	 * Gets the wheel radius (treads included)
	 * @param WHEEL_RADIUS int Wheel radius in 0.1mm (10000 = 1 metre)
	 * @return String "WHEEL RADIUS: *WHEEL_RADIUS*"
	 */
	public static String getWHEEL_RADIUS(int WHEEL_RADIUS) {
		String s = "WHEEL RADIUS: " + WHEEL_RADIUS;
		return s;
	}
	
	/**
	 * Gets the bot radius (diagonal from top left to lower right)
	 * @param BOT_RADIUS int Bot radius in 0.1mm (10000 = 1 metre)
	 * @return String "BOT RADIUS: *BOT_RADIUS*"
	 */
	public static String getBOT_RADIUS(int BOT_RADIUS) {
		String s = "BOT RADIUS: " + BOT_RADIUS;
		return s;
	}
	
	/**
	 * Converts motor rotation to distance travelled by treads
	 * @param degrees int The amount of degrees the motor rotates
	 * @return int Returns the amount of distance the tread travels. Distance in 0.1mm (10000 = 1 metre)
	 */
	public static int degToDistance(int degrees) {
		int theta = (int) (degrees * RADIAN);
		int distance = (int) (theta * WHEEL_RADIUS);
		return distance;
	}

	/**
	 * Converts tread distance to motor rotation (degrees)
	 * @param distance int Distance travelled by tread in 0.1mm (10000 = 1 metre)
	 * @return int Returns the amount of degrees to rotate the motor
	 */
	public static int distanceToDeg(int distance) {
		int theta = distance / WHEEL_RADIUS;
		int degrees = (int) (theta / RADIAN);
		return degrees;
	}
	
	/**
	 * Converts real world bot rotation (degrees) to distance travelled by tread (0.1mm) (10000 = 1 metre)
	 * @param botDegrees int Degrees the bot should rotate
	 * @return int Returns the distance the tread should travel in 0.1mm (10000 = 1 metre)
	 */
	public static int botDegToDistance(int botDegrees) {
		int distance = (int) (botDegrees * PI * BOT_RADIUS) / 180;
		return (int) distance;
	}
}
