package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which we want to access the ride  
 * of a user while he isn't on a ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class NoRideException extends Exception {

	public NoRideException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("User is not on a ride. Impossible to unlock bike. \nPlease start a ride before trying again.");
	}
}
