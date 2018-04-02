package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle invalid syntax in CLUI
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class InvalidSyntaxException extends Exception {
	/**
	 * Constructor to make a InvalidSyntaxException instance.
	 */
	public InvalidSyntaxException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("Invalid command syntax.");
	}
}
