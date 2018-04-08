package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.UseCase;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User;

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
public class UseCaseTwoUsers {

	public static void main(String[] args) {

		Network n= new Network("network");
		try {
			n = new Network("network",2,2,0.1,0.5); //Two stations, one slot, one bike
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}
		
		n.createUser("Elon Musk");
		n.createUser("Bill Gates");

		User user1 = n.getUserList().get(0);
		User user2 = n.getUserList().get(1);
		System.out.println(user1);
		System.out.println(user2);
		
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		System.out.println("Now to Station 1 for both users ! Who will get there first ?\n");
		user1.goTo(stationCoords.get(1));
		user2.goTo(stationCoords.get(1));
		try {
			user1.getRide().start();
			user2.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
				
	}

}
