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
	public static final double WHEEL_RADIUS = 1.8;

	public static int driveActual;
	public static int turnActual;

	public static int degToDistance(int degrees) {
		double theta = degrees * RADIAN;
		int distance = (int) (theta * WHEEL_RADIUS);
		return distance;
	}

	public static int distanceToDeg(int distance) {
		double theta = distance / WHEEL_RADIUS;
		int degrees = (int) (theta / RADIAN);
		return degrees;
	}
}

// s=theta*r