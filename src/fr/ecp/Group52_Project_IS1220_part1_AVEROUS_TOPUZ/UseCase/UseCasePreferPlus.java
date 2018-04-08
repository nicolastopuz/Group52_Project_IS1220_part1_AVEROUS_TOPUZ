package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.UseCase;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.*;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;
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
		
		n.createUser("Elon Musk");
		n.createUser("Bill Gates");
		User user1 = n.getUserList().get(0);
		User user2 = n.getUserList().get(1);
		System.out.println(user1);
		System.out.println(user2);
		
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
		
		user1.setPosition(stationCoords.get(0));
		user2.setPosition(stationCoords.get(0));
		
		System.out.println("Now user "+user1.getName()+"'s arrival point is between a normal station\n"
				+ "and a plus station. With no preference, he should go toward station 1, but with \n"
				+ "preferPlus preference he should be off to station 2 now !\n"
				+ "User "+user2.getName() +" is starting off at the same location, he is going to the\n"
				+ "same spot as the other user, but with no arrival station preference. He will \n"
				+ "therefore go towards station 1.\n");
		
		GPScoordinates arrivalPoint = GPScoordinates.intermediateDistance(stationCoords.get(1), stationCoords.get(2), 0.48);
		
		ArrivalStationPreferenceVisitable arrivalStationPreference1 = new PreferPlus();
		ArrivalStationPreferenceVisitable arrivalStationPreference2 = new NoPreference();
		user1.goTo(arrivalPoint, arrivalStationPreference1 , PathPreferences.Fastest);
		user2.goTo(arrivalPoint, arrivalStationPreference2 , PathPreferences.Fastest);

		try {
			user1.getRide().start();
			user2.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
	}
}
