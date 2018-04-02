package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which a User inputs Coordinates
 * into a GPScoordinates object, that are out of bounds.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class OutOfBoundsException extends Exception {
	
	/**
	 * Constructor for making an OutOfBoundsException instance.
	 */
	public OutOfBoundsException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("Entered data is out of bounds.");
	}
}
