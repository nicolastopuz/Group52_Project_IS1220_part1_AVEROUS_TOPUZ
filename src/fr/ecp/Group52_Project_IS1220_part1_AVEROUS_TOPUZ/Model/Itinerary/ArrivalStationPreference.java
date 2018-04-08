package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;

/**
 * A simple interface for all arrival stations possible. The only 
 * method returns the list of all possible ArrivalStations following 
 * the criteria set by the very nature of the preference.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface ArrivalStationPreference {
	
	/**
	 * A method to get all possible arrival stations as an ArrayList
	 * within a ride, depending on the preference.
	 * 
	 * @param ride 	The ride in question
	 * @return	an ArrayList containing all possible arrival stations,
	 * following the given preference.
	 */
	public ArrayList<Station> getArrivalStations(Ride ride);
	
}
