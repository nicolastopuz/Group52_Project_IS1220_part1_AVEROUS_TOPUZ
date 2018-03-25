package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

public interface PathPreferenceVisitor {
	
	public Station[] departureAndArrival();
	
	public Station getUpdateOnArrivalStation(GPScoordinates departure);
	
	public void setRide(Ride ride);
	
}
