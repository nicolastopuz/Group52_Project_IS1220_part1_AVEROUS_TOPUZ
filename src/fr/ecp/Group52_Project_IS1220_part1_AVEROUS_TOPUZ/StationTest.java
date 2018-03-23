package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;


import static org.junit.Assert.*;

import java.util.ArrayList;

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
			assertTrue("Test pour constructeur à 2 paramètre", station1 instanceof Station && station1.getStationID()==0 && station1.getNumberOfSlots()==10 && station1.getLocation()==location1 && station1.getParkingSlots().size()==10);
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
			Station station1 = new Station(10,location1,8,(float) 0.25);
			assertTrue("Test pour constructeur à 4 paramètre", station1 instanceof Station && station1.getStationID()==1 && station1.getNumberOfSlots()==10 && station1.getLocation()==location1 && station1.getParkingSlots().size()==10);
			for (int i=0;i<10;i++) {
				assertTrue("Test de la génération des parkingSlots", station1.getParkingSlots().get(i) instanceof ParkingSlot && station1.getParkingSlots().get(i).getParkingSlotID()==i);
			}
			for (int j=0; j<8; j++) {
				assertTrue("Vérification de la présence des Bikes", station1.getParkingSlots().get(j).isBike());
			}
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch (MoreBikesThanSlotsException e) {
			e.printStackTrace();
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}
	
	@Test (expected = MoreBikesThanSlotsException.class)
	public void testStationIntGPScoordinatesDouble_exception_too_much_bikes() throws MoreBikesThanSlotsException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1,11,(float) 0.25);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}
	
	@Test (expected = MoreBikesThanSlotsException.class)
	public void testStationIntGPScoordinatesDouble_exception_negative_bikes() throws MoreBikesThanSlotsException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1,-1,(float) 0.25);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch (InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}
	
	@Test (expected = InvalidProportionsException.class)
	public void testStationIntGPScoordinatesDouble_exception_proportion_top() throws InvalidProportionsException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1,5,(float) 2);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch (MoreBikesThanSlotsException e) {
			e.printStackTrace();
		}	
	}

	@Test (expected = InvalidProportionsException.class)
	public void testStationIntGPScoordinatesDouble_exception_proportion_under() throws InvalidProportionsException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1,5,(float) -1);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch (MoreBikesThanSlotsException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testCountUp() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			station1.countUp();
			assertTrue("Test de CountUp()", station1.getParkingCounter()==11);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testAddParkingSlot() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			ParkingSlot ps = new ParkingSlot(station1);
			station1.addParkingSlot(ps);
			assertTrue("Test de addParkingSlot", station1.getParkingSlots().get(10).equals(ps));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testIsFreeSlot() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de isFreeSlot cas positif simple", station1.isFreeSlot());
			Station station2 = new Station(10,location1, 3, (float)1);
			assertTrue("Test de isFreeSlot cas positif complexe", station2.isFreeSlot());
			Station station3 = new Station(10, location1, 10, (float)1);
			assertFalse("Test de isFreeSlot cas négatif", station3.isFreeSlot());
		} catch(OutOfBoundsException | MoreBikesThanSlotsException | InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testGetFreeSlot() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de GetFreeSlot", station1.getFreeSlot()==station1.getParkingSlots().get(0));
		} catch(OutOfBoundsException | NoFreeSlotException e) {
			e.printStackTrace();
		}	
	}
	
	@Test (expected = NoFreeSlotException.class)
	public void testGetFreeSlot_exception() throws NoFreeSlotException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1, 10, (float) 1);
		} catch(OutOfBoundsException | MoreBikesThanSlotsException | InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}
	

	@Test
	public void testIsAvailableBicycle() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1, 10, (float) 1);
			Station station2 = new Station(10, location1);
			assertTrue("Test positif", station1.isAvailableBicycle());
			assertFalse("Test négatif", station2.isAvailableBicycle());
		} catch(OutOfBoundsException | MoreBikesThanSlotsException | InvalidProportionsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testGetAvailableBicycle() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1, 10, (float) 1);
			assertTrue("Test de GetAvailableBike", station1.getAvailableBicycle()==station1.getParkingSlots().get(0));
		} catch(OutOfBoundsException | MoreBikesThanSlotsException | InvalidProportionsException | NoAvailableBikeException e) {
			e.printStackTrace();
		}	
	}
	
	@Test (expected = NoAvailableBikeException.class)
	public void testGetAvailableBicycle_exception() throws NoAvailableBikeException {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			station1.getAvailableBicycle();
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testGetParkingCounter() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de GetParkingCounter", station1.getParkingCounter()==10);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLocation() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de getLocation", station1.getLocation()==location1);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIsOnline() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de isOnline cas positif", station1.isOnline);
			station1.setState(false);
			assertFalse("Test de isOnlince cas négatif", station1.isOnline);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetStationID() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			assertTrue("Test de GetStationID", station1.getStationID()==0);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetParkingSlots() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			ArrayList<ParkingSlot> res = new ArrayList<ParkingSlot>();
			for (int i=0;i<10;i++) {
				res.add(station1.getParkingSlots().get(i));
			}
			assertTrue("Test de GetParkingSlots", station1.getParkingSlots().equals(res));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetLocation() {
		GPScoordinates location1;
		GPScoordinates location2;
		try {
			location1 = new GPScoordinates(10,10);
			location2 = new GPScoordinates(20, 20);
			Station station1 = new Station(10,location1);
			station1.setLocation(location2);
			assertTrue("Test de SetLocation", station1.getLocation()==location2);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}	}

	@Test
	public void testSetState() {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			station1.setState(false);
			assertFalse("Test de isOnlince cas négatif", station1.isOnline);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
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


