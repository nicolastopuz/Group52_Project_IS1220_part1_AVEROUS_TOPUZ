package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

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
	 * The constructor for making a Ride instance.
	 * 
	 * @param user
	 * @param arrival
	 * @param allStations
	 * @param arrivalStationPreference
	 */
	public Ride(User user, GPScoordinates arrival, ArrayList<Station> allStations, ArrivalStationPreferenceVisitable arrivalStationPreference) {
		this.user = user; 
		this.departure=user.getPosition();
		this.arrival = arrival;
		this.allStations = allStations;
		this.arrivalStationPreference = arrivalStationPreference;
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
	
	
	//Les Setters
	
	public void setDeparture(GPScoordinates departure) {
		this.departure=departure;
	}
	
	public void setArrival(GPScoordinates arrival) {
		this.arrival=arrival;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setPreference(ArrivalStationPreferenceVisitable preference) {
		this.arrivalStationPreference=preference;
	}
	
	public void setAllStations(ArrayList<Station> allStation) {
		this.allStations=allStation;
	}
	
	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation=arrivalStation;
	}
	
	public void setDepartureStation(Station departureStation) {
		this.departureStation=departureStation;
	}
	
	@Override
	public String toString() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
	}
	
	@Override
	public int hashCode() {
		
	}
	
}
