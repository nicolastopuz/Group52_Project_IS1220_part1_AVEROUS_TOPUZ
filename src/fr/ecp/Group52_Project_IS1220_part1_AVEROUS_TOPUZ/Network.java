package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;

/**
 * The myVelib global network. It contains all users, all stations,
 * and all bikes present in the myVelib System.
 * THe network is used to create new instances of Stations, Users, 
 * or Bikes.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public class Network implements VisitableItems {
	
	/**
	 * An ArrayList containing all users who use the myVelib system
	 */
	protected ArrayList<User> userList;
	
	/**
	 * An ArrayList containing all stations built in the myVelib system
	 */
	protected ArrayList<Station> stationList;
	
	/**
	 * An ArrayList containing all bikes that exist in the myVelib system
	 */
	protected ArrayList<Bike> bikeList;
	
	
	/**
	 * The constructor for making a Network instance. 
	 * This constructor creates a Network in which the Stations are located 
	 * at random on the Earth's surface.
	 * 
	 * @param numberOfStations	The number of stations to be built in the network, as an int
	 * @param slotsPerStation	The number of parking slots to build within each station, as an int
	 * @param fillingPercentage	The filling rate of each station with bikes, as a double
	 */
	public Network(int numberOfStations, int slotsPerStation, double fillingPercentage) {
		Double d = (slotsPerStation*fillingPercentage);
		int bikesPerStation = d.intValue();
		
		ArrayList<Bike> bikeList = new ArrayList<Bike>();
		ArrayList<Station> stationList = new ArrayList<Station>();
		ArrayList<User> userList = new ArrayList<User>();
		
		for (int i = 0; i < numberOfStations; i++) {
			try {
				Station s = this.createStation(slotsPerStation, GPScoordinates.randomLocation(), bikesPerStation, 0.7f);
			}
			catch(MoreBikesThanSlotsException e) {
				e.printStackTrace();
			}
			catch(InvalidProportionsException e) {
				e.printStackTrace();
			}
		}
		
		this.userList = new ArrayList<User>();
		this.stationList = new ArrayList<Station>();
		this.bikeList = new ArrayList<Bike>();
	}
	
	/**
	 * A simple method to create a Station and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @param location	The location of the station
	 * @return	the new Station object
	 */
	public Station createStation(int numberOfSlots, GPScoordinates location) {
		Station s = new Station(this, numberOfSlots, location);
		this.addStation(s);
		return s;
	}
	/**
	 * A simple method to create a Station and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @param location	The location of the station
	 * @param mechanicalBikeProportion the proportion of mechanical Bikes among the 
	 * bikes added to the station
	 * @return	the new Station object
	 * @throws MoreBikesThanSlotsException
	 * @throws InvalidProportionsException
	 */
	public Station createStation(int numberOfSlots, GPScoordinates location, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		Station s = new Station(this, numberOfSlots, location, numberOfBikes, mechanicalBikeProportion);
		this.addStation(s);
		return s;
	}
	
	/**
	 * A simple method to create a User and add it to the userList
	 * @param name the name of the user as a String
	 * @return	A new User object
	 */
	public User createUser(String name) {
		User u = new User(name);
		u.setNetwork(this);
		this.addUser(u);
		return u;
	}
	/**
	 * A simple method to create a User and add it to the userList
	 * @param name the name of the user as a String
	 * @param cardType the type of card the user should own, if any
	 * @return	A new User object
	 */
	public User createUser(String name, CardTypes cardType) {
		User u = new User(name, cardType);
		u.setNetwork(this);
		this.addUser(u);
		return u;
	}
	
	/**
	 * A simple method to create a Bike and add it to the bikeList
	 * @param type	The type of bike, chosen from enum BikesType
	 * @return	A new Bike object, of type <code>type</code>
	 */
	public Bike createBike(BikesType type) {
		Bike b = BikeFactory.create(type);
		b.setNetwork(this);
		this.addBike(b);
		return b;
	}
	
	/**
	 * Method to add a bike to the bikeList
	 * @param bike	The bike to be added to the list
	 */
	public void addBike(Bike bike) {
		this.bikeList.add(bike);
	}
	
	/**
	 * Method to add a user to the userList
	 * @param user	The user to be added to the list
	 */
	public void addUser(User user) {
		this.userList.add(user);
	}
	
	/**
	 * Method to add a station to the stationList
	 * @param station	The station to be added to the list
	 */
	public void addStation(Station station) {
		stationList.add(station);
	}


	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);	}
	
	//Getters
	
	/**
	 * Getter method for the ArrayList object userList
	 * @return	The list of all users in the Network as an ArrayList
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}
	
	/**
	 * Getter method for the ArrayList object stationList
	 * @return	The list of all stations in the Network as an ArrayList
	 */
	public ArrayList<Station> getStationList() {
		return stationList;
	}

	/**
	 * Getter method for the ArrayList object bikeList
	 * @return	The list of all bikes in the Network as an ArrayList
	 */
	public ArrayList<Bike> getBikeList() {
		return bikeList;
	}
	
	
	//Setters
	/**
	 * Setter method for the ArrayList object userList
	 * @param userList	the list of all users in the Network as an ArrayList
	 */
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	
	/**
	 * Setter method for the ArrayList object stationList
	 * @param userList	the list of all stations in the Network as an ArrayList
	 */
	public void setStationList(ArrayList<Station> stationList) {
		this.stationList = stationList;
	}
	
	/**
	 * Setter method for the ArrayList object bikeList
	 * @param userList	the list of all bikes in the Network as an ArrayList
	 */
	public void setBikeList(ArrayList<Bike> bikeList) {
		this.bikeList = bikeList;
	}
	
	
	
}
