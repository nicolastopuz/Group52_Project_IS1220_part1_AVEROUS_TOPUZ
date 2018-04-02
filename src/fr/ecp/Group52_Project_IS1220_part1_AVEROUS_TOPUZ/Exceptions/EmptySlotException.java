package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which a User would try to pick 
 * up a bike from an empty parking slot.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class EmptySlotException extends Exception {
	
	/**
	 * Constructor to make an EmptySlotException instance.
	 */
	public EmptySlotException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("There is no bike here.");
	}
}
