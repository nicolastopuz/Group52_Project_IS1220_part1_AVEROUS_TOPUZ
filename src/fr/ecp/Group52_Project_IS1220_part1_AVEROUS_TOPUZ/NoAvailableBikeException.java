package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * An exception to handle cases in which a User would try to pick 
 * up a bike at a station in which there are no available bikes.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoAvailableBikeException extends Exception {
	
	/**
	 * Constructor to make a NoAvailableBikeException instance.
	 */
	public NoAvailableBikeException() {
		super();
	}
}
