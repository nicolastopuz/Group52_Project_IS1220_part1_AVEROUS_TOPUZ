package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;


import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.EmptySlotException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoAvailableBikeException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoFreeSlotException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OccupiedSlotException;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.OutOfBoundsException;


/**
 * The User class basically represents anyone using the myVelib
 * system. Whether he has a Velib card or not is not relevant to
 * being a user. A user is just someone who uses the system.
 * he is characterized by :
 * <ul>
 * <li> a name
 * <li> his position (GPS coordinates)
 * <li> a unique ID
 * </ul>
 * <p>
 * In case a user holds a card, he also has a time-credit balance 
 * (expressed in minutes) representing the credit gained by returning
 * bicycles to <i>plus stations</i>. The time credit is used to compute 
 * the actual cost of a bike ride.
 * 
 * @see GPScoordinates
 * @see Card
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */

public class User implements VisitableItems, Observer {
	
	/**
	 * A string that defines the name of the user
	 */
	protected String name;
	
	/**
	 * A static double counting the number of instance of the user class
	 */
	protected static double userCounter=0;
	
	/**
	 * A read-only double defining a unique numericalId for each user 
	 */
	protected final double numericalId;
	
	/**
	 * A GPScoordinates object storing the position of the user
	 */
	protected GPScoordinates position;
	
	/**
	 * A Card object indicating if the user has a Card and what type of card if necessary
	 */
	protected Card card;
	
	/**
	 * A MovingBehavior object storing how the user is moving (walking, biking on a mechanical bike, biking on an electrical bike) and his speed accordingly
	 */
	protected MovingBehavior behavior;
	
	/**
	 * A Bike object storing the bike the user is using.
	 */
	protected Bike bike;
	
	/**
	 * A boolean indicating whether the user is on a ride
	 */
	protected boolean onARide;
	
	/**
	 * A Ride object indicating the ride
	 */
	protected Ride ride;
	
	/**
	 * The stations to which the user should go for his ride
	 * departureOfRide is where he should pick up a bike
	 * arrivalOfRide is where he should drop his bike off
	 */
	protected Station departureOfRide, arrivalOfRide;
	
	/**
	 * The GPS coordinates of the place where the user wants to go
	 */
	protected GPScoordinates arrival;
	
	/**
	 * The preference of the user for the way he wishes to go to station
	 */
	private ArrivalStationPreferenceVisitable arrivalStationPreference;
	
	/**
	 * The user preference for which path should be taken (fastest/shortest)
	 */
	protected PathPreferences pathPreference;
	
	/**
	 * An ArrayList of Rides to keep the record of all the rides performed by the user
	 */
	protected ArrayList<Ride> rides;
	
	/**
	 * The Network the User belongs to
	 */
	protected Network network;
	
	/**
	 * The user's time credit
	 */
	protected double timeCredit;
	
	
	/**
	 * A constructor creating a User instance with a name and a unique numericalId and setting him as a no card user
	 * @param name A string defining the name of the user 
	 */
	public User(String name) {
		this.name=name;
		User.userCounter+=1;
		this.numericalId=User.userCounter;
		this.card=CardFactory.create(this, CardTypes.NoCard);
		this.onARide=false;
		this.rides=new ArrayList<Ride>();
		this.timeCredit = 0;
		this.setBehavior(new Walking());
		this.arrivalStationPreference = new NoPreference();
		this.pathPreference = PathPreferences.Fastest;
	}


	/**
	 * A constructor creating a User instance with a name, a unique numericalId and a card of the type entered
	 * @param name A string defining the name of the user 
	 * @param type A CardTypes defining the type of the card the user has
	 */
	public User(String name, CardTypes type) {
		this.name=name;
		User.userCounter+=1;
		this.numericalId=User.userCounter;
		this.card=CardFactory.create(this, type);
		this.rides=new ArrayList<Ride>();
		this.timeCredit = 0;
		this.setBehavior(new Walking());
		this.arrivalStationPreference = new NoPreference();
		this.pathPreference = PathPreferences.Fastest;
	}


