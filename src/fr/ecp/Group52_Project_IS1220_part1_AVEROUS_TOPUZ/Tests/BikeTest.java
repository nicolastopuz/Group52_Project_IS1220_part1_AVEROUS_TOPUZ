package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Bike;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.BikeFactory;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.BikesType;

public class BikeTest {

	@Test
	public void testHashCode() {
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		Bike b2 = BikeFactory.create(BikesType.Electrical);
		Bike b3 = BikeFactory.create(BikesType.Mechanical);
		assertTrue("Test du hashCode pour 2 v�los du m�me type", b1.hashCode()!=b2.hashCode());
		assertTrue("Test du hashCode pour 2 v�los de types diff�rents", b1.hashCode()!=b3.hashCode());
		assertTrue("Test du hashCode pour un seul et m�me objet", b1.hashCode()==b1.hashCode());
	}

	@Test
	public void testBike() {
		double b = Bike.getBikeCounter();
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		assertTrue("Test du constructeur de Bike", b+1==Bike.getBikeCounter() && b1.getNumericalId()==b+1);
	}

	@Test
	public void testGetBikeCounter() {
		double b = Bike.getBikeCounter();
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		assertTrue("Test du getBikeCounter", b+1==Bike.getBikeCounter());
	}

	@Test
	public void testGetNumericalId() {
		double b = Bike.getBikeCounter();
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		assertTrue("Test du getNumericalId", b1.getNumericalId()==b+1);
	}

	@Test
	public void testGetType() {
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		assertTrue("Test du getType pour un velo electrique", b1.getType()==BikesType.Electrical);
		Bike b2 = BikeFactory.create(BikesType.Mechanical);
		assertTrue("Test du getType pour un velo mecanique", b2.getType()==BikesType.Mechanical);
	}

	@Test
	public void testToString() {
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		assertTrue("Test du toString sur un velo electrique", b1.toString().equals("This bike is an electrical bike. Its unique numerical id is "+ b1.getNumericalId()));
		Bike b2 = BikeFactory.create(BikesType.Mechanical);
		assertTrue("Test du toString sur un velo mecanique", b2.toString().equals("This bike is a mechanical bike. Its unique numerical id is "+ b2.getNumericalId()));
	}

	@Test
	public void testEqualsObject() {
		Bike b1 = BikeFactory.create(BikesType.Electrical);
		Bike b2 = BikeFactory.create(BikesType.Electrical);
		Bike b3 = BikeFactory.create(BikesType.Mechanical);
		assertTrue("Test de equals sur 2 velos du meme type", !b1.equals(b2));
		assertTrue("Test de equals sur 2 velos de types diff�rents", !b1.equals(b3));
		assertTrue("Test de equals sur 2 velos identiques", b1.equals(b1));
		
	}

}
