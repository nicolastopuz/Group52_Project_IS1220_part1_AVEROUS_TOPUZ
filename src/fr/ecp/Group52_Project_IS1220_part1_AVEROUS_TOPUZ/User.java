package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;


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
	protected PathPreferenceVisitor pathPreference;
	
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
	}

	/**
	 * Method for user to pick up a Bike at a given Station. 
	 * @param s	The station from which the user should pick up the bike from.
	 */
	public void takeBike(Station s) {
		try {
			ParkingSlot p = s.getAvailableBicycle();
			p.giveBike(this);
			this.behavior = this.bike.getBehavior();
		}
		catch(EmptySlotException e) {
		}
		catch(NoAvailableBikeException e) {
		}
	}
	
	/**
	 * Method for User to drop off a Bike at a given Station.
	 * @param s The station where the Bike should be dropped off to.
	 */
	public void dropBike(Station s) {
		try {
			ParkingSlot p = s.getFreeSlot();
			p.acceptBike(this);
			this.behavior = new Walking();
		}
		catch(OccupiedSlotException e) {
		}
		catch(NoFreeSlotException e) {
		}
	}
	
	public void update() throws NotOnRideException {
		if(this.isOnARide()) {
			goTo(this.arrival, this.arrivalStationPreference, this.pathPreference);
		}
		else {
			throw new NotOnRideException();
		}
	}
	
	public void goTo(GPScoordinates arrival, ArrivalStationPreferenceVisitable arrivalStationPreference, PathPreferenceVisitor pathPreference) {
		this.arrival = arrival;
		this.arrivalStationPreference = arrivalStationPreference;
		this.pathPreference = pathPreference;
		this.ride = new Ride(this, arrival, allStations, arrivalStationPreference, pathPreference);
		this.setOnARide(true);
		this.departureOfRide = this.ride.getDepartureStation();
		this.arrivalOfRide = this.ride.getArrivalStation();
		departureOfRide.addDepartureObserver(this);
		arrivalOfRide.addArrivalObserver(this);
	}
	
	public void goTo(GPScoordinates arrival) {
		this.goTo(arrival, new NoPreference(), new FastestPath());
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

	public static void main(String[] args) {
		GPScoordinates location1;
		try {
			location1 = new GPScoordinates(10,10);
			Station station1 = new Station(10,location1);
			System.out.println(station1.getStationID());
		} catch(OutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public String accept(StatisticVisitor v) {
		return v.visit(this);
	}
}
