package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases, in which minutes or seconds for
 * gps coordinates have been entered with negative values, which makes
 * no sense.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class InvalidCoordinatesException extends Exception {
	/**
	 * Constructor to make a InvalidCoordinatesException instance.
	 */
	public InvalidCoordinatesException() {
		super();
	}
}
