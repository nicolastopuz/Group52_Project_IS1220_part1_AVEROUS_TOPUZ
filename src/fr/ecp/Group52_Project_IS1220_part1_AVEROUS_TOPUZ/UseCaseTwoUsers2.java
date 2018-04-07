package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * A class to test the functioning of the network for two user.
 * In this usecase there are only two users, two stations and two bikes. 
 * This usecase is used to test if the ride interruption functionality 
 * does work : the first user goes to the common departure station and 
 * takes away the only bike : the second user then has to change his itinerary 
 * in order to adapt to this change, and go to a station in which there 
 * is an available bike.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */
public class UseCaseTwoUsers2 {

	public static void main(String[] args) {
		
		StatisticCompiler stats = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		Network n= new Network("network");
		try {
			n = new Network("network",3,2,0.1,0.5); //Three stations, two slots, one bike
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}
		
		Station station0 = n.getStationList().get(0);
		station0.getParkingSlots().get(1).setState(ParkingSlotState.outOfOrder);
		//The second slot of station 0 is out of order. Only the first slot can be used.
		
		n.createUser("Lapitre");
		n.createUser("Paolo");
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		
		User lapitre = n.getUserList().get(0);
		User paolo = n.getUserList().get(1);
		lapitre.setPosition(stationCoords.get(0)); //User lapitre starts his journey at station 0.
		paolo.setPosition(stationCoords.get(2)); //User paolo starts his journey at station 2.
		
		System.out.println(lapitre);
		System.out.println(paolo);
		

		

		
		System.out.println("Now to Station 1 user "+  lapitre.getName()  + "!");
		lapitre.goTo(stationCoords.get(1));
		try {
			lapitre.getRide().start();
			lapitre.getRide().join();
		} catch (NoRideException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\nNow that user "+  lapitre.getName()  + " has arrived, he will try to go back to station 0.");
		System.out.println("However, user " + paolo.getName() + " coming from station 2 also wishes to go to station 0.");
		System.out.println("there is only one free slot, so let's see how the updateArrival method works.");
		lapitre.goTo(stationCoords.get(0));
		paolo.goTo(stationCoords.get(0));
		try {
			lapitre.getRide().start();
			paolo.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
		
		
	}

}