	/**
	 * Method for user to pick up a Bike at a given Station. 
	 * @param s	The station from which the user should pick up the bike from.
	 */
	public synchronized void takeBike(Station s) {
		try {
			ParkingSlot p = s.getAvailableBicycle();
			departureOfRide.removeDepartureObserver(this);
			p.giveBike(this);
			this.behavior = this.bike.getBehavior();
			double numberOfRent = s.getNumberOfRent()+1;
			s.setNumberOfRent(numberOfRent);
			this.ride.setBike(this.bike);
			this.ride.startOnBike();

		}
		catch(EmptySlotException | NoRideException | NoAvailableBikeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for user to pick up a Bike at a given Station. 
	 * @param s	The station from which the user should pick up the bike from.
	 */
	public synchronized void takeBike(Station s, BikesType type) {
		try {
			ParkingSlot p = s.getAvailableBicycle(type);
			departureOfRide.removeDepartureObserver(this);
			p.giveBike(this);
			this.behavior = this.bike.getBehavior();
			double numberOfRent = s.getNumberOfRent()+1;
			s.setNumberOfRent(numberOfRent);
			this.ride.setBike(this.bike);
			this.ride.startOnBike();
		}
		catch(EmptySlotException e) {
			e.printStackTrace();
		}
		catch(NoAvailableBikeException e) {
			this.takeBike(s);
		}
		catch(NoRideException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for User to drop off a Bike at a given Station.
	 * @param s The station where the Bike should be dropped off to.
	 */
	public synchronized void dropBike(Station s) {
		try {
			ParkingSlot p = s.getFreeSlot();
			s.removeArrivalObserver(this);
			p.acceptBike(this);
			this.behavior = new Walking();
			
			double numberOfReturn = s.getNumberOfReturn();
			s.setNumberOfReturn(numberOfReturn+1);
			
			this.ride.stopOnBike();
			
			this.ride.proceedCreditAttribution();
			this.ride.proceedPayment();
		}
		catch(OccupiedSlotException | NoRideException | NoFreeSlotException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for User to drop off a Bike at a given Station.
	 * @param s 	The station where the Bike should be dropped off to.
	 * @param time 	the time the user spent on the bike before returning it.
	 */
	public synchronized void dropBike(Station s, double time) {
		try {
			ParkingSlot p = s.getFreeSlot();
			s.removeArrivalObserver(this);
			p.acceptBike(this);
			this.behavior = new Walking();
			
			double numberOfReturn = s.getNumberOfReturn();
			s.setNumberOfReturn(numberOfReturn+1);
			
			this.ride.stopOnBike();

			this.ride.proceedCreditAttribution();
			this.ride.proceedPayment(time);
		}
		catch(OccupiedSlotException | NoRideException | NoFreeSlotException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateDeparture() throws NoRideException {
		if(this.isOnARide()) {
			this.ride.interrupt();
		}
		else {
			throw new NoRideException();
		}
	}
	
	@Override
	public void updateArrival() throws NoRideException {
		if(this.isOnARide()) {
			this.ride.interrupt();
		}
		else {
			throw new NoRideException();
		}
	}
	
	/**
	 * Method used by users to go to a specific point using the myVelib system.
	 * 
	 * @param arrival	The GPScoordinates of where the user wishes to go to
	 * @param arrivalStationPreference	The Preference the user has in the nature of the arrival station
	 * @param pathPreference	The preference the user has concerning the path to take to reach his goal
	 */
	public void goTo(GPScoordinates arrival, ArrivalStationPreferenceVisitable arrivalStationPreference, PathPreferences pathPreference) {
		this.arrival = arrival;
		this.arrivalStationPreference = arrivalStationPreference;
		this.pathPreference = pathPreference;
		this.ride = new Ride(this, arrival, this.network.getStationList(), arrivalStationPreference, pathPreference);
		this.departureOfRide = this.ride.getDepartureStation();
		this.arrivalOfRide = this.ride.getArrivalStation();
		this.setOnARide(true);
	}
	
	/**
	 * Default method used by users to go to a specific point using the myVelib system.
	 * 
	 * @param arrival	The GPScoordinates of where the user wishes to go to
	 */
	public void goTo(GPScoordinates arrival) {
		this.goTo(arrival, this.arrivalStationPreference, this.pathPreference);
	}
	
	
	//Getters 
	/**
	 * A getter returning the name of the user
	 * @return the name of the user as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * A getter returning the userCounter 
	 * @return the number of users as a double
	 */
	public static double getUserCounter() {
		return userCounter;
	}

	/**
	 * A getter returning the unique numericalId of the user
	 * @return the unique numerical id of the user as a double
	 */
	public double getNumericalId() {
		return numericalId;
	}

	/**
	 * A getter returning the position of the user
	 * @return the position of the user as a GPScoordinates
	 */
	public GPScoordinates getPosition() {
		return position;
	}

	/**
	 * A getter returning the card of the user
	 * @return the card of the user as a Card 
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * A getter returning the movingBehavior of the user  
	 * @return the user moving behavior as a MovingBehavior
	 */
	public MovingBehavior getBehavior() {
		return behavior;
	}

	/**
	 * A getter returning the Bike of the user
	 * @return	the bike of the user as a Bike
	 */
	public Bike getBike() {
		return bike;
	}
	
	/**
	 * A getter returning the TimeCredit of the user
	 * @return	the time credit of the user as an int
	 */
	public double getTimeCredit() {
		return timeCredit;
	}
	
	/**
	 * A getter returning the Network the User belongs to
	 * @return	the Network the User belongs to
	 */
	public Network getNetwork() {
		return network;
	}
	
	/**
	 * A getter returning the arrival Station for the ride the User is on
	 * @return	the arrival Station for the ride the User is on
	 */
	public Station getArrivalOfRide() {
		return arrivalOfRide;
	}
	
	/**
	 * A getter returning the departure Station for the ride the User is on
	 * @return	the departure Station for the ride the User is on
	 */
	public Station getDepartureOfRide() {
		return departureOfRide;
	}
	
	/**
	 * A getter for the arrival station preference of the user
	 * @return	the arrival station preference of the user
	 */
	public ArrivalStationPreferenceVisitable getArrivalStationPreference() {
		return arrivalStationPreference;
	}
	
	/**
	 * A getter returning the arrival location (the place the user wants to go to)
	 * @return	the arrival location (the place the user wants to go to)
	 */
	public GPScoordinates getArrival() {
		return arrival;
	}
	
	/**
	 * A getter for the path preference of the user
	 * @return	the path preference of the user
	 */
	public PathPreferences getPathPreference() {
		return pathPreference;
	}
	
	//Setters
	
	/**
	 * A setter for the arrival Station of the User
	 * @param	the arrival Station of the User
	 */
	public void setArrivalOfRide(Station arrivalOfRide) {
		this.arrivalOfRide = arrivalOfRide;
	}
	
	/**
	 * A setter for the departure Station of the User
	 * @param	the departure Station of the User
	 */
	public void setDepartureOfRide(Station departureOfRide) {
		this.departureOfRide = departureOfRide;
	}
	
	/**
	 * A setter for the path preference of the User
	 * @param	the path preference of the User
	 */
	public void setPathPreference(PathPreferences pathPreference) {
		this.pathPreference = pathPreference;
	}
	
	/**
	 * A setter for the arrival station preference of the User
	 * @param	the arrival station preference of the User
	 */
	public void setArrivalStationPreference(ArrivalPreferences arrivalPreference) {
		this.arrivalStationPreference = arrivalPreference.getArrivalPreference();
	}
	
	/**
	 * A setter for the arrival station preference of the User
	 * @param	the arrival station preference of the User
	 */
	public void setArrivalStationPreference(ArrivalStationPreferenceVisitable arrivalPreference) {
		this.arrivalStationPreference = arrivalPreference;
	}
	
	/**
	 * A setter for the arrival location of the User
	 * @param	the arrival location of the User
	 */
	public void setArrival(GPScoordinates arrival) {
		this.arrival = arrival;
	}
	
	/**
	 * A setter to change the name of the user 
	 * @param name A string defining the name of the user 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * A setter to change the position of the user
	 * @param position A GPScoordinate object defining the position of the user
	 */
	public void setPosition(GPScoordinates position) {
		this.position = position;
	}

	/**
	 * A setter to change the card status of the user 
	 * @param card A Card object defining the card status of the user 
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * A setter to set the moving behavior of the user 
	 * @param behavior A MovingBehavior object defining the moving behavior of the user
	 */
	public void setBehavior(MovingBehavior behavior) {
		this.behavior = behavior;
	}
	
	/**
	 * A setter to set the bike of the user 
	 * @param bike A Bike object defining bike of the user
	 */
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	/**
	 * A getter to know whether the user is on a ride
	 * @return onARide A boolean defining whether the user is on a ride
	 */
	public boolean isOnARide() {
		return onARide;
	}
	
	/**
	 * A getter returning the ride of the user if he has one
	 * @return ride A Ride object defining the ride the user is on.
	 */
	public Ride getRide() throws NoRideException{
		if (this.isOnARide()) {
			return ride;
		}
		else {
			throw new NoRideException();
		}
	}

	/**
	 * A setter to set whether the user is on a ride
	 * @param onARide a boolean indicating whether the user is on a ride
	 */
	public void setOnARide(boolean onARide) {
		this.onARide = onARide;
	}
	
	/**
	 * A setter to signal that the user is on a specific ride
	 * @param ride A Ride object defining the ride that the user is on.
	 */
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	/**
	 * A setter to set the user's time credit
	 * @param timeCredit an int indicating the user's new time credit
	 */
	public void setTimeCredit(double timeCredit) {
		this.timeCredit = timeCredit;
	}
	
	/**
	 * A setter to set the array list rides storing all the rides done by the user
	 * @param rides an ArrayList storing all the rides done by the user
	 */
	public ArrayList<Ride> getRides() {
		return rides;
	}
	
	/**
	 * a setter for the Network the User belongs to
	 * @param network the Network the User belongs to
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
	
	/**
	 * A Method to store a done ride in the ArrayLists rides
	 * @param ride The finished ride to add to the list
	 */
	public void addRide(Ride ride) {
		this.rides.add(ride);
	}
	
	
	@Override
	public String toString() {
		if (this.card.getType()==CardTypes.NoCard) {
			return (this.name+" is user number "+this.numericalId+" and hasn't got a card.\n");
		}
		else {
			return (this.name+" is user number "+this.numericalId+" and has a "+this.card.getType()+" card.\n");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User that = (User) obj;
			return (this.numericalId==that.getNumericalId() && this.name==that.getName() && this.card==that.getCard() && this.behavior==that.getBehavior());
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.name.hashCode())+this.numericalId);
	}	

	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);
	}
}
