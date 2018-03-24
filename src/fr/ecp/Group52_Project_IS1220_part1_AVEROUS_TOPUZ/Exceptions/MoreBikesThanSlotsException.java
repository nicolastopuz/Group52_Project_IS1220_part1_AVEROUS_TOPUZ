package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which it is asked to create more
 * bikes than there are available slots at a station.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class MoreBikesThanSlotsException extends Exception {
	/**
	 * Constructor to make MoreBikesThanSlotsException instance.
	 */
	public MoreBikesThanSlotsException() {
		super();
	}
}
