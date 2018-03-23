package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public class Ride {
	/**
	 * The coordinates from which the user starts his ride.
	 */
	protected GPScoordinates departure;
	/**
	 * The coordinates at which the user wishes to arrive.
	 */
	protected GPScoordinates arrival;
	/**
	 * The user performing the ride.
	 */
	protected User user;
	/**
	 * The bike used for the ride.
	 */
	protected Bike bike;
	
	public Ride(User user, GPScoordinates arrival) {
		this.user = user;
		this.departure=user.getPosition();
		this.arrival = arrival;
	}
	
	
	
}
