package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The NoCard class extends the abstract class Card 
 * and defines the type of card corresponding to no card at all.
 * 
 * @see Card
 * @see CardTypes
 * @see User
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 *
 */

public class Vmax extends Card implements CardVisitor {
	
	/**
	 * A constructor creating a Vmax card object, assigning it a user, a unique card number and incrementing the card counter
	 * @param user A User object defining the user of the card
	 */
	public Vmax(User user) {
		super(user);
		Card.cardCounter+=1;
		this.cardNumber=Card.cardCounter;
		this.type=CardTypes.Vmax;
	}
	
	@Override
	public double pay(BikesType type, double rideDuration) {
		double finalPrice = 0;
		int hourDuration = (int) rideDuration/60;
		if (hourDuration > 1) {
			finalPrice = rideDuration/60 - 1;
		}
		return finalPrice;
	}
}
