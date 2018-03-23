package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {

	@Test
	public void testHashCode() {
		GPScoordinates location1;
		GPScoordinates location2;
		try {
			location1 = new GPScoordinates(10,10);
			location2 = new GPScoordinates(20,20);
			Station station1 = new Station(10,location1);
			Station station2 = new Station(10,location2);
			assertFalse("Test pour Hashcode()", station1.hashCode()==station2.hashCode());
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStationIntGPScoordinates() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test pour constructeur à 2 paramètre", station1 instanceof Station && station1.getStationID()==1 && station1.getNumberOfSlots()==10 && station1.getLocation()==location1 && station1.getParkingSlots().size()==10);
			for (int i=0;i<10;i++) {
				assertTrue("Test de la génération des parkingSlots", station1.getParkingSlots().get(i) instanceof ParkingSlot && station1.getParkingSlots().get(i).getParkingSlotID()==i);
			}
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStationIntGPScoordinatesDouble() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1,8,0,25);
			assertTrue("Test pour constructeur à 4 paramètre", station1 instanceof Station && station1.getStationID()==1 && station1.getNumberOfSlots()==10 && station1.getLocation()==location1 && station1.getParkingSlots().size()==10);
			for (int i=0;i<10;i++) {
				assertTrue("Test de la génération des parkingSlots", station1.getParkingSlots().get(i) instanceof ParkingSlot && station1.getParkingSlots().get(i).getParkingSlotID()==i);
			}
			for (int j=0; j<8; j++) {
				assertTrue("Vérification de la présence des Bikes", station1.getParkingSlots().get(j).isBike());
			}
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testCountUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddParkingSlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFreeSlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFreeSlot() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAvailableBicycle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAvailableBicycle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParkingCounter() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOnline() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStationID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParkingSlots() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			String str = ("This is station number " + station1.getStationID() + ", containing " + station1.getParkingSlots().size() +" parking slots.");
			str += "\n";
			str += ("This station is located at : "+station1.getLocation().toString() +".");
			assertTrue("Test de toString", station1.toString().equals(str));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsObject() {
		GPScoordinates location1;
		GPScoordinates location2;
		try {
			location1 = new GPScoordinates(10,10);
			location2 = new GPScoordinates(20,20);
			Station station1 = new Station(10,location1);
			Station station2 = new Station(10,location2);
			Station station3 = new Station(10, location1);
			assertFalse("Test de equals pour deux stations à des emplacements différents", station1.equals(station2));
			assertTrue("Test de reflexivité", station1.equals(station1));
			assertFalse("Test de equals pour deux stations distinctes identique", station1.equals(station3));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}	
}


