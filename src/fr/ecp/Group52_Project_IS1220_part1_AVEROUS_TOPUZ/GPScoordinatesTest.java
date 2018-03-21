package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The JUnit test class testing the GPScoordinates class
 * @see GPScoordinates
 * @see OutOfBoundsException
 * @see BadCoordinatesSyntaxException
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GPScoordinatesTest {
	
	///First let's test the constructor using doubles as parameters 

	@Test
	public void testGPScoordinatesDoubleDouble() {
		GPScoordinates gps1;
		GPScoordinates gps2;
		GPScoordinates gps3;
		GPScoordinates gps4;
		try {
			gps1 = new GPScoordinates(10, 10);
			assertTrue("Test du constructeur avec les doubles positifs", gps1 instanceof GPScoordinates && gps1.getLatitude()==10 && gps1.getLongitude()==10);
			gps2 = new GPScoordinates(10, -10);
			assertTrue("Test du constructeur avec les doublespositif et négatif", gps2 instanceof GPScoordinates && gps2.getLatitude()==10 && gps2.getLongitude()==-10);
			gps3 = new GPScoordinates(-10, 10);
			assertTrue("Test du constructeur avec les doubles négatif et positif", gps3 instanceof GPScoordinates && gps3.getLatitude()==-10 && gps3.getLongitude()==10);
			gps4 = new GPScoordinates(-10, -10);
			assertTrue("Test du constructeur avec les doubles négatifs", gps4 instanceof GPScoordinates && gps4.getLatitude()==-10 && gps4.getLongitude()==-10);
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}	
	}
	


	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_latitude_top() throws OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates(91, 10);
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_latitude_down() throws OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates(-91, 10);
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_longitudee_top() throws OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates(10, 181);
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesDoubleDouble_exception_on_longitude_down() throws OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates(10, -181);
	}

	///Now let's test the constructor using strings as parameters 
	
	@Test
	public void testGPScoordinatesStringString_basic_positive_positive() {
		GPScoordinates gps1;
		GPScoordinates gps2;
		GPScoordinates gps3;
		GPScoordinates gps4;
		try {
			gps1 = new GPScoordinates("10°0'0,0","15°0'0,0");
			assertTrue("Test du constructeur avec les String positifs et positifs", gps1 instanceof GPScoordinates && gps1.latitude==10 && gps1.getLongitude()==15);
			gps2 = new GPScoordinates("10°0'0,0","-15°0'0,0");
			assertTrue("Test du constructeur avec les String positifs et positifs", gps2 instanceof GPScoordinates && gps2.latitude==10 && gps2.getLongitude()==-15);
			gps3 = new GPScoordinates("-10°0'0,0","15°0'0,0");
			assertTrue("Test du constructeur avec les String positifs et positifs", gps3 instanceof GPScoordinates && gps3.latitude==-10 && gps3.getLongitude()==15);
			gps4 = new GPScoordinates("-10°0'0,0","-15°0'0,0");
			assertTrue("Test du constructeur avec les String positifs et positifs", gps4 instanceof GPScoordinates && gps4.latitude==-10 && gps4.getLongitude()==-15 );
		} catch (BadCoordinatesSyntaxException e) {
			e.printStackTrace();
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		} catch (InvalidCoordinatesException e) {
			e.printStackTrace();
		}
	}



	@Test (expected = BadCoordinatesSyntaxException.class)
	public void testGPScoordinatesStringString_wrongStrignFormat_tooShort() throws BadCoordinatesSyntaxException, OutOfBoundsException {
			GPScoordinates gps = new GPScoordinates("-10°","-15°0'0,0");
	}
	
	@Test (expected = BadCoordinatesSyntaxException.class)
	public void testGPScoordinatesStringString_wrongStrignFormat_tooLong1() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("-10°75'0,0","-15°0'0,0");
	}
	
	@Test (expected = BadCoordinatesSyntaxException.class)
	public void testGPScoordinatesStringString_wrongStrignFormat_tooLong2() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("-10°0'72,0","-15°0'0,0");
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesStringString_out_of_bond_latitude_top() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("100°0'0,0","-15°0'0,0");
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesStringString_out_of_bond_latitude_down() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("-100°0'0,0","-15°0'0,0");
	}
	
	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesStringString_out_of_bond_longitude_top() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("10°0'0,0","190°0'0,0");
	}

	@Test (expected = OutOfBoundsException.class)
	public void testGPScoordinatesStringString_out_of_bond_longitude_down() throws BadCoordinatesSyntaxException, OutOfBoundsException {
		GPScoordinates gps = new GPScoordinates("10°0'0,0","-190°0'0,0");
	}

	///Now let's test the basic methods (getters and object methods)
	
	@Test
	public void testGetLatitude() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			GPScoordinates gps2 = new GPScoordinates(90,0);
			GPScoordinates gps3 = new GPScoordinates(-90,0);
			GPScoordinates gps4 = new GPScoordinates(33,0);
			GPScoordinates gps5 = new GPScoordinates(-66,0);
			assertTrue("Test de getLatitude pour 0", gps1.getLatitude()==0);
			assertTrue("Test de getLatitude pour valeur max", gps2.getLatitude()==90);
			assertTrue("Test de getLatitude pour valeur min", gps3.getLatitude()==-90);
			assertTrue("Test de getLatitude pour valeur quelconque positive", gps4.getLatitude()==33);
			assertTrue("Test de getLatitude pour valeur quelconque negative", gps5.getLatitude()==-66);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLongitude() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			GPScoordinates gps2 = new GPScoordinates(0,180);
			GPScoordinates gps3 = new GPScoordinates(0,-180);
			GPScoordinates gps4 = new GPScoordinates(0,111);
			GPScoordinates gps5 = new GPScoordinates(0,-45);
			assertTrue("Test de getLongitude pour 0", gps1.getLongitude()==0);
			assertTrue("Test de getLongitude pour valeur max", gps2.getLongitude()==180);
			assertTrue("Test de getLongitude pour valeur min", gps3.getLongitude()==-180);
			assertTrue("Test de getLongitude pour valeur quelconque positive", gps4.getLongitude()==111);
			assertTrue("Test de getLongitude pour valeur quelconque negative", gps5.getLongitude()==-45);	
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToString() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			assertTrue("Test de la méthode ToString", gps1.toString().equals("(0,0)"));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualsObject() {
		try {
			GPScoordinates gps1 = new GPScoordinates(10,10);
			GPScoordinates gps2 = new GPScoordinates(10,10);		
			GPScoordinates gps3 = new GPScoordinates(-10,-10);
			GPScoordinates gps4 = new GPScoordinates("10°0'0,0","10°0'0,0");
			GPScoordinates gps5 = new GPScoordinates(0,180);
			GPScoordinates gps6 = new GPScoordinates(0,-180);
			assertTrue("Test de equals dans un cas d'égalité avec constructeur numérique", gps1.equals(gps2));
			assertFalse("Test de equals dans un cas d'inégalité avec constructeur numérique", gps1.equals(gps3));
			assertTrue("Test de equals dans un cas d'égalité avec constructeur String", gps1.equals(gps4));
			assertFalse("Test de equals dans un cas d'inégalité avec un objet autre", gps1.equals(12));
			assertTrue("Test de equals : vérification du modulo", gps5.equals(gps6));
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		} catch(BadCoordinatesSyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHashCode() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			GPScoordinates gps2 = new GPScoordinates(0,180);	
			assertTrue("Test de la méthode hashCode",gps1.hashCode()!=gps2.hashCode());
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	///Finally let's test the distance calculating methods
	
	@Test
	public void testDistanceTo() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			GPScoordinates gps2 = new GPScoordinates(90,0);
			GPScoordinates gps3 = new GPScoordinates(0,0);
			GPScoordinates gps4 = new GPScoordinates(0,180);
			GPScoordinates gps5 = new GPScoordinates(0,-180);
			assertTrue("Distance nulle", gps1.distanceTo(gps3)==0);
			assertTrue("Distance à soi-même", gps1.distanceTo(gps1)==0);
			assertTrue("Distance triviale", gps1.distanceTo(gps2)==0.5*Math.PI*6371);
			assertTrue("Distance à soi-même : vérification du modulo", gps4.distanceTo(gps5)==0);
			GPScoordinates gps6 = new GPScoordinates(12,-7);
			GPScoordinates gps7 = new GPScoordinates(-74,120);
			double dLat = (gps6.getLatitude()-gps7.getLatitude())*(Math.PI/180); //Angle between latitudes of points A and B, converted to radians.
			double dLon = (gps6.getLongitude()-gps7.getLongitude())*(Math.PI/180); //Angle between longitudes of points A and B, converted to radians.
			
			double a = 
					Math.sin(dLat/2) * Math.sin(dLat/2) +
					Math.cos(gps6.getLatitude()*Math.PI/180) * Math.cos(gps7.getLatitude()*Math.PI/180) *
					Math.sin(dLon/2) * Math.sin(dLon/2)
					;
			
			double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double distance = 6371 * b;
			assertTrue("Distance complexe entre deux points aléatoires", gps6.distanceTo(gps7)==distance);
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDistanceAB() {
		try {
			GPScoordinates gps1 = new GPScoordinates(0,0);
			GPScoordinates gps2 = new GPScoordinates(90,0);
			GPScoordinates gps3 = new GPScoordinates(0,0);
			GPScoordinates gps4 = new GPScoordinates(12,-7);
			GPScoordinates gps5 = new GPScoordinates(-74,120);
			assertTrue("Test distance nulle", GPScoordinates.distanceAB(gps1, gps3)==0);
			assertTrue("Test distance à soi-même", GPScoordinates.distanceAB(gps1, gps1)==0);
			assertTrue("Test distance triviale", GPScoordinates.distanceAB(gps1, gps2)==0.5*Math.PI*6371);
			double dLat = (gps4.getLatitude()-gps5.getLatitude())*(Math.PI/180); //Angle between latitudes of points A and B, converted to radians.
			double dLon = (gps4.getLongitude()-gps5.getLongitude())*(Math.PI/180); //Angle between longitudes of points A and B, converted to radians.
			
			double a = 
					Math.sin(dLat/2) * Math.sin(dLat/2) +
					Math.cos(gps4.getLatitude()*Math.PI/180) * Math.cos(gps5.getLatitude()*Math.PI/180) *
					Math.sin(dLon/2) * Math.sin(dLon/2)
					;
			
			double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double distance = 6371 * b;
			assertTrue("Test distance complexe entre deux points aléatoires", GPScoordinates.distanceAB(gps4, gps5)==distance);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
