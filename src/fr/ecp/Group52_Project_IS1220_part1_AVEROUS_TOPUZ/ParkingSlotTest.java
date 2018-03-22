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
	
	@Test
	public void testGetBike() {
		GPScoordinates location;
		ElectricalBike elecBike = (ElectricalBike) BikeFactory.create(BikesType.Electrical);
		MechanicalBike mechaBike = (MechanicalBike) BikeFactory.create(BikesType.Mechanical);
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			ps_1.setBike(elecBike);
			assertTrue("Test de SetBike pour une electrical bike", ps_1.getBike()==elecBike);
			ps_1.setBike(mechaBike);
			assertTrue("Test de SetBike pour une mechanical bike", ps_1.getBike()==mechaBike);			
		} catch (OutOfBoundsException | EmptySlotException e){
			e.printStackTrace();
		}
	}
	
	@Test (expected = EmptySlotException.class)
	public void testGetBike_exception() throws EmptySlotException {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			ps_1.getBike();		
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSetState() {
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
	public void testSetBike() {
		GPScoordinates location;
		ElectricalBike elecBike = (ElectricalBike) BikeFactory.create(BikesType.Electrical);
		MechanicalBike mechaBike = (MechanicalBike) BikeFactory.create(BikesType.Mechanical);
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			ps_1.setBike(elecBike);
			assertTrue("Test de SetBike pour une electrical bike", ps_1.getBike()==elecBike);
			ps_1.setBike(mechaBike);
			assertTrue("Test de SetBike pour une mechanical bike", ps_1.getBike()==mechaBike);			
		} catch (OutOfBoundsException | EmptySlotException e){
			e.printStackTrace();
		}		
	}

	@Test
	public void testToString() {
		GPScoordinates location;
		try {
			location = new GPScoordinates(40, 50);
			Station station = new Station(10, location);
			ParkingSlot ps_1 = new ParkingSlot(station);	
			String str = ("This is parking slot number " + ps_1.getParkingSlotID() + ", situated within station number " + ps_1.getStation().getStationID() +".");
			str += "\n";
			str += ("This parking slot is located at : "+ps_1.getStation().getLocation().toString()+".");
			assertTrue("Test de toString", ps_1.toString().equals(str));
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}

	@Test
	public void testEqualsObject() {
		GPScoordinates location1;
		GPScoordinates location2;
		try {
			location1 = new GPScoordinates(40, 50);
			location2 = new GPScoordinates(10,20);
			Station station1 = new Station(10, location1);
			ParkingSlot ps_1 = new ParkingSlot(station1);	
			ParkingSlot ps_2 = new ParkingSlot(station1);
			Station station2 = new Station(10, location2);
			ParkingSlot ps_3 = new ParkingSlot(station2);
			assertFalse("Test de equals pour deux parking slots d'une même station", ps_1.equals(ps_2));
			assertTrue("Test de reflexivité", ps_1.equals(ps_1));
			assertFalse("Test de equals pour deux parking slots de deux stations différents", ps_1.equals(ps_3));
		} catch (OutOfBoundsException e){
			e.printStackTrace();
		}	
	}

}
