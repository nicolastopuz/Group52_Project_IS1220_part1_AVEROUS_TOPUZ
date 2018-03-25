package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

/**
 * This class is used to proceed the payment operations for a given ride.
 * It calculates the price of the ride, and defines if and 
 * how much bonus credit should be given to the user 
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class PaymentCenter {
	
	//Attributes
	/**
	 * The Ride for which payments have to be computed
	 */
	protected Ride ride;
	
	/**
	 * The user who is on this ride
	 */
	protected User user;
	
	/**
	 * The bike used on this ride
	 */
	protected Bike bike;
	
	/**
	 * The card of the user
	 */
	protected Card card;
	
	/**
	 * The price of the ride
	 */
	protected double price;
	
	/**
	 * long objects to store the time at which the user picked up a bike, in milliseconds, and the 
	 * time at which the user dropped off a bike, thus allowing to know the time 
	 * the user as spent on the bike : rentalDuration, expressed in minutes.
	 */
	protected long timeBikeTaken, timeBikeDropped;
	
	protected int rentalDuration;
	
	/**
	 * Constructor for a PaymentCenter instance
	 * @param ride	The ride for which payments have to be computed
	 */
	public PaymentCenter(Ride ride) {
		this.ride = ride;
		this.user = ride.getUser();
		this.card = user.getCard();
	}
	
	
	//Methods
	/**
	 * This method updates the price of the ride inside the ride object.
	 */
	public void proceedPayment() {
		this.price = this.card.pay(this.bike.getType(), this.ride.getTimeOnBike());
		this.ride.setPriceOfRide(this.price);
	}
	
	/**
	 * This method proceeds to the attribution of bonus credits, depending on the 
	 * station visited by the user upon his arrival.
	 */
	public void proceedCreditAttribution() {
		Station arrivalStation = this.ride.getArrivalStation();
		this.ride.setCreditEarned(arrivalStation.getBonusCredit());
		this.user.setTimeCredit( this.user.getTimeCredit() + arrivalStation.getBonusCredit() );
	}
	
	/**
	 * Method called upon when a user picks up a bike. It lets the payment center know
	 * the time at which the user picked up a bike, in order to compute the 
	 * duration of rental of the given bike.
	 */
	public void startOnBike() {
		this.setBike(user.getBike());
		this.timeBikeTaken = System.currentTimeMillis();
	}
	
	/**
	 * Method called upon when a user drops a bike off. It lets the payment center know
	 * the time at which the user dropped off a bike, and then computes the 
	 * duration of rental of the given bike.
	 */
	public void stopOnBike() {
		this.timeBikeDropped = System.currentTimeMillis();
		this.rentalDuration = (int) (this.timeBikeDropped - this.timeBikeTaken)/60000;
		this.ride.setTimeOnBike(this.rentalDuration);
	}
	
	//Getters
	public Bike getBike() {
		return bike;
	}
	
	public Card getCard() {
		return card;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Ride getRide() {
		return ride;
	}
	
	public User getUser() {
		return user;
	}
	
	//Setters
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
