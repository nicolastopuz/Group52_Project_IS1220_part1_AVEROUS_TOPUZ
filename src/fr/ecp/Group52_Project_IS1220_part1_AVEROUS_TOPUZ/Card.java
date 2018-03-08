package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public abstract class Card {
	protected static double counter;
	protected double cardNumber;
	protected User user;
	protected CardTypes type;

	public Card(User user) {
		super();
		this.user=user;
	}

	public static double getCounter() {
		return counter;
	}

	public double getCardNumber() {
		return cardNumber;
	}

	public User getUser() {
		return user;
	}

	public CardTypes getType() {
		return type;
	}


	public void setUser(User user) {
		this.user = user;
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
