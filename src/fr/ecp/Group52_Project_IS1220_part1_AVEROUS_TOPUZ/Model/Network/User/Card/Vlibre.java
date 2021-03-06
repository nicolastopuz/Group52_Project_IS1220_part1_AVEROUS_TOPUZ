package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.Card;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Bikes.BikesType;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.User.User;

/**
 * The Vlibre class extends the abstract class Card 
 * and defines the type of card corresponding to a Vlibre card.
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

public class Vlibre extends Card {

	/**
	 * A constructor creating a Vlibre card object, assigning it a user, a unique card number and incrementing the card counter
	 * @param user A User object defining the user of the card
	 */
	public Vlibre(User user) {
		super(user);
		Card.cardCounter+=1;
		this.cardNumber=Card.cardCounter;
		this.type=CardTypes.Vlibre;
	}
	
	@Override
	public double pay(BikesType type, double rideDuration) {
		double finalPrice = 0;
		if(type == BikesType.Mechanical) {
			int hourDuration = (int) rideDuration/60;
			if (hourDuration > 1) {
				finalPrice = rideDuration/60 - 1;
			}
		}
		else {
			int hourDuration = (int) rideDuration/60;
			if (hourDuration >= 1) {
				finalPrice = rideDuration/30 - 1;
			}
			else {
				finalPrice = rideDuration/60;
			}
		}
		return finalPrice;
	}

}
