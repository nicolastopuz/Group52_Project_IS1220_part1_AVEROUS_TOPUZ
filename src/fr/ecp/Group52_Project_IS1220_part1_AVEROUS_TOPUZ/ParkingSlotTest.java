package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkingSlotTest {
	

	@Test
	public void testHashCode() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);
			ParkingSlot ps_2 = new ParkingSlot(station);
			assertTrue("Test pour le hashcode", ps_1.hashCode()!=ps_2.hashCode());
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testParkingSlot() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);
			assertTrue("Test du constructeur", ps_1 instanceof ParkingSlot && ps_1.getState()==ParkingSlotState.free && ps_1.getStation()==station && ps_1.getParkingSlotID()==10 && ps_1.getStation().getParkingCounter()==11);
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}	}

	@Test
	public void testIsFree() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			assertTrue("Test de isFree dans le cas positif", ps_1.isFree());
			ps_1.setState(ParkingSlotState.outOfOrder);
			ParkingSlot ps_2 = new ParkingSlot(station);
			ps_2.setState(ParkingSlotState.taken);
			assertFalse("Test de isFree dans les cas négatifs", ps_1.isFree() | ps_2.isFree());
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}
	}

	
	@Test
	public void testIsBike() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			ParkingSlot ps_2 = new ParkingSlot(station);
			ps_1.setState(ParkingSlotState.outOfOrder);
			assertFalse("Test de isBike dans les cas négatifs", ps_1.isBike()|ps_2.isBike());
			ps_1.setState(ParkingSlotState.taken);
			assertTrue("Test de isBike dans le cas positif", ps_1.isBike());
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}

	
	@Test
	public void testGetState() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			assertTrue("Test de getState pour free", ps_1.getState()==ParkingSlotState.free);
			ps_1.setState(ParkingSlotState.outOfOrder);
			assertTrue("Test de getState pour out of order", ps_1.getState()==ParkingSlotState.outOfOrder);
			ps_1.setState(ParkingSlotState.taken);
			assertTrue("Test de getState pour taken", ps_1.getState()==ParkingSlotState.taken);
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetParkingSlotID() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			assertTrue("Test de getParkingSlotID ", ps_1.getParkingSlotID()==10);
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGetStation() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			assertTrue("Test de getStation", ps_1.getStation()==station);
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}
	/**
	@Test
	public void testGetBike() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBike() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}
*/
}
