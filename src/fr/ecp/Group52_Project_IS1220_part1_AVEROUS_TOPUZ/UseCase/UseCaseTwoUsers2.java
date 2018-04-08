package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.UseCase;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.NetworkStatisticsSortingMethods;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.ParkingSlotState;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.StatisticCompiler;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.User;
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
		
		n.createUser("Elon Musk");
		n.createUser("Bill Gates");
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		
		User user1 = n.getUserList().get(0);
		User user2 = n.getUserList().get(1);
		user1.setPosition(stationCoords.get(0)); //User user1 starts his journey at station 0.
		user2.setPosition(stationCoords.get(2)); //User user2 starts his journey at station 2.
		
		System.out.println(user1);
		System.out.println(user2);
		

		

		
		System.out.println("Now to Station 1 user "+  user1.getName()  + "!\n");
		user1.goTo(stationCoords.get(1));
		try {
			user1.getRide().start();
			user1.getRide().join();
		} catch (NoRideException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\nNow that user "+  user1.getName()  + " has arrived, he will try to go back to station 0.");
		System.out.println("However, user " + user2.getName() + " coming from station 2 also wishes to go to station 0.");
		System.out.println("there is only one free slot, so let's see how the updateArrival method works.\n");
		user1.goTo(stationCoords.get(0));
		user2.goTo(stationCoords.get(0));
		try {
			user1.getRide().start();
			user2.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
		
		
	}

}
