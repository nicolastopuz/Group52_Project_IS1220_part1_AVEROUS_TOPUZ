package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

public class Ride {
	//Class Attributes start here
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
	
	/**
	 * The preference chosen by the user for what kind of arrival station he prefers.
	 */
	protected ArrivalStationPreferenceVisitable arrivalStationPreference;
	
	/**
	 * The list of all stations present in the network. 
	 */
	protected ArrayList<Station> allStations;
	
	/**
	 * The station to which the user must go to pick up a Bike.
	 */
	protected Station departureStation;
	
	/**
	 * The station to which the user must go to drop his bike off.
	 */
	protected Station arrivalStation;
	
	/**
	 * The user preference for which path should be taken (fastest/shortest)
	 */
	protected PathPreferenceVisitor pathPreference;
	
	/**
	 * The time the user spends on the bike during the ride
	 */
	protected int timeOnBike;
	
	/**
	 * The price of the ride as a double
	 */
	protected double priceOfRide;
	
	/**
	 * The credit earned by this ride
	 */
	protected int creditEarned;
	
	/**
	 * long objects to store the time at which the user picked up a bike, in milliseconds, and the 
	 * time at which the user dropped off a bike, thus allowing to know the time 
	 * the user as spent on the bike 
	 */
	protected long timeBikeTaken, timeBikeDropped;
	
	//Methods start here
	
	/**
	 * The constructor for making a Ride instance.
	 * 
	 * @param user		The User who is on this ride
	 * @param arrival	The GPS coordinates of where the user wishes to go
	 * @param allStations	An ArrayList of all stations in the current network
	 * @param arrivalStationPreference	The preference for the arrival Station (plus station or not)
	 * @param pathPreference	The preference in the path to be taken to the arrival (Fastest or Shortest)
	 */
	public Ride(User user, GPScoordinates arrival, ArrayList<Station> allStations, ArrivalStationPreferenceVisitable arrivalStationPreference, PathPreferenceVisitor pathPreference) {
		this.user = user; 
		this.departure=user.getPosition();
		this.arrival = arrival;
		this.allStations = allStations;
		this.arrivalStationPreference = arrivalStationPreference;
		this.pathPreference = pathPreference;
		this.pathPreference.setRide(this);
		Station[] departureAndArrival = pathPreference.departureAndArrival();
		this.departureStation = departureAndArrival[0];
		this.arrivalStation = departureAndArrival[1];
	}
	
	/**
	 * This method gives you a matrix containing at index i the distance from your
	 * departure point to the station located at index i in the ArrayList
	 * <code>departureStations</code> that you put as input.
	 * 
	 * @param departureStations	The ArrayList of all stations with an available bike.
	 * @return	An ArrayList containing the distances to departure stations.
	 */
	public double[] distanceToDeparture(ArrayList<Station> departureStations) {
		double[] distances = new double[departureStations.size()];
		for (int i = 0; i < departureStations.size(); i++) {
			distances[i] = this.departure.distanceTo(departureStations.get(i).getLocation()) ;
		}
		return distances;
	}
	
	/**
	 * This method gives you a matrix containing at index i the distance from your
	 * arrival point to the station located at index i in the ArrayList
	 * <code>arrivalStations</code> that you put as input.
	 * 
	 * @param arrivalStations	The ArrayList of all stations with a free ParkingSlot.
	 * @return	An ArrayList containing the distances to arrival stations.
	 */
	public double[] distanceToArrival(ArrayList<Station> arrivalStations) {
		double[] distances = new double[arrivalStations.size()];
		for (int i = 0; i < arrivalStations.size(); i++) {
			distances[i] = this.arrival.distanceTo(arrivalStations.get(i).getLocation()) ;
		}
		return distances;
	}
	
	/**
	 * This method goes over all stations in the network, and returns an ArrayList
	 * containing only the stations, in which a bike is available for pickup.
	 * Those can then be used as departure stations for a ride.
	 * 
	 * @return	an ArrayList containing all stations with an available bike.
	 */
	public ArrayList<Station> getDepartureStations() {
		ArrayList<Station> departureStations = new ArrayList<Station>();
		for (int i = 0; i < this.allStations.size(); i++) {
			if(this.allStations.get(i).isAvailableBicycle()) {
				departureStations.add(this.allStations.get(i));
			}
		}
		return departureStations;
	}
	
