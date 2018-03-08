package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class Vmax extends Card {

	public Vmax(User user) {
		super(user);
		Card.counter+=1;
		this.cardNumber=Card.counter;
		this.type=CardTypes.Vmax;
	}

}
