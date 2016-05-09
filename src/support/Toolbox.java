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

	public static void wheelRadiusIncrease() {
		WHEEL_RADIUS++;
	}
	
	public static void wheelRadiusDecrease() {
		WHEEL_RADIUS--;
	}
	
	public static void botRadiusIncrease() {
		BOT_RADIUS++;
	}
	
	public static void botRadiusDecrease() {
		BOT_RADIUS--;
	}
	
	public static String getWHEEL_RADIUS(int WHEEL_RADIUS) {
		String s = "WHEEL RADIUS: " + WHEEL_RADIUS;
		return s;
	}
	
	public static String getBOT_RADIUS(int BOT_RADIUS) {
		String s = "BOT RADIUS: " + BOT_RADIUS;
		return s;
	}
	
	public static int degToDistance(int degrees) {
		int theta = (int) (degrees * RADIAN);
		int distance = (int) (theta * WHEEL_RADIUS);
		return distance;
	}

	public static int distanceToDeg(int distance) {
		int theta = distance / WHEEL_RADIUS;
		int degrees = (int) (theta / RADIAN);
		return degrees;
	}
	
	public static int botDegToDistance(int botDegrees) {
		int distance = (int) (botDegrees * PI * BOT_RADIUS) / 180;
		return (int) distance;
	}
}