	/**
	 * This method goes over all stations in the network, and returns an ArrayList
	 * containing only the stations, at which there is room to drop off a bike.
	 * This search depends on the preferences the user has put in when creating
	 * the Ride. He can either choose to avoid Plus Stations, to prefer them, or
	 * to make a simple search with no preference regarding the arrival station.
	 * 
	 * @return	an ArrayList containing all stations with a free spot.
	 */
	public ArrayList<Station> getArrivalStations() {
		return arrivalStationPreference.getArrivalStations(this);
	}
	
	/**
	 * This method calculates the distances between all stations from the input
	 * ArrayLists, and returns them as a <code>double</code> matrix. Element at
	 * position (i,j) in the output matrix corresponds to the distance between
	 * departureStation index i and arrivalStation index j.
	 * 
	 * @param departureStations	The ArrayList containing all departure stations (available bike)
	 * @param arrivalStations	The ArrayList containing all arrival stations (available free spot)
	 * @return	a double[][] matrix wit the distances between the departure stations and the arrival stations.
	 */
	public double[][] distancesUsedStation(ArrayList<Station> departureStations, ArrayList<Station> arrivalStations) {
		double[][] distancesMatrix = new double[departureStations.size()][arrivalStations.size()];
		for (int i = 0; i < departureStations.size(); i++) {
			for (int j = 0; j < arrivalStations.size(); j++) {
				distancesMatrix[i][j] = departureStations.get(i).getLocation().distanceTo(arrivalStations.get(j).getLocation());	
			}
		}
		return distancesMatrix;
	}
	
	public void endTheRide() {
		
	}
	
	//Methods
	/**
	 * This method updates the price of the ride inside the ride object.
	 */
	public void proceedPayment() {
		int credit = this.user.getTimeCredit();
		int timeToPay;
		if(this.timeOnBike >= credit) {
			timeToPay = (int)this.timeOnBike - this.user.getTimeCredit();
			this.user.setTimeCredit(0);
		}
		else {
			timeToPay = 0;
			this.user.setTimeCredit(credit - (int)this.timeOnBike);
		}
		this.priceOfRide = this.user.getCard().pay(this.bike.getType(), timeToPay);
	}
	
	/**
	 * This method proceeds to the attribution of bonus credits, depending on the 
	 * station visited by the user upon his arrival.
	 */
	public void proceedCreditAttribution() {
		Station arrivalStation = this.arrivalStation;
		this.creditEarned = arrivalStation.getBonusCredit();
		this.user.setTimeCredit( this.user.getTimeCredit() + this.creditEarned );
	}
	
	/**
	 * Method called upon when a user picks up a bike. It lets the payment center know
	 * the time at which the user picked up a bike, in order to compute the 
	 * duration of rental of the given bike.
	 */
	public void startOnBike() {
		this.bike = user.getBike();
		this.timeBikeTaken = System.currentTimeMillis();
	}
	
	/**
	 * Method called upon when a user drops a bike off. It lets the payment center know
	 * the time at which the user dropped off a bike, and then computes the 
	 * duration of rental of the given bike.
	 */
	public void stopOnBike() {
		this.timeBikeDropped = System.currentTimeMillis();
		this.timeOnBike = (int) (this.timeBikeDropped - this.timeBikeTaken)/60000;
	}
	
	public void updateArrivalStation() {
		GPScoordinates userLocation = this.user.getPosition();
		this.arrivalStation = pathPreference.getUpdateOnArrivalStation(userLocation);		
	}
	
	//Les Getters
	/**
	 * The getter for the GPScoordinates of the departure.
	 * @return	a GPScoordinates object for the departure's location
	 */
	public GPScoordinates getDeparture() {
		return this.departure;
	}

	/**
	 * The getter for the GPScoordinates of the arrival.
	 * @return	a GPScoordinates object for the arrival location
	 */
	public GPScoordinates getArrival() {
		return this.arrival;
	}
	
	/**
	 * The getter for the User who is interested in this Ride.
	 * @return	The User concerned by this ride
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * The getter for the preferences of the user regarding the arrival
	 * station. He can choose to prefer Plus Stations, to avoid them, 
	 * or simply not to have any preferences. 
	 * @return	The user's preferences for the arrival station.
	 */
	public ArrivalStationPreferenceVisitable getPreference() {
		return this.arrivalStationPreference;
	}
	
