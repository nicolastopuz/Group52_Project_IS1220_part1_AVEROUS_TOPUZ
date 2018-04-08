package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network;

import java.util.ArrayList;

import org.omg.CORBA.DynAnyPackage.Invalid;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.StatisticCompiler;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Stats.VisitableItems;

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
	 * A string to store the name of the network
	 */
	protected String name;
	
	/**
	 * each network's ID
	 */
	protected int networkID;
	
	/**
	 * A counter to guarantee each network has a unique ID
	 */
	protected static int networkCounter;
	
	/**
	 * A double to store the side of the area in which the network is generated.
	 */
	protected double sideArea;
	
	/**
	 * The constructor for making a Network instance.
	 * 
	 * @param name	The name of the network, as a String
	 */
	public Network(String name) {
		this.name = name;
		networkCounter++;
		this.networkID = networkCounter;
		this.bikeList = new ArrayList<Bike>();
		this.stationList = new ArrayList<Station>();
		this.userList = new ArrayList<User>();
		this.sideArea=4;
	}
	
	/**
	 * The constructor for making a Network instance. 
	 * This constructor creates a Network in which the Stations are located 
	 * at random in a square of side <code>4</code> on the earth's surface.
	 * This is the default constructor for making a Network within a square.
	 * 
	 * @param name				The name of the network, as a String
	 * @param numberOfStations	The number of stations to be built in the network, as an int
	 * @param slotsPerStation	The number of parking slots to build within each station, as an int
	 * @param fillingPercentage	The filling rate of each station with bikes, as a double
	 */
	public Network(String name, int numberOfStations, int slotsPerStation, double fillingPercentage) throws InvalidProportionsException{
		this(name, numberOfStations, slotsPerStation, 4, fillingPercentage);
	}
	
	/**
	 * The constructor for making a Network instance. 
	 * This constructor creates a Network in which the Stations are located 
	 * at random in a square of side <code>sideArea</code> on the earth's surface.
	 * 
	 * @param name				The name of the network, as a String
	 * @param numberOfStations	The number of stations to be built in the network, as an int
	 * @param slotsPerStation	The number of parking slots to build within each station, as an int
	 * @param sideArea			The side of the square will randomly pop into (in km) as a double
	 * @param fillingPercentage	The filling rate of each station with bikes, as a double
	 */
	public Network(String name, int numberOfStations, int slotsPerStation, double sideArea, double fillingPercentage) throws InvalidProportionsException{
		if(fillingPercentage<0 || fillingPercentage>1) {throw new InvalidProportionsException();}
		else {			
			Double d = (slotsPerStation*fillingPercentage);
			int bikesPerStation = d.intValue();
			this.sideArea = sideArea;
			this.name = name;
			networkCounter++;
			this.networkID = networkCounter;
			this.userList = new ArrayList<User>();
			this.stationList = new ArrayList<Station>();
			this.bikeList = new ArrayList<Bike>();
			
			for (int i = 0; i < numberOfStations; i++) {
				try {
					Station s = this.createStation(slotsPerStation, GPScoordinates.randomLocation(sideArea), bikesPerStation, 0.7f);
				}
				catch(MoreBikesThanSlotsException e) {
					e.printStackTrace();
				}
				catch(InvalidProportionsException e) {
					e.printStackTrace();
				}
			}	
		}
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
	 * A simple method to create a Station within the Network's range and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @return	the new Station object
	 */
	public Station createStation(int numberOfSlots) {
		Station s = new Station(this, numberOfSlots, GPScoordinates.randomLocation(sideArea));
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
	 * A simple method to create a Station within the Network's range and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @param mechanicalBikeProportion the proportion of mechanical Bikes among the 
	 * bikes added to the station
	 * @return	the new Station object
	 * @throws MoreBikesThanSlotsException
	 * @throws InvalidProportionsException
	 */
	public Station createStation(int numberOfSlots, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		Station s = new Station(this, numberOfSlots, GPScoordinates.randomLocation(sideArea), numberOfBikes, mechanicalBikeProportion);
		this.addStation(s);
		return s;
	}
	
	/**
	 * A simple method to create a StationPlus and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @param location	The location of the station
	 * @return	the new StationPlus object
	 */
	public StationPlus createStationPlus(int numberOfSlots, GPScoordinates location) {
		StationPlus s = new StationPlus(this, numberOfSlots, location);
		this.addStation(s);
		return s;
	}
	
	/**
	 * A simple method to create a StationPlus within the Network's range and add it to the stationList
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @return	the new StationPlus object
	 */
	public StationPlus createStationPlus(int numberOfSlots) {
		StationPlus s = new StationPlus(this, numberOfSlots, GPScoordinates.randomLocation(sideArea));
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
	public StationPlus createStationPlus(int numberOfSlots, GPScoordinates location, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		StationPlus s = new StationPlus(this, numberOfSlots, location, numberOfBikes, mechanicalBikeProportion);
		this.addStation(s);
		return s;
	}
	
	/**
	 * A simple method to create a Station within the Network's range and add it to the stationList 
	 * @param numberOfSlots	The number of slots to be built in the station
	 * @param mechanicalBikeProportion the proportion of mechanical Bikes among the 
	 * bikes added to the station
	 * @return	the new Station object
	 * @throws MoreBikesThanSlotsException
	 * @throws InvalidProportionsException
	 */
	public StationPlus createStationPlus(int numberOfSlots, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		StationPlus s = new StationPlus(this, numberOfSlots, GPScoordinates.randomLocation(sideArea), numberOfBikes, mechanicalBikeProportion);
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
		u.setPosition(GPScoordinates.randomLocation(this.sideArea));
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
		u.setPosition(GPScoordinates.randomLocation(this.sideArea));
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
	
	/**
	 * Method to find a station in the Network using its unique ID.
	 * @param stationID the ID of the station, as an int
	 * @return	The station whose ID was stationID
	 */
	public Station findStation(int stationID) throws NoSuchStationException {
		for(Station station : stationList) {
			if (station.getStationID() == stationID)
				return station;
		}
		throw new NoSuchStationException();
	}
	
	//Getters
	
	/**
	 * Getter for the side of the area in which the network is generated, in km
	 * @return	the side of the area in which the network is generated, in km
	 */
	public double getSideArea() {
		return sideArea;
	}
	
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
	
	/**
	 * Getter method for the String object containing the name of the network
	 * @return	The name of the network as a String.
	 */
	public String getName() {
		return this.name;
	}
	
	
	//Setters
	
	/**
	 * Setter method for the side of the area in which the network is generated, in km
	 * @param sideArea	the side of the area in which the network is generated, in km, as a double
	 */
	public void setSideArea(double sideArea) {
		this.sideArea = sideArea;
	}
	
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
	
	/**
	 * Setter method for the name of the network
	 * @param name	The name of the network as a String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
