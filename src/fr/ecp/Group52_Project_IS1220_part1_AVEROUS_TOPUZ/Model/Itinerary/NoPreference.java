package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station;

/**
 * A preference in arrival stations. The ride calculator has no 
 * particular constraints in this case regarding the arrival
 * station
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class NoPreference implements ArrivalStationPreferenceVisitable {
	
	/**
	 * Constructor to get a NoPreference instance.
	 */
	public NoPreference() {
		
	}
	
	@Override
	public ArrayList<Station> getArrivalStations(Ride ride) {
		ArrayList<Station> allStations = ride.getAllStations();
		ArrayList<Station> arrivalStations = new ArrayList<Station>();
		for (int i = 0; i < allStations.size(); i++) {
			if(allStations.get(i).isFreeSlot()) {
				arrivalStations.add(allStations.get(i));
			}
		}
		return arrivalStations;
	}
}
