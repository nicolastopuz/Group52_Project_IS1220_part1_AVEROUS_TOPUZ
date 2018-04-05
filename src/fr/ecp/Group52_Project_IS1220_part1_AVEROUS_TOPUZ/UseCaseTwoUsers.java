package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * A class to test the functionning of the network for two user.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */
public class UseCaseTwoUsers {

	public static void main(String[] args) {

		StatisticCompiler stats = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		Network n= new Network("network");
		try {
			n = new Network("network",2,2,0.1,0.5); //Two stations, one slot, one bike
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}
		
		n.createUser("Lapitre");
		n.createUser("Paolo");

		User lapitre = n.getUserList().get(0);
		User paolo = n.getUserList().get(1);
		System.out.println(lapitre);
		System.out.println(paolo);
		
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		System.out.println("Now to Station 1 for both users ! Who will get there first ? !");
		lapitre.goTo(stationCoords.get(1));
		paolo.goTo(stationCoords.get(1));
		try {
			lapitre.getRide().start();
			paolo.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
