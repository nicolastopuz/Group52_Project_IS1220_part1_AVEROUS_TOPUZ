package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.*;

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
			assertTrue("test pour le hashcode", ps_1.hashCode()!=ps_2.hashCode());
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	@Test
	public void testParkingSlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFree() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsBike() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParkingSlotID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStation() {
		fail("Not yet implemented");
	}

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
