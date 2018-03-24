package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which a User would try to drop 
 * off a bike at a station in which there are no free parking slots.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoFreeSlotException extends Exception {
	
	/**
	 * Constructor to make a NoFreeSlotException instance.
	 */
	public NoFreeSlotException() {
		super();
	}
}
