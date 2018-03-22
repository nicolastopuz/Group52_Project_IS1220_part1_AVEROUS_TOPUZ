package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	User u = new User("Patrick");
	
	@Test
	public void testHashCode_SameCardType() {
		assertTrue("Test de la fonction HashCode sur 2 cartes du m�me type", CardFactory.create(u, CardTypes.Vlibre).hashCode() != CardFactory.create(u, CardTypes.Vlibre).hashCode());
	}	
	
	@Test
	public void testHashCode_DifferentCardType() {
		assertTrue("Test de la fonction HashCode sur 2 cartes de type diff�rent", CardFactory.create(u, CardTypes.NoCard).hashCode() != CardFactory.create(u, CardTypes.Vmax).hashCode());
	}

	@Test
	public void testCardFactory() {
		assertTrue("Test du constructeur par factory pour Vmax", CardFactory.create(u, CardTypes.Vmax) instanceof Vmax);
		assertTrue("Test du constructeur par factory pour Vlibre", CardFactory.create(u, CardTypes.Vlibre) instanceof Vlibre);
		assertTrue("Test du constructeur par factory pour NoCard", CardFactory.create(u, CardTypes.NoCard) instanceof NoCard);
		
	}

	@Test
	public void testGetCardCounter() {
		double c = Card.getCardCounter();
		Vlibre vl = (Vlibre) CardFactory.create(u, CardTypes.Vlibre);
		assertTrue("Test de la fonction getCardCounter", c == Card.getCardCounter()-1);
	}

	@Test
	public void testGetCardNumber() {
		double c = Card.getCardCounter()+1;
		Vlibre vl = (Vlibre) CardFactory.create(u, CardTypes.Vlibre);
		assertTrue("Test de la fonction getCardCounter", c == vl.getCardNumber());
	}

	@Test
	public void testGetUser() {
		assertTrue("Test de getUser", CardFactory.create(u, CardTypes.NoCard).getUser().equals(u));
	}

	@Test
	public void testGetType() {
		assertTrue("Test de getType sur Vmax", CardFactory.create(u, CardTypes.Vmax).getType()==CardTypes.Vmax);
		assertTrue("Test de getType sur Vlibre", CardFactory.create(u, CardTypes.Vlibre).getType()==CardTypes.Vlibre);
		assertTrue("Test de getType sur NoCard", CardFactory.create(u, CardTypes.NoCard).getType()==CardTypes.NoCard);
	}

	@Test
	public void testToString() {
		Card CC = CardFactory.create(u, CardTypes.Vlibre);
		assertTrue("Test of toString for actual card", CC.toString().equals("This card number "+CC.getCardNumber()+" is a "+CC.getType()+" and belongs to "+CC.getUser().getName()+".\n"));
		Card noCard = CardFactory.create(u, CardTypes.NoCard);
		assertTrue("Test of toString for no card", noCard.toString().equals(noCard.getUser().getName()+" doesn't have a card.\n"));
		
	}

	@Test
	public void testEqualsObject() {
		Card c1 = CardFactory.create(u, CardTypes.NoCard);
		Card c2 = CardFactory.create(u, CardTypes.Vlibre);
		Card c3 = CardFactory.create(u, CardTypes.Vmax);
		Card c4 = CardFactory.create(u, CardTypes.Vlibre);
		assertTrue("Test de equals on NoCard & Vlibre", !c1.equals(c2));
		assertTrue("Test de equals on NoCard & Vmax", !c1.equals(c3));
		assertTrue("Test de equals on Vmax & Vlibre", !c3.equals(c2));
		assertTrue("Test de equals on same type but different object", !c2.equals(c4));
		
		assertTrue("Test de la r�flexivit� de la fonction", c1.equals(c2) == c2.equals(c1));
		assertTrue("Test d'une �galit�", c1.equals(c1));
	}

}
