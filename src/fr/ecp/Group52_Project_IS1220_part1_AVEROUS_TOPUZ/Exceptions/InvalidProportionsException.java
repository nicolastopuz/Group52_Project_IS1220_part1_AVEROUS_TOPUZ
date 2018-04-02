package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which the proportions are superior
 * to 100% when creating a new station.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class InvalidProportionsException extends Exception {
	/**
	 * Constructor to make a InvalidProportionsException instance.
	 */
	public InvalidProportionsException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("Proportions are incoherent. Could not create station.");
	}
}
