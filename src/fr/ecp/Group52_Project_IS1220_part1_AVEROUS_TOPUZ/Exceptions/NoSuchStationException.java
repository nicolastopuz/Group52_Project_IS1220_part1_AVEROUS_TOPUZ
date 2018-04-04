package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions;

/**
 * An exception to handle cases in which someone searches for a Station
 * using its stationID, but no station with the given ID exists in the Network.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoSuchStationException extends Exception {
	/**
	 * Constructor to make a NoSuchStationException instance.
	 */
	public NoSuchStationException() {
		super();
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("No station has this ID in the network.");
	}
}
