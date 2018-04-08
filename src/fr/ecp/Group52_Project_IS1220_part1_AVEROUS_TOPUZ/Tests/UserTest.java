package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OutOfBoundsException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Card;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.CardFactory;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.CardTypes;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.ElectricalBiking;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Walking;

public class UserTest {

	@Test
	public void testHashCode() {
		User u1 = new User("Lotus");
		User u2 = new User("Ajax");
		assertTrue("Test du hashCode", u1.hashCode()!=u2.hashCode());
	}

	@Test
	public void testUserString() {
		User u1 = new User("Lotus");
		assertTrue("Test du constructeur simple", u1.getName()=="Lotus" && u1.getCard().getType()==CardTypes.NoCard);
	}

	@Test
	public void testUserStringCardTypes() {
		User u1 = new User("Lotus", CardTypes.Vlibre);
		assertTrue("Test du constructeur avec carte", u1.getName()=="Lotus" && u1.getCard().getType()==CardTypes.Vlibre);
	}

	@Test
	public void testGetName() {
		User u1 = new User("Lotus");
		assertTrue("Test de getName", u1.getName()=="Lotus");
	}

	@Test
	public void testGetUserCounter() {
		User u1 = new User("Lotus");
		double counter = User.getUserCounter();
		User u2 = new User("Ajax");
		assertTrue("Test du getter statique de userCounter", counter+1==User.getUserCounter());
	}

	@Test
	public void testGetNumericalId() {
		User u1 = new User("Lotus");
		User u2 = new User("Ajax");
		assertTrue("Test du getter de NumericalID", u1.getNumericalId()+1==u2.getNumericalId());
	}

	@Test
	public void testGetPosition() {
		try {
			GPScoordinates positionLotus = new GPScoordinates(0,0);
			GPScoordinates positionAjax = new GPScoordinates(0,30);
			User u1 = new User("Lotus");
			User u2 = new User("Ajax");
			u1.setPosition(positionLotus);
			u2.setPosition(positionAjax);
			assertTrue("Test du getter de la position 1", u1.getPosition()==positionLotus);
			assertTrue("Test du getter de la position 2", u2.getPosition()==positionAjax);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCard() {
		User u1 = new User("Lotus");
		User u2 = new User("Ajax", CardTypes.Vmax);
		Card c1 = CardFactory.create(u1, CardTypes.Vlibre);
		u1.setCard(c1);
		assertTrue("Test de getCard", u1.getCard()==c1);
		assertTrue("Test de getCard pour constructeur avec Carte", u2.getCard().getType()==CardTypes.Vmax);	
	}

	@Test
	public void testGetBehavior() {
		User u1 = new User("Lotus");
		u1.setBehavior(new Walking());
		assertTrue("Test du getBehaviour sur un walking", u1.getBehavior() instanceof Walking);
		u1.setBehavior(new ElectricalBiking());
		assertTrue("Test du getBehaviour sur une modif de behaviour", u1.getBehavior() instanceof ElectricalBiking);
	}

	@Test
	public void testSetName() {
		User u1 = new User("Lotus");
		u1.setName("Ajax");
		assertTrue("Test de setName", u1.getName()=="Ajax");
	}

	@Test
	public void testSetPosition() {
		try {
			GPScoordinates positionLotus = new GPScoordinates(0,0);
			GPScoordinates positionAjax = new GPScoordinates(0,30);
			User u1 = new User("Lotus");
			User u2 = new User("Ajax");
			u1.setPosition(positionLotus);
			u2.setPosition(positionAjax);
			assertTrue("Test du setter de la position 1", u1.getPosition()==positionLotus);
			assertTrue("Test du setter de la position 2", u2.getPosition()==positionAjax);
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetCard() {
		User u1 = new User("Lotus");
		Card c = CardFactory.create(u1, CardTypes.Vlibre);
		u1.setCard(c);
		assertTrue("Test du setCard", u1.getCard()==c);
	}

	@Test
	public void testSetBehavior() {
		User u1 = new User("Lotus");
		u1.setBehavior(new Walking());
		assertTrue("Test du getBehaviour sur un walking", u1.getBehavior() instanceof Walking);
		u1.setBehavior(new ElectricalBiking());
		assertTrue("Test du getBehaviour sur une modif de behaviour", u1.getBehavior() instanceof ElectricalBiking);
		
	}

	@Test
	public void testToString() {
		User u1 = new User("Lotus");
		User u2 = new User("Ajax", CardTypes.Vlibre);
		assertTrue("Test du toString pour un user sans carte", ("Lotus is user number "+u1.getNumericalId()+ " and hasn't got a card.\n").equals(u1.toString()));
		assertTrue("Test du toString pour un user avec une carte Vlibre", ("Ajax is user number "+u2.getNumericalId()+" and has a Vlibre card.\n").equals(u2.toString()));
	}

	@Test
	public void testEqualsObject() {
		User u1 = new User("Lotus");
		User u2 = new User("Lotus");
		assertTrue("Test de equals sur 2 users du m�me nom", !u1.equals(u2));
		User u3 = new User("Ajax");
		assertTrue("Test de equals sur 2 users de nom diff�rent", !u1.equals(u3));
	}
}
