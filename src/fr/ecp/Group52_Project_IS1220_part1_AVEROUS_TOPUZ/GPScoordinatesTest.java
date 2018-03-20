package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.*;

import org.junit.Test;

public class GPScoordinatesTest {


	@Test
	public void testGPScoordinatesDoubleDouble_basic_positive_positive() {
		GPScoordinates gps = new GPScoordinates(10, 10);
		assertTrue("Test du constructeur avec les doubles", gps instanceof GPScoordinates && gps.getLatitude()==10 && gps.getLongitude()==10);
	}
	
	@Test
	public void testGPScoordinatesDoubleDouble_basic_positive_negative() {
		GPScoordinates gps = new GPScoordinates(10, -10);
		assertTrue("Test du constructeur avec les doubles", gps instanceof GPScoordinates && gps.getLatitude()==10 && gps.getLongitude()==10);
	}
	
	@Test
	public void testGPScoordinatesDoubleDouble_basic_negative_positive() {
		GPScoordinates gps = new GPScoordinates(-10, 10);
		assertTrue("Test du constructeur avec les doubles", gps instanceof GPScoordinates && gps.getLatitude()==10 && gps.getLongitude()==10);
	}
	
	@Test
	public void testGPScoordinatesDoubleDouble_basic_negative_negative() {
		GPScoordinates gps = new GPScoordinates(-10, -10);
		assertTrue("Test du constructeur avec les doubles", gps instanceof GPScoordinates && gps.getLatitude()==10 && gps.getLongitude()==10);
	}
	
	@Test (expected = OutOfBoundException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_latitude_top() {
		GPScoordinates gps = new GPScoordinates(91, 10);
	}
	
	@Test (expected = OutOfBoundException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_latitude_down() {
		GPScoordinates gps = new GPScoordinates(-91, 10);
	}
	
	@Test (expected = OutOfBoundException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_longitudee_top() {
		GPScoordinates gps = new GPScoordinates(10, 181);
	}
	
	@Test (expected = OutOfBoundException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_longitude_down() {
		GPScoordinates gps = new GPScoordinates(10, -181);
	}
	
	
	
	@Test
	public void testGPScoordinatesStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDistanceTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testDistanceAB() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLatitude() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLongitude() {
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
	
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

}
