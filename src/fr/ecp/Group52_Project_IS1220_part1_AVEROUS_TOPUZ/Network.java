package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.*;

public class Network implements VisitableItems {
	protected ArrayList<User> userList;
	protected ArrayList<Station> stationList;
	protected ArrayList<Bike> bikeList;
	protected ArrayList<Double> distanceMatrix;
	
	public Network() {
		this.userList = new ArrayList<User>();
		this.stationList = new ArrayList<Station>();
		this.bikeList = new ArrayList<Bike>();
		this.distanceMatrix = new ArrayList<Double>();		
	}
	
	
	public Station createStation(int numberOfSlots, GPScoordinates location) {
		Station s = new Station(numberOfSlots, location);
		s.setNetwork(this);
		return s;
	}
	
	public Station createStation(int numberOfSlots, GPScoordinates location, int numberOfBikes, float mechanicalBikeProportion) throws MoreBikesThanSlotsException, InvalidProportionsException {
		Station s = new Station(numberOfSlots, location, numberOfBikes, mechanicalBikeProportion);
		s.setNetwork(this);
		return s;
	}
	
	public User createUser(String name) {
		User u = new User(name);
		u.setNetwork(this);
		return u;
	}
	
	public User createUser(String name, CardTypes cardType) {
		User u = new User(name, cardType);
		u.setNetwork(this);
		return u;
	}
	
	public Bike createBike(BikesType type) {
		Bike b = BikeFactory.create(type);
		b.setNetwork(this);
		return b;
	}
	
	public void addBike(Bike bike) {
		this.bikeList.add(bike);
	}
	
	public void addUser(User user) {
		this.userList.add(user);
	}
	
	public void addStation(Station station) {
		stationList.add(station);
	}


	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);	}
	
	//Getters

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<Station> getStationList() {
		return stationList;
	}

	public ArrayList<Bike> getBikeList() {
		return bikeList;
	}
	
	
	//Setters
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public void setStationList(ArrayList<Station> stationList) {
		this.stationList = stationList;
	}

	public void setBikeList(ArrayList<Bike> bikeList) {
		this.bikeList = bikeList;
	}
	
	
	
}
