package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network;

/**
 * The NoCard class extends the abstract class Card 
 * and defines the type of card corresponding to no card at all.
 * Therefore in that particular case, the card hasn't got a card number and doesn't increment the card counter.
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

public class NoCard extends Card {

	/**
	 * A constructor creating a NoCard card object, assigning it a user, a unique card number and incrementing the card counter
	 * @param user A User object defining the user of the card
	 */
	public NoCard(User user) {
		super(user);
		this.type=CardTypes.NoCard;
		this.cardNumber= -1;
	}

	@Override
	public double pay(BikesType type, double rideDuration) {
		double finalPrice = 0;
		if(type == BikesType.Mechanical) {
			finalPrice =  rideDuration/60;
		}
		else {
			finalPrice = rideDuration/30;
		}
		return finalPrice;
	}
	
	@Override
	public String toString() {
		return (this.user.getName()+" doesn't have a card.\n");
	}

}
