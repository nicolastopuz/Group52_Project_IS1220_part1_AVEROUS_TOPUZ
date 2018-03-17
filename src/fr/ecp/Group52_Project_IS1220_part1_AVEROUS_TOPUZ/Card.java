package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * The Card abstract class is used to indicate the card status of a user
 * in order to know if he has a card and what type of card he uses in that case.
 * As a result the enumeration CardTypes defines all the possible status (NoCard, Vlibre and Vmax up to now)
 * The abstract class is extended by a concrete class for each possible type of class
 * he is characterized by :
 * <ul>
 * <li> a card number
 * <li> a user
 * <li> a type
 * </ul>
 * <p>
 * 
 * @see User
 * @see CardTypes
 * @see NoCard
 * @see Vlibre
 * @see Vmax
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public abstract class Card {
	
	/**
	 *  A static double counting the number of instance of actual cards (the NoCard type is no counted)
	 */
	protected static double cardCounter;
	
	/**
	 * A read-only double defining a unique CardNumber for each actual type of cards (The NoCard type doesn't have a card number) 
	 */
	protected double cardNumber;
	
	/**
	 * A read-only User object representing the user of the card
	 */
	protected User user;
	
	/**
	 * A CardTypes object defining the type of the Card (according to the enumeration)
	 */
	protected CardTypes type;

	
	/**
	 * A constructor creating a new Card and assigning him a user 
	 * @param user A User object defining the user of the card
	 */
	public Card(User user) {
		super();
		this.user=user;
	}
	
	/**
	 * A getter returning the number of cards 
	 * @return the number of cards as a double
	 */
	public static double getCardCounter() {
		return cardCounter;
	}

	/**
	 * A getter returning the unique numerical id of the card
	 * @return the unique numerical id of the card as a double
	 */
	public double getCardNumber() {
		return cardNumber;
	}

	/**
	 * A getter returning the user of the card
	 * @return the user if the card as a User Object
	 */
	public User getUser() {
		return user;
	}

	/**
	 * A getter returning the type of the card 
	 * @return the type of the card as a CardTypes Object 
	 */
	public CardTypes getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return ("This card number "+this.cardNumber+" is a "+this.type+" and belongs to "+this.user.getName()+".\n");
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.cardNumber));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card that = (Card) obj;
			return (this.cardNumber==that.getCardNumber() && this.type==that.getType() && this.user==that.getUser());
		}
		else {
			return false;
		}
	}
	
	

}
