package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which someone searches for a User
 * using his userID, but no user with the given ID exists in the Network.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoSuchUserException extends Exception {
	
	/**
	 * Constructor to make a NoSuchUserException instance.
	 */
	public NoSuchUserException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("No user has this ID in the network.");
	}
}
