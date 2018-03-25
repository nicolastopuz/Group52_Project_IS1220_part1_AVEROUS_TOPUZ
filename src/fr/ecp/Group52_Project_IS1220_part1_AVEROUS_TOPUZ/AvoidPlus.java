package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

/**
 * A preference in arrival stations. The ride calculator has to avoid
 * sending the user to a Plus station on his ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class AvoidPlus implements ArrivalStationPreferenceVisitable {
	
	/**
	 * Constructor to get an AvoidPlus instance.
	 */
	public AvoidPlus() {
		
	}

	@Override
	public ArrayList<Station> getArrivalStations(Ride ride) {
		ArrayList<Station> allStations = ride.getAllStations();
		ArrayList<Station> arrivalStations = new ArrayList<Station>();
		for (int i = 0; i < allStations.size(); i++) {
			if(allStations.get(i).isFreeSlot() && !(allStations.get(i) instanceof StationPlus)) {
				arrivalStations.add(allStations.get(i));
			}
		}
		return arrivalStations;
	}
}
