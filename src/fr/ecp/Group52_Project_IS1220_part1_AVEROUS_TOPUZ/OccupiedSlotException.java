package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;


/**
 * An exception to handle cases in which a User would try to drop 
 * off a bike at an occupied parking slot.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class OccupiedSlotException extends Exception {
	
	/**
	 * Constructor for making an OccupiedSlotException instance.
	 */
	public OccupiedSlotException() {
		super();
	}
}
