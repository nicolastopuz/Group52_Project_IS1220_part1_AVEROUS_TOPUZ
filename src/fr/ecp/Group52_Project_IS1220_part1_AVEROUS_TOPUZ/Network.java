package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import java.util.ArrayList;

public class Network implements VisitableItems {
	protected ArrayList<User> userList;
	protected ArrayList<Station> stationList;
	protected ArrayList<Bike> bikeList;
	protected ArrayList<Double> distanceMatrix;
	
	public Network() {
		
	}
	
	public void addStation(Station station) {
		stationList.add(station);
	}


	@Override
	public String accept(StatisticCompiler v) {
		return v.visit(this);	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<Station> getStationList() {
		return stationList;
	}

	public ArrayList<Bike> getBikeList() {
		return bikeList;
	}

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
