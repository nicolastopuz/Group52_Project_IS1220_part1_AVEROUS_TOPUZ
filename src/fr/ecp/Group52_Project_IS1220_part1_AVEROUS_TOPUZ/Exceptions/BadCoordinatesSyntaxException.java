package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle bad syntax when inputting GPS coordinates as
 * String in DMS format.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class BadCoordinatesSyntaxException extends Exception {
	/**
	 * Constructor to make a BadCoordinatesSyntaxException instance.
	 */
	public BadCoordinatesSyntaxException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("The syntax of coordinates was wrong. \nFormat should be DD\"MM'SS,SSS.");
	}
}
