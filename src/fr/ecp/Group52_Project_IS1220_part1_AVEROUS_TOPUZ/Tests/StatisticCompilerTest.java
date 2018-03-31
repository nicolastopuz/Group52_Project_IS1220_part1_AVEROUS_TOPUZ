package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.*;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;

public class StatisticCompilerTest {
	
	Network n = this.setNetwork();
	
	public Network setNetwork() {
		try {
			return( new Network(10,10,0.7));
		} catch(InvalidProportionsException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void testStatisticCompiler() {
		StatisticCompiler statos1 = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		assertTrue("Test du constructeur pour MostUsed", statos1.getSortingMethod()==NetworkStatisticsSortingMethods.MostUsed);
		StatisticCompiler statos2 = new StatisticCompiler(NetworkStatisticsSortingMethods.LeastOccupied);
		assertTrue("Test du constructeur pour LeastOccupied", statos2.getSortingMethod()==NetworkStatisticsSortingMethods.LeastOccupied);
	}

	@Test
	public void testGetSortingMethod() {
		StatisticCompiler statos = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		assertTrue("Test de getSortingMethod pour MostUsed", statos.getSortingMethod()==NetworkStatisticsSortingMethods.MostUsed);
	}

	@Test
	public void testSetSortingMethod() {
		StatisticCompiler statos = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
		statos.setSortingMethod(NetworkStatisticsSortingMethods.LeastOccupied);
		assertTrue("Test de setSortingMethod pour changer de MostUsed a LeastOccupied", statos.getSortingMethod()==NetworkStatisticsSortingMethods.LeastOccupied);
	}

	@Test
	public void testVisitNetwork() {
		String result = new String();
		
		try {
			
			StatisticCompiler statos = new StatisticCompiler(NetworkStatisticsSortingMethods.MostUsed);
			User u1 = n.createUser("Testeur1");
			User u2 = n.createUser("Testeur2");
			u1.goTo(GPScoordinates.randomLocation());
			u1.takeBike(u1.getRide().getDepartureStation());
			u1.dropBike(u1.getRide().getArrivalStation());
			
			u2.goTo(GPScoordinates.randomLocation());
			u2.dropBike(u1.getRide().getDepartureStation());
			u2.takeBike(u1.getRide().getArrivalStation());
			
			u1.goTo(GPScoordinates.randomLocation());
			u1.takeBike(u1.getRide().getDepartureStation());
			u1.dropBike(u1.getRide().getArrivalStation());
			
			u2.goTo(GPScoordinates.randomLocation());
			u2.dropBike(u1.getRide().getDepartureStation());
			u2.takeBike(u1.getRide().getArrivalStation());
			
			u1.goTo(GPScoordinates.randomLocation());
			u1.takeBike(u1.getRide().getDepartureStation());
			u1.dropBike(u1.getRide().getArrivalStation());
			
			u2.goTo(GPScoordinates.randomLocation());
			u2.dropBike(u1.getRide().getDepartureStation());
			u2.takeBike(u1.getRide().getArrivalStation());
			
			u1.goTo(GPScoordinates.randomLocation());
			u1.takeBike(u1.getRide().getDepartureStation());
			u1.dropBike(u1.getRide().getArrivalStation());
			
			u2.goTo(GPScoordinates.randomLocation());
			u2.dropBike(u1.getRide().getDepartureStation());
			u2.takeBike(u1.getRide().getArrivalStation());
			
			result = statos.visit(n);
			
		} catch (NoRideException e) {
			e.printStackTrace();
		}
		
		System.out.println(n.getStationList().get(4));
		System.out.println();
		System.out.println((n.getStationList().get(4).getNumberOfRent()+n.getStationList().get(4).getNumberOfReturn())+ " is the activity of the station.");
		System.out.println();
		System.out.println(result);
	}

	@Test
	public void testVisitStation() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testVisitParkingSlot() {
		fail("Not yet implemented");
	}

}
