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
	
	
}
