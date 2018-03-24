package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which a Station tries to 
 * update a Users itinerary, but this User was not on a ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NotOnRideException extends Exception {
	
	/**
	 * Constructor for making a NotOnRideException instance.
	 */
	public NotOnRideException() {
		super();
	}
}
