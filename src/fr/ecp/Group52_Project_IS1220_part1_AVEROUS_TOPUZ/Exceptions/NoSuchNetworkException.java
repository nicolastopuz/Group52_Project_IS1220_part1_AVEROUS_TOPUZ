package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which someone searches for a Network
 * using its name or id, but no Network with the given parameters exists 
 * the system.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoSuchNetworkException extends Exception{
	/**
	 * Constructor to make a NoSuchNetworkException instance.
	 */
	public NoSuchNetworkException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("No such network exists in the system.");
	}
}
