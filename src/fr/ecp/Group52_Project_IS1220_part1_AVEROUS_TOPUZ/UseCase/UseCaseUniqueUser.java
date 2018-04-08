package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.UseCase;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.InvalidProportionsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Network;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.NetworkStatisticsSortingMethods;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.StatisticCompiler;

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
		
		n.createUser("Elon Musk");
		System.out.println(n.getUserList().get(0));
		
		ArrayList<Station> allStations = n.getStationList();
		ArrayList<GPScoordinates> stationCoords = new ArrayList<GPScoordinates>();
		
		for(Station s : allStations) {
			stationCoords.add(s.getLocation());
		}
		
		User user1 = n.getUserList().get(0);
		
		
		System.out.println("Now to Station 4 !");
		user1.goTo(stationCoords.get(4));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 5 !");
		user1.goTo(stationCoords.get(5));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		user1.goTo(stationCoords.get(7));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");
		

		System.out.println("Now to Station 4 !");
		user1.goTo(stationCoords.get(4));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 2 !");
		user1.goTo(stationCoords.get(2));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 4 !");
		user1.goTo(stationCoords.get(4));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		user1.goTo(stationCoords.get(7));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 5 !");
		user1.goTo(stationCoords.get(5));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 8 !");
		user1.goTo(stationCoords.get(8));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 4 !");
		user1.goTo(stationCoords.get(4));
		try {
			user1.getRide().start(); user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 7 !");
		user1.goTo(stationCoords.get(7));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 1 !");
		user1.goTo(stationCoords.get(1));
		try {
			user1.getRide().start(); 
			user1.getRide().join();
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");

		System.out.println("Now to Station 9 !");
		user1.goTo(stationCoords.get(9));
		try {
			user1.getRide().start(); 
			user1.getRide().join(); 
		} catch (NoRideException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		System.out.println("After "+ user1.getRides().size()+ " rides, " +user1.getName()
				+ " has a time credit of : "+user1.getTimeCredit()+".");
		
		System.out.println(stats.visit(n));
		System.out.println(stats.visit(user1));
		System.out.println(stats.visit(allStations.get(4)));
		System.out.println(stats.visit(allStations.get(2)));
		
	}

}