	/**
	 * The getter for the ArrayList containing all stations on the network.
	 * @return	The ArrayList containing all stations on the network.
	 */
	public ArrayList<Station> getAllStations() {
		return this.allStations;
	}
	
	/**
	 * The getter for the Station at which the user should deposit his bike.
	 * @return	The Station at which the user should drop his bike off.
	 */
	public Station getArrivalStation() {
		return this.arrivalStation;
	}
	
	/**
	 * The getter for the Station at which the user should pick up a bike.
	 * @return	The Station at which the user should pick up a bike.
	 */
	public Station getDepartureStation() {
		return this.departureStation;
	}
	
	/**
	 * The credit earned with this ride, if any (plus station)
	 * @return	credit earned with the ride
	 */
	public int getCreditEarned() {
		return creditEarned;
	}
	
	/**
	 * The getter for the price of the ride
	 * @return	the price of the ride in euros as aa int
	 */
	public double getPriceOfRide() {
		return priceOfRide;
	}
	
	/**
	 * The getter for the time spent on a bike during the ride
	 * @return	the time spent on a bike during the ride in milliseconds as a double
	 */
	public int getTimeOnBike() {
		return timeOnBike;
	}
	
	//Les Setters
	
	/**
	 * Setter for the GPScoordinates of the departure of the ride
	 * @param departure the GPScoordinates object for the location of the departure of the ride
	 */
	public void setDeparture(GPScoordinates departure) {
		this.departure=departure;
	}
	
	/**
	 * Setter for the GPScoordinates of the arrival of the ride
	 * @param arrival the GPScoordinates object for the location of the arrival of the ride
	 */
	public void setArrival(GPScoordinates arrival) {
		this.arrival=arrival;
	}
	
	/**
	 * Setter for the User who in on this ride
	 * @param user	the user who is on this ride
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Setter for the preference in the arrival station (avoid plus, prefer plus or no preference ?)
	 * @param preference	the preference among the three types
	 */
	public void setPreference(ArrivalStationPreferenceVisitable preference) {
		this.arrivalStationPreference=preference;
	}
	
	/**
	 * The setter for the ArrayList containing all stations present in the network.
	 * @param allStation	the ArrayList conaining all stations of the network
	 */
	public void setAllStations(ArrayList<Station> allStation) {
		this.allStations=allStation;
	}
	
	/**
	 * Setter used to set the arrival station at which the user should drop his bike off
	 * @param arrivalStation	the station at which the user should drop his bike off
	 */
	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation=arrivalStation;
	}
	
	/**
	 * Setter used to set the departure station from which the user should pick up his bike
	 * @param departure	the station from which the user should pick up his bike 
	 */
	public void setDepartureStation(Station departureStation) {
		this.departureStation=departureStation;
	}
	
	/**
	 * The setter for the credit earned for the user during this ride (if arrival is a plus station)
	 * @param creditEarned	the credit earned for this ride
	 */
	public void setCreditEarned(int creditEarned) {
		this.creditEarned = creditEarned;
	}
	
	/**
	 * The setter for the price to pay for the ride
	 * @param priceOfRide	the price to pay for the ride
	 */
	public void setPriceOfRide(double priceOfRide) {
		this.priceOfRide = priceOfRide;
	}
	
	/**
	 * The setter for the time the user has spent on a bike during this ride
	 * @param timeOnBike	the time he has spent on a bike during this ride
	 */
	public void setTimeOnBike(int timeOnBike) {
		this.timeOnBike = timeOnBike;
	}
	
	@Override
	public String toString() {
		return("This ride goes from point " +this.departure.toString()+ " to point " 
				+this.arrival.toString()+ " passing through stations " +this.departureStation.getStationID()+ 
				" and " +this.arrivalStation.getStationID()+ ".");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ride) {
			Ride that = (Ride) obj;
			return (this.departure.equals(that.getDeparture()) && this.arrival.equals(that.getArrival()) 
					&& this.departureStation.equals(that.getDepartureStation()) 
					&& this.arrivalStation.equals(that.getArrivalStation()));
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (int) (41*(41+this.bike.hashCode() + this.arrivalStation.hashCode()));
	}
	
}
