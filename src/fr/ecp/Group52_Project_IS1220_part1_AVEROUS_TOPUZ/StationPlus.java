package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * Stations are among the most important elements of the myVelib
 * system. A station is where bicycles can be rented and dropped. 
 * It consists of a number of parking slots where bikes are stored 
 * and of a terminal which users can interact with in order to 
 * rent bicycles. Parking slots can be be occupied by a bicycle, 
 * free or out-of-order. A station is located in a specific place 
 * specified by GPS coordinates. A station can be on service or 
 * offline: if it is offline all of its parking bays as well as the 
 * terminal cannot be used. There exist two types of stations, a 
 * <i>standard</i> type, and a <i>plus</i> type. <b>This class represents 
 * the <i>plus</i> type.</b> When a user who holds a Velib 
 * card drops a bicycle at a <i>plus</i> station, they earns 5 minutes credits 
 * in their time balance. Each station has a unique numerical ID and so 
 * each parking slot (within a station) has a unique numerical ID.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class StationPlus extends Station {
	
	/**
	 * Constructor for StationPlus class.
	 * 
	 * @param numberofslots
	 * @param location
	 */
	public StationPlus(int numberofslots, GPScoordinates location) {
		super(numberofslots, location);
	}
	
	/**
	 * Constructor for StationPlus class.
	 * 
	 * @param numberofslots
	 * @param location
	 * @throws InvalidProportionsException 
	 * @throws MoreBikesThanSlotsException 
	 */
	public StationPlus(int numberofslots, GPScoordinates location, int numberOfBikes, float proportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		super(numberofslots, location, numberOfBikes, proportion);
	}
	
}
