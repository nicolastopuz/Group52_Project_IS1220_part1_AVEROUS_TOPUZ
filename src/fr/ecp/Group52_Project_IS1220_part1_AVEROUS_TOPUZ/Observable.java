package fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ;

import fr.ecp.Group52_Project_IS1220_part1_AVEROUS_TOPUZ.Exceptions.NoRideException;

/**
 * An interface to be implemented by classes observing one another.
 * In this case, the interface has been built for users to observe
 * the state of stations on the network during a ride.
 * 
 * @author Pierre Averous
 * @author Nicolas Topuz
 * @since 1.0
 */
public interface Observable {
	
	/**
	 * Method to add an Observer to a station considered as possible 
	 * departure of a ride.
	 * @param obs	The user on a ride which should begin at this station.
	 */
	public void addDepartureObserver(Observer obs);
	
	/**
	 * Method to add an Observer to a station considered as possible 
	 * arrival of a ride.
	 * @param obs	The user on a ride which should end at this station.
	 */
	public void addArrivalObserver(Observer obs);
	
	/**
	 * Method to remove an Observer from a station considered as possible 
	 * departure of a ride.
	 * @param obs	The user on a ride which has begun at this station.
	 */
	public void removeDepartureObserver(Observer obs);
	
	/**
	 * Method to remove an Observer from a station considered as possible 
	 * arrival of a ride.
	 * @param obs	The user on a ride which ended at this station.
	 */
	public void removeArrivalObserver(Observer obs);
	
	/**
	 * Method to notify all Observers of a station considered as possible 
	 * departure of a ride.
	 */
	public void notifyAllDepartureObservers() throws NoRideException;
	
	/**
	 * Method to notify all Observers of a station considered as possible 
	 * arrival of a ride.
	 */
	public void notifyAllArrivalObservers() throws NoRideException;
}
