package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * A class to test the functionning of the network for a unique user.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */

public class UseCaseUniqueUser {

	public static void main(String[] args) {
		StatisticCompiler stats = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		Network n= new Network("network");
		try {
			n = new Network("network",10,10,0.1,0.7);
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}
		
		n.createUser("Lapitre");
		System.out.println(n.getUserList().get(0));
		
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		User pierre = n.getUserList().get(0);
		
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}		
		
		System.out.println();
		
		ArrivalStationPreferenceVisitable arrivalPref = new PreferPlus();
		PathPreferenceVisitor pathPref = new ShortestPath();
		
		System.out.println("Now to Station 4 !");
		pierre.goTo(stationCoords.get(4), arrivalPref, PathPreferences.Shortest);
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}
		
		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 5 !");
		pierre.goTo(stationCoords.get(5));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		pierre.goTo(stationCoords.get(7));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");
		

		System.out.println("Now to Station 4 !");
		pierre.goTo(stationCoords.get(4));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 2 !");
		pierre.goTo(stationCoords.get(2));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 4 !");
		pierre.goTo(stationCoords.get(4));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		pierre.goTo(stationCoords.get(7));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 5 !");
		pierre.goTo(stationCoords.get(5));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 8 !");
		pierre.goTo(stationCoords.get(8));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 4 !");
		pierre.goTo(stationCoords.get(4));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		pierre.goTo(stationCoords.get(7));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 1 !");
		pierre.goTo(stationCoords.get(1));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");

		System.out.println("Now to Station 9 !");
		pierre.goTo(stationCoords.get(9));
		try {
			pierre.getRide().start();
		} catch (NoRideException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ pierre.getRides().size()+ " rides, " +pierre.getName()
				+ " has a time credit of : "+pierre.getTimeCredit()+".");
		
		System.out.println(stats.visit(n));
		System.out.println(stats.visit(pierre));
		System.out.println(stats.visit(allStations.get(4)));
		System.out.println(stats.visit(allStations.get(2)));
		
	}

}
