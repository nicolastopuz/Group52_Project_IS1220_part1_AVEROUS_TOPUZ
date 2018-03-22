package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The CardFactory class implements a simple Factory pattern in order to allow 
 * the creation of cards in an easy and OPEN-CLOSE way
 * 
 * @see Card
 * @see CardTypes
 * @see User
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class CardFactory {

	public static Card create(User user, CardTypes type) {
		if (type==CardTypes.NoCard) {
			return new NoCard(user);
		}
		else if (type==CardTypes.Vlibre) {
			return new Vlibre(user);
		}
		else if (type==CardTypes.Vmax) {
			return new Vmax(user);
		}
		else {
			return null;
		}
	}

}
