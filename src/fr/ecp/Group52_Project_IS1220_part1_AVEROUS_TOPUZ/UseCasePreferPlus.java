package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.MoreBikesThanSlotsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OutOfBoundsException;

/**
 * This class is used to test the good functioning of the PreferPlus
 * arrival station preference.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */
public class UseCasePreferPlus {

	public static void main(String[] args) {

		StatisticCompiler stats = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		Network n= new Network("network");
		try {
			n = new Network("network",2,2,0.1,0.5); //Two stations, one slot, one bike
			n.createStationPlus(2, 1, 1); //A third station, of plus type, with two slots, and one bike
		} catch (InvalidProportionsException | MoreBikesThanSlotsException e) {
			e.printStackTrace();
		}
		
		n.createUser("Lapitre");
		User lapitre = n.getUserList().get(0);
		System.out.println(lapitre);
		
		ArrayList<Station> allStations = n.getStationList();
		
		try {
			allStations.get(0).setLocation(new GPScoordinates(0,0));
			allStations.get(1).setLocation(new GPScoordinates(-0.0002, 0.0003));
			allStations.get(2).setLocation(new GPScoordinates(0.0002, 0.0003));
		} catch (OutOfBoundsException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		lapitre.setPosition(stationCoords.get(0));
		
		System.out.println("Now user "+lapitre.getName()+"'s arrival point is between a normal station\n"
				+ "and a plus station. With no preference, he should go toward station 1, but with \n"
				+ "preferPlus preference he should be off to station 2 now !");
		
		ArrivalStationPreferenceVisitable arrivalStationPreference = new NoPreference();
		lapitre.goTo(GPScoordinates.intermediateDistance(stationCoords.get(1), stationCoords.get(2), 0.48), arrivalStationPreference , PathPreferences.Fastest);
		
		try {
			lapitre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
	}
}
