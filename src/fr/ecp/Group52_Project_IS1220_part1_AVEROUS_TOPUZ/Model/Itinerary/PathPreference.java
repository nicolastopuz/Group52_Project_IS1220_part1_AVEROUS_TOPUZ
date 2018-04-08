package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.GPScoordinates;
import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Network.Station.Station;

/**
 * This interface is implemented by classes defining the preference 
 * for the path a user takes on a ride. Should it be the shortest ?
 * the fastest ? and so on. 
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface PathPreference {
	
	/**
	 * Most important method of this interface : it computes the departure
	 * and arrival Stations for the given ride. In this case, depending on
	 * the user's preference for the ride.
	 * <p>This method also updates the ride durations for electrical
	 * and mechanical biking, contained in the variables 
	 * electricalRideDuration and mechanicalRideDuration.
	 * @return departureAndArrival	A Station[] Object, containing in 
	 * position 0 the departure Station, and in position 1 the arrival 
	 * Station.
	 */
	public Station[] departureAndArrival();
	
	/**
	 *
	 * A method to update the arrival station of the ride, in case the last parkingslots at this 
	 * station are taken by another user during the ride
	 *
	 * @param departure	GPScoordinates of the new start of the ride
	 * @return	the Station to which the user should go to deposit his bike
	 */
	public Station getUpdateOnArrivalStation(GPScoordinates departure);
	
	/**
	 * Setter for the ride in which the path is calculated.
	 * This method also updates the departureAndArrival variable, 
	 * so that it matches with the ride.
	 * @param ride	The ride that is to be calculated.
	 */
	public void setRide(Ride ride);
	
	
	/**
	 * Getter for Departure and Arrival stations of the optimized ride, 
	 * in a size 2 array. in position [0] is departure Station, position
	 * [1] is arrival Station.
	 * @return	the departure and arrival stations as a size 2 array.
	 */
	public Station[] getDepartureAndArrival();
	
}
