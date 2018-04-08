package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Model.Itinerary;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * An interface to be implemented by classes that observe other ones.
 * In this case, the interface has been built for users to observe
 * the state of stations on the network during a ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface Observer {
	
	/**
	 * The method to update a users's ride status when there is a change
	 * in his itinerary
	 * @throws NoRideException
	 */
	public void updateDeparture() throws NoRideException;

	public void updateArrival() throws NoRideException;

	
}